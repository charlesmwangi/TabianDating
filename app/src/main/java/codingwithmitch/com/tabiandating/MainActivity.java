package codingwithmitch.com.tabiandating;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import codingwithmitch.com.tabiandating.models.Message;
import codingwithmitch.com.tabiandating.models.User;
import codingwithmitch.com.tabiandating.util.PreferenceKeys;

public class MainActivity extends AppCompatActivity implements IMainActivity,
        BottomNavigationViewEx.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";

    //widgets
    private BottomNavigationViewEx mBottomNavigationViewEx;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //check which nav icon was selected
        switch (item.getItemId()){
            case R.id.bottom_nav_home: {
                Log.d(TAG, "onNavigationItemReselected: HomeFragment.");
                //highlight icon when selected
                item.setChecked(true);
                //create the fragment
                HomeFragment homeFragment = new HomeFragment();
                //inflate the fragment using transaction
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //add fragment to the container
                transaction.replace(R.id.main_content_frame,homeFragment,getString(R.string.tag_fragment_home));
                //add transaction to the back stack
                transaction.addToBackStack(getString(R.string.tag_fragment_home));
                //commit the transaction
                transaction.commit();
                break;
            }
            case R.id.bottom_nav_connections:{
                Log.d(TAG, "onNavigationItemReselected: ConnectionsFragment.");
                //highlight icon when selected
                item.setChecked(true);
                //create the fragment
                SavedConnectionsFragment savedConnectionsFragment = new SavedConnectionsFragment();
                //inflate the fragment using transaction
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //add fragment to the container
                transaction.replace(R.id.main_content_frame,savedConnectionsFragment,getString(R.string.tag_fragment_saved_connections));
                //add transaction to the back stack
                transaction.addToBackStack(getString(R.string.tag_fragment_saved_connections));
                //commit the transaction
                transaction.commit();
                break;
            }
            case R.id.bottom_nav_messages:{
                Log.d(TAG, "onNavigationItemReselected: MessageFragment.");
                //highlight icon when selected
                item.setChecked(true);
                //create the fragment
                MessagesFragment messagesFragment = new MessagesFragment();
                //inflate the fragment using transaction
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //add fragment to the container
                transaction.replace(R.id.main_content_frame,messagesFragment,getString(R.string.tag_fragment_messages));
                //add transaction to the back stack
                transaction.addToBackStack(getString(R.string.tag_fragment_messages));
                //commit the transaction
                transaction.commit();
                break;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attach the navigation view
        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
        
        //check if it is first login
        isFirstLogin();
        //load fragment
        init();
        //load the bottom nav view
        initBottomNavigationView();
    }
    //initialize the bottom nav view
    private void initBottomNavigationView(){
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view");
        mBottomNavigationViewEx.enableAnimation(false);

    }
    //create the init method
    private void init(){
        //create the fragment
        HomeFragment homeFragment = new HomeFragment();
        //inflate the fragment using transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //add fragment to the container
        transaction.replace(R.id.main_content_frame,homeFragment,getString(R.string.tag_fragment_home));
        //add transaction to the back stack
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        //commit the transaction
        transaction.commit();
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
        //get selected user object
        //create the fragment
        ViewProfileFragment viewProfileFragment = new ViewProfileFragment();
        //create bundle of the selected user
        Bundle args = new Bundle();
        //add user object to the bundle
        args.putParcelable(getString(R.string.intent_user), user);
        //set args to the fragment
        viewProfileFragment.setArguments(args);
        //inflate the fragment using transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //add fragment to the container
        transaction.replace(R.id.main_content_frame,viewProfileFragment,getString(R.string.tag_fragment_view_profile));
        //add transaction to the back stack
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        //commit the transaction
        transaction.commit();
    }

    @Override
    public void onMessageSelected(Message message) {

    }


}
