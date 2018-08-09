package com.six.webview;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kotlink on 2017/12/13.
 * @描述 MainTabPagerAdapter 首页 tab+viewpager的适配器
 */

public class MainTabPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private Context context;
    private ImageView imageView;
    private TextView textView;
    private String[] titles;

    public MainTabPagerAdapter(Context context, FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragments.get(arg0);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



}

