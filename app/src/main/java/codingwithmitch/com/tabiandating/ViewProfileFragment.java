package codingwithmitch.com.tabiandating;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.HashSet;
import java.util.Set;

import codingwithmitch.com.tabiandating.models.User;
import codingwithmitch.com.tabiandating.util.PreferenceKeys;
import codingwithmitch.com.tabiandating.util.Resources;
import de.hdodenhof.circleimageview.CircleImageView;


public class ViewProfileFragment extends Fragment implements OnLikeListener {

    private static final String TAG = "ViewProfileFragment";

    //widgets
    private TextView mFragmentHeading, mName, mGender, mInterestedIn, mStatus;
    private LikeButton mLikeButton;
    private RelativeLayout mBackArrow;
    private CircleImageView mProfileImage;

    //vars
    private User mUser;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create the bundle
        Bundle bundle = this.getArguments();
        //check if bundle is null
        if(bundle != null){
            mUser = bundle.getParcelable(getString(R.string.intent_user));
            Log.d(TAG, "onCreate: got incoming bundle: " + mUser.getName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        Log.d(TAG, "onCreateView: started.");
        //connect widgets to the views
        mBackArrow = view.findViewById(R.id.back_arrow);
        mFragmentHeading = view.findViewById(R.id.fragment_heading);
        mProfileImage = view.findViewById(R.id.profile_image);
        mLikeButton = view.findViewById(R.id.heart_button);
        mName = view.findViewById(R.id.name);
        mGender = view.findViewById(R.id.gender);
        mInterestedIn = view.findViewById(R.id.interested_in);
        mStatus = view.findViewById(R.id.status);

        mLikeButton.setOnLikeListener(this);

        //load data
        init();
        setBackgroundImage(view);
        checkIfConnected();


        return view;
    }
    private void init(){
        Log.d(TAG, "init: initializing ViewProfileFragment");
        //check if user is null
        if(mUser != null){
            //set profile image
            Glide.with(getActivity())
                    .load(mUser.getProfile_image())
                    .into(mProfileImage);
            //set name
            mName.setText(mUser.getName());
            mGender.setText(mUser.getGender());
            mInterestedIn.setText(mUser.getInterested_in());
            mStatus.setText(mUser.getStatus());
        }
    }
    //set background image using glide
    private void setBackgroundImage(View view){
        //find the view
        ImageView backgroundImage = view.findViewById(R.id.background);
        //set background image
        Glide.with(getActivity())
                .load(Resources.BACKGROUND_HEARTS)
                .into(backgroundImage);
    }

    //check if user is already connected
    private void checkIfConnected(){
        //get Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //get list of all the saved connections of this user
        //use set object to eliminate duplicates
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, new HashSet<String>());

        //check if user is on the list
        if(savedNames.contains(mUser.getName())){
            mLikeButton.setLiked(true);
        }
        else{
            mLikeButton.setLiked(false);
        }
    }


    @Override
    public void liked(LikeButton likeButton) {
        Log.d(TAG, "liked: Liked.");
        //get Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();

        //get list of all the saved connections of this user
        //use set object to eliminate duplicates
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, new HashSet<String>());
        savedNames.add(mUser.getName());

        //add set to the editor
        editor.putStringSet(PreferenceKeys.SAVED_CONNECTIONS, savedNames);
        editor.commit();
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        Log.d(TAG, "unLiked: unliked.");
        //get Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();

        //get list of all the saved connections of this user
        //use set object to eliminate duplicates
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, new HashSet<String>());
        savedNames.remove(mUser.getName());
        editor.remove(PreferenceKeys.SAVED_CONNECTIONS);
        editor.commit();

        //add set to the editor
        editor.putStringSet(PreferenceKeys.SAVED_CONNECTIONS, savedNames);
        editor.commit();
    }
}
















