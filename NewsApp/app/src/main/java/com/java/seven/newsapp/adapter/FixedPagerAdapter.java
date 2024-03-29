package com.java.seven.newsapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.java.seven.newsapp.chinesenews.news.NewsCategory;
import com.java.seven.newsapp.chinesenews.news.NewsFragment;

import java.util.List;

/**
 * map position to fragment
 * position -> fragment[position]
 * position -> titles[position]
 */
public class FixedPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
             fragment = (Fragment)super.instantiateItem(container, position);
        } catch (Exception e) {}
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {}

    @Override
    public CharSequence getPageTitle(int position) {
        NewsFragment newsFragment = (NewsFragment)fragments.get(position);
        return NewsCategory.codeToName(newsFragment.getCategoryCode());
    }
}


