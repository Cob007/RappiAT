package cob.michealcob.rappiat.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList;
    private final List<String> mFragmentTitleList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.mFragmentList = new ArrayList<>();
        this.mFragmentTitleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {

        return this.mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title){
        this.mFragmentList.add(fragment);
        this.mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return
               this.mFragmentTitleList.get(position);
    }
}
