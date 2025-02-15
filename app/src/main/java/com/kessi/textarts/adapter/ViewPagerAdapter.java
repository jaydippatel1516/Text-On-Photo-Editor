package com.kessi.textarts.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList();
    private final List<String> mFragmentTitleList = new ArrayList();

    @Override 
    public int getItemPosition(Object obj) {
        return -2;
    }

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, 1);
    }

    @Override 
    public Fragment getItem(int i) {
        return this.mFragmentList.get(i);
    }

    @Override 
    public int getCount() {
        return this.mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String str) {
        this.mFragmentList.add(fragment);
        this.mFragmentTitleList.add(str);
    }

    @Override 
    public CharSequence getPageTitle(int i) {
        return this.mFragmentTitleList.get(i);
    }
}
