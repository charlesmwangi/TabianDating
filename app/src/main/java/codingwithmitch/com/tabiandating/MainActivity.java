package codingwithmitch.com.tabiandating;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import codingwithmitch.com.tabiandating.models.FragmentTag;
import codingwithmitch.com.tabiandating.models.Message;
import codingwithmitch.com.tabiandating.models.User;
import codingwithmitch.com.tabiandating.settings.SettingsFragment;
import codingwithmitch.com.tabiandating.util.PreferenceKeys;

public class MainActivity extends AppCompatActivity implements IMainActivity,
        BottomNavigationViewEx.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    //widgets
    private BottomNavigationViewEx mBottomNavigationViewEx;
    private ImageView mHeaderImage;
    private DrawerLayout mDrawerLayout;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //check which nav icon was selected
        switch (item.getItemId()){
            case R.id.bottom_nav_home: {
                Log.d(TAG, "onNavigationItemReselected: HomeFragment.");
                //clear tags
                mFragmentTags.clear();
                mFragmentTags = new ArrayList<>();
               init();
                break;
            }
            case R.id.bottom_nav_connections:{
                Log.d(TAG, "onNavigationItemReselected: ConnectionsFragment.");
                //highlight icon when selected
                item.setChecked(true);
                //check if the frag has already been instantiated
                if(mSavedConnectionFragment == null){
                    //create the fragment
                    mSavedConnectionFragment = new SavedConnectionsFragment();
                    //inflate the fragment using transaction
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //add fragment to the container
                    transaction.add(R.id.main_content_frame,mSavedConnectionFragment,getString(R.string.tag_fragment_saved_connections));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_saved_connections));
                    mFragments.add(new FragmentTag(mSavedConnectionFragment, getString(R.string.tag_fragment_saved_connections)));
                    //commit the transaction
                    transaction.commit();
                }
                else{
                    //remove it from the back stack
                    mFragmentTags.remove(getString(R.string.tag_fragment_saved_connections));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_saved_connections));
                }
                //set visibility when selected
                setFragmentVisibility(getString(R.string.tag_fragment_saved_connections));
                break;
            }
            case R.id.bottom_nav_messages:{
                Log.d(TAG, "onNavigationItemReselected: MessageFragment.");
                //highlight icon when selected
                item.setChecked(true);
                //check if the frag has already been instantiated
                if(mMessageFragment == null){
                    //create the fragment
                    mMessageFragment = new MessagesFragment();
                    //inflate the fragment using transaction
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //add fragment to the container
                    transaction.add(R.id.main_content_frame,mMessageFragment,getString(R.string.tag_fragment_messages));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_messages));
                    mFragments.add(new FragmentTag(mMessageFragment, getString(R.string.tag_fragment_messages)));
                    //commit the transaction
                    transaction.commit();
                }
                else{
                    //remove it from the back stack
                    mFragmentTags.remove(getString(R.string.tag_fragment_messages));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_messages));
                }
                //set visibility when selected
                setFragmentVisibility(getString(R.string.tag_fragment_messages));
                break;
            }
            //for navigation drawer
            case R.id.home:{
//                //load home fragment
//                Log.d(TAG, "onNavigationItemReselected: HomeFragment.");
//                //highlight icon when selected
//                item.setChecked(true);
//                //check if the frag has already been instantiated
//                if(mHomeFragment == null){
//                    //create the fragment
//                    mHomeFragment = new HomeFragment();
//                    //inflate the fragment using transaction
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    //add fragment to the container
//                    transaction.add(R.id.main_content_frame,mHomeFragment,getString(R.string.tag_fragment_home));
//                    //add fragment to back stack
//                    mFragmentTags.add(getString(R.string.tag_fragment_home));
//                    mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));
//                    //commit the transaction
//                    transaction.commit();
//                }
//                else{
//                    //remove it from the back stack
//                    mFragmentTags.remove(getString(R.string.tag_fragment_home));
//                    //add fragment to back stack
//                    mFragmentTags.add(getString(R.string.tag_fragment_home));
//                }
//                //set visibility when selected
//                setFragmentVisibility(getString(R.string.tag_fragment_home));
//                break;
                init();
            }
            case R.id.settings:{
                Log.d(TAG, "onNavigationItemReselected: SettingsFragment.");
//                //highlight icon when selected
//                item.setChecked(true);
                //check if the frag has already been instantiated
                if(mSettingsFragment == null){
                    //create the fragment
                    mSettingsFragment = new SettingsFragment();
                    //inflate the fragment using transaction
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //add fragment to the container
                    transaction.add(R.id.main_content_frame,mSettingsFragment,getString(R.string.tag_fragment_settings));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_settings));
                    mFragments.add(new FragmentTag(mSettingsFragment, getString(R.string.tag_fragment_settings)));
                    //commit the transaction
                    transaction.commit();
                }
                else{
                    //remove it from the back stack
                    mFragmentTags.remove(getString(R.string.tag_fragment_settings));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_settings));
                }
                //set visibility when selected
                setFragmentVisibility(getString(R.string.tag_fragment_settings));

                break;
            }
            case R.id.agreement:{
                Log.d(TAG, "onNavigationItemReselected: AgreementFragment.");
//                //highlight icon when selected
//                item.setChecked(true);
                //check if the frag has already been instantiated
                if(mAgreementFragment == null){
                    //create the fragment
                    mAgreementFragment = new AgreementFragment();
                    //inflate the fragment using transaction
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //add fragment to the container
                    transaction.add(R.id.main_content_frame,mAgreementFragment,getString(R.string.tag_fragment_agreement));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_agreement));
                    mFragments.add(new FragmentTag(mAgreementFragment, getString(R.string.tag_fragment_agreement)));
                    //commit the transaction
                    transaction.commit();
                }
                else{
                    //remove it from the back stack
                    mFragmentTags.remove(getString(R.string.tag_fragment_agreement));
                    //add fragment to back stack
                    mFragmentTags.add(getString(R.string.tag_fragment_agreement));
                }
                //set visibility when selected
                setFragmentVisibility(getString(R.string.tag_fragment_agreement));

                break;
            }
        }
        //close drawer after an item is selected
        mDrawerLayout.closeDrawer(Gravity.START);
        return false;
    }
    //fragments for back stack
    private HomeFragment mHomeFragment;
    private SavedConnectionsFragment mSavedConnectionFragment;
    private MessagesFragment mMessageFragment;
    private SettingsFragment mSettingsFragment;
    private ViewProfileFragment mViewProfileFragment;
    private ChatFragment mChatFragment;
    private AgreementFragment mAgreementFragment;

    //vars
    //for back stack
    private ArrayList<String> mFragmentTags = new ArrayList<>();
    private ArrayList<FragmentTag> mFragments = new ArrayList<>();
    //allow the app to close if back stack is empty
    private int mExitCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attach the navigation view
        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
        //load the nav drawer
        NavigationView navigationView = findViewById(R.id.navigation_view);
        //get header view
        View headerView = navigationView.getHeaderView(0);
        //attach the header to the image
        mHeaderImage = headerView.findViewById(R.id.header_image);

        //attach drawer layout to it id
        mDrawerLayout = findViewById(R.id.drawer_layout);
        
        //check if it is first login
        isFirstLogin();
        //load homefragment
        init();
        //load the bottom nav view
        initBottomNavigationView();
        //set drawer header image
        setHeaderImage();
        //set listener on the navigation view
        setNavigationViewListener();
    }
    //initialize the bottom nav view
    private void initBottomNavigationView(){
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view");
        mBottomNavigationViewEx.enableAnimation(false);

    }
    //create the init method
    private void init(){
        //check if the frag has already been instantiated
        if(mHomeFragment == null){
            //create the fragment
            mHomeFragment = new HomeFragment();
            //inflate the fragment using transaction
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //add fragment to the container
            transaction.add(R.id.main_content_frame,mHomeFragment,getString(R.string.tag_fragment_home));
            //add fragment to back stack
            mFragmentTags.add(getString(R.string.tag_fragment_home));
            mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));
            //commit the transaction
            transaction.commit();
        }
        else{
            //remove it from the back stack
            mFragmentTags.remove(getString(R.string.tag_fragment_home));
            //add fragment to back stack
            mFragmentTags.add(getString(R.string.tag_fragment_home));
        }
        //highlight icon when selected
        //set visibility when selected
        setFragmentVisibility(getString(R.string.tag_fragment_home));
    }
    //control back stack,only show fragment which is on top
    private void setFragmentVisibility(String tagname){
        for(int i=0; i<mFragments.size(); i++){
            if (tagname.equals(mFragments.get(i).getTag())){
                //show this fragment
                //inflate the fragment using transaction
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //add fragment to the container
                transaction.show(mFragments.get(i).getFragment());
                transaction.commit();
            }
            else{
                //dont show
                //inflate the fragment using transaction
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //add fragment to the container
                transaction.hide(mFragments.get(i).getFragment());
                transaction.commit();
            }
        }
    }
    //set listener for the drawer layout
    private void setNavigationViewListener(){
        Log.d(TAG, "setNavigationViewListener: initializing the navigation drawer listener");
        //ref the id of the navigation view
        NavigationView navigationView = findViewById(R.id.navigation_view);
        //set the listener
        navigationView.setNavigationItemSelectedListener(this);
    }
    //set drawer header image
    private void setHeaderImage(){
        Log.d(TAG, "setHeaderImage: setting header image for navigation drawer");
        Glide.with(this)
                .load(R.drawable.couple)
                .into(mHeaderImage);
    }

    private void isFirstLogin(){
        Log.d(TAG, "isFirstLogin: checking if this is the first login");
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin = preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN,true);
        //check if boolean is true
        if (isFirstLogin){
            Log.d(TAG, "isFirstLogin: launching alert dialog");

            //build the alert dialog
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.first_time_user_message));
            //set the positive button
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d(TAG, "onClick: closing dialog");
                    //prevent the pop up in future
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN,false);
                    editor.commit();
                    dialogInterface.dismiss();
                }
            });
            alertDialogBuilder.setIcon(R.drawable.tabian_dating);
            //provide a blank title esle it wont show
            alertDialogBuilder.setTitle("");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }

    @Override
    public void inflateViewProfileFragment(User user) {
        //check if the frag has already been instantiated
        if(mViewProfileFragment != null){
          getSupportFragmentManager().beginTransaction().remove(mViewProfileFragment).commitAllowingStateLoss();
        }
        //get selected user object
        //create the fragment
        mViewProfileFragment = new ViewProfileFragment();
        //create bundle of the selected user
        Bundle args = new Bundle();
        //add user object to the bundle
        args.putParcelable(getString(R.string.intent_user), user);
        //set args to the fragment
        mViewProfileFragment.setArguments(args);
        //inflate the fragment using transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //add fragment to the container
        transaction.add(R.id.main_content_frame,mViewProfileFragment,getString(R.string.tag_fragment_view_profile));

        //commit the transaction
        transaction.commit();
        //add fragment to back stack
        mFragmentTags.add(getString(R.string.tag_fragment_view_profile));
        mFragments.add(new FragmentTag(mViewProfileFragment, getString(R.string.tag_fragment_view_profile)));
        //set visibility when selected
        setFragmentVisibility(getString(R.string.tag_fragment_view_profile));
    }

    @Override
    public void onMessageSelected(Message message) {
        //get selected user object
        if(mChatFragment !=null){
            getSupportFragmentManager().beginTransaction().remove(mChatFragment).commitAllowingStateLoss();
        }
        //create the fragment
        mChatFragment = new ChatFragment();
        //create bundle of the selected user
        Bundle args = new Bundle();
        //add user object to the bundle
        args.putParcelable(getString(R.string.intent_message), message);
        //set args to the fragment
        mChatFragment.setArguments(args);
        //inflate the fragment using transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //add fragment to the container
        transaction.add(R.id.main_content_frame,mChatFragment,getString(R.string.tag_fragment_view_profile));
        //commit the transaction
        transaction.commit();
        //add fragment to back stack
        mFragmentTags.add(getString(R.string.tag_fragment_chat));
        mFragments.add(new FragmentTag(mChatFragment, getString(R.string.tag_fragment_chat)));
        //set visibility when selected
        setFragmentVisibility(getString(R.string.tag_fragment_chat));

    }


}
