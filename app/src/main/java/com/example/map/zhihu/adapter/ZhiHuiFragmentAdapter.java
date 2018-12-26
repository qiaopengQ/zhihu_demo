package com.example.map.zhihu.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ZhiHuiFragmentAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments;
    private List<String> strings ;
    public ZhiHuiFragmentAdapter(FragmentManager fm,List<Fragment> fragments,List<String> strings) {
        super(fm);
        this.fragments=fragments;
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
