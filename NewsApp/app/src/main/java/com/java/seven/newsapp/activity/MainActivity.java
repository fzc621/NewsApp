package com.java.seven.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.FixedPagerAdapter;
import com.java.seven.newsapp.chinesenews.news.NewsCategory;
import com.java.seven.newsapp.chinesenews.news.NewsFragment;
import com.java.seven.newsapp.util.AppConstants;
import com.java.seven.newsapp.util.SharedPreferencesUtil;

import org.jsoup.select.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int SUBSCRIBE_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

    private int[] categoryCodes = NewsCategory.getAllCategoryCodes();
    private TabLayout tab_layout;
    private List<Fragment> fragments;
    private FixedPagerAdapter fixedPagerAdapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("hashcode: " + this.hashCode());
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewRef();
        initData();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * init some reference of view
     */
    private void initViewRef() {
        tab_layout = (TabLayout) this.findViewById(R.id.tab_layout);
        pager = (ViewPager) this.findViewById(R.id.viewpager);
    }

    /**
     * it recreates the fragments and pager adapter
     */
    private void initData() {
        fixedPagerAdapter = new FixedPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        for (int i = 0; i < categoryCodes.length; ++i) {
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setCategoryCode(categoryCodes[i]);
            fragments.add(newsFragment);
        }
        fixedPagerAdapter.setFragments(fragments);
        pager.setAdapter(fixedPagerAdapter);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.setupWithViewPager(pager);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        switch (item.getItemId()) {
            case R.id.item_night:
                SharedPreferencesUtil.setBoolean(this, AppConstants.ISNIGHT, true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
                break;
            case R.id.item_light:
                SharedPreferencesUtil.setBoolean(this, AppConstants.ISNIGHT, false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_subscribe) {
            Intent intent = new Intent(MainActivity.this, SubscribeActivity.class);
            boolean[] subscribeState = new boolean[NewsCategory.CATEGORY_CNT + 1];
            for (int i = 0; i < subscribeState.length; ++i) {
                subscribeState[i] = false;
            }
            for (int i = 0; i < categoryCodes.length; ++i) {
                subscribeState[categoryCodes[i]] = true;
            }
            intent.putExtra(SubscribeActivity.KEY, subscribeState);
            startActivityForResult(intent, SUBSCRIBE_ACTIVITY_REQUEST_CODE);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SUBSCRIBE_ACTIVITY_REQUEST_CODE &&
                resultCode == SubscribeActivity.RESULT_CODE) {
            List<Integer> categoryCodesList = new ArrayList<>();
            boolean[] subscribeState = data.getBooleanArrayExtra(SubscribeActivity.KEY);
            for (int i = 0; i < subscribeState.length; ++i) {
                if (subscribeState[i] == true)
                    categoryCodesList.add(i);
            }
            categoryCodes = new int[categoryCodesList.size()];
            for (int i = 0; i < categoryCodes.length; ++i) {
                categoryCodes[i] = categoryCodesList.get(i);
            }
            initData();
        }

    }
}
