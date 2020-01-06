package codingwithmitch.com.tabiandating.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import codingwithmitch.com.tabiandating.R;


public class ChoosePhotoActivity extends AppCompatActivity {

    private static final String TAG = "ChoosePhotoActivity";
    private static final int GALLERY_FRAGMENT = 0;
    private static final int PHOTO_FRAGMENT = 1;

    //fragments
    private GalleryFragment mGalleryFragment;
    private PhotoFragment mPhotoFragment;

    //widgets
    private ViewPager mViewPager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
     //attach viewPager to its id
        mViewPager = findViewById(R.id.viewpager_container);

        setupViewPager();

    }
    private void setupViewPager(){
       // declare adapter object
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        //instantiate the fragments
        mPhotoFragment = new PhotoFragment();
        mGalleryFragment = new GalleryFragment();
        //add fragment to the adapter
        adapter.addFragment(mGalleryFragment);
        adapter.addFragment(mPhotoFragment);

        //set adpter to the viewpager
        mViewPager.setAdapter(adapter);

        //set up Tabs
        TabLayout tabLayout = findViewById(R.id.tabs_bottom);
        tabLayout.setupWithViewPager(mViewPager);

        //display text on the tabs
        tabLayout.getTabAt(GALLERY_FRAGMENT).setText(getString(R.string.tag_fragment_gallery));
        tabLayout.getTabAt(PHOTO_FRAGMENT).setText(getString(R.string.tag_fragment_photo));
    }


}










