package com.aib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 注册页适配器
 */

public class RegisterAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;
    private final String[] tabTitles;

    public RegisterAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] tabTitles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabTitles = tabTitles;
    }

    /**
     * 返回第position位置的Fragmnent
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * 返回几个Viewpager滑动的Fragment数
     *
     * @return
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 返回position位置页签名字
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
