package codingwithmitch.com.tabiandating.settings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    //store tab fragments in an array list
    private final List<Fragment> mFragmentList = new ArrayList<>();
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    //add fragment to the pager adapter
    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }
}
