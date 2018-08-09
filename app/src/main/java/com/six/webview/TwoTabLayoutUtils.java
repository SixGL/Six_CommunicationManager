package com.six.webview;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KotlinK on 2017/12/3.
 */

public class TwoTabLayoutUtils implements TabLayout.OnTabSelectedListener {

    private String[] titles = new String[]{"无参无返回值", "无参有返回值", "有参无返回值","有参有返回值"};
    private Activity mActivity;
    private ViewPager mViewpager;
    private int listSize=0;
    private String TAG= "TabLayoutUtils";

    /**
     * @param fragments Fragment的集合
     * @param size Fragment的集合的长度
     * */
    public  void setTabItem(TwoFragmentAndActivity mainActivity, TabLayout tabLayout, List<Fragment> fragments, ViewPager viewpager, int size){
        mActivity=mainActivity;
        mViewpager=viewpager;
        listSize=size;
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(mainActivity.getResources().getColor(R.color.colorPrimary));
        MainTabPagerAdapter viewPagerAdapter = new MainTabPagerAdapter(mainActivity, mainActivity.getSupportFragmentManager(), fragments, titles);
        viewpager.setOffscreenPageLimit(size);
        viewpager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setOnTabSelectedListener(this);
        setupTabIcons(tabLayout);
    }
    /**
     * @see （）etupTabIcons  设置tablayout 文字加图片处理
     */
    private void setupTabIcons(TabLayout tabLayout ) {
        for (int i=0;i<listSize;i++){
            tabLayout.getTabAt(i).setCustomView(getTabView(i));
        }
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.main_tab_item, null);
        TextView txt_title = (TextView) view.findViewById(R.id.tv);
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(titles[position]);
        return view;
    }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Log.i(TAG,TAG);
            mViewpager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }

}
