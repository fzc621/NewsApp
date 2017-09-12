package com.java.seven.newsapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.FixedPagerAdapter;

import com.java.seven.newsapp.chinesenews.favorate.FavorActivity;
import com.java.seven.newsapp.chinesenews.news.NewsCategory;

import com.java.seven.newsapp.chinesenews.news.NewsFragment;
import com.java.seven.newsapp.util.AppConstants;
import com.java.seven.newsapp.util.AppGlobal;
import com.java.seven.newsapp.util.SevenDecoder;
import com.java.seven.newsapp.util.SevenEncoder;
import com.java.seven.newsapp.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int SUBSCRIBE_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

    private boolean[] subscribeStates;
    private TabLayout tab_layout;
    private List<Fragment> fragments;
    private FixedPagerAdapter fixedPagerAdapter;
    private ViewPager pager;
    private List<OnIgnoreKeysChangeListener> onIgnoreKeysChangeListeners;

    public MainActivity() { AppGlobal.activity = this; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(this.hashCode());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewRef();
        initSetting();
        initData();
        initListener();

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

    private void initSetting() {
        String subscribeStatesStr = SharedPreferencesUtil.getString(this, AppConstants.PREF_KEY_SUBSCRIBE,
                AppConstants.DEFAULT_SUBSCRIBE_STATE);
        subscribeStates = SevenDecoder.decodeSubscribeStatesStr(subscribeStatesStr);
        AppGlobal.saveDataUsage = SharedPreferencesUtil.getBoolean(this, AppConstants.PREF_KEY_SAVE_DATA_USAGE,
                AppConstants.DEFAULT_SAVE_DATA_USAGE);
        String ignoreKeysStr = SharedPreferencesUtil.getString(this, AppConstants.PREF_KEY_IGNORE,
                AppConstants.DEFAULT_KEY_IGNORE);
        AppGlobal.ignoreKeys = SevenDecoder.decodeShieldKeyWords(ignoreKeysStr);
    }

    private void initData() {
        fixedPagerAdapter = new FixedPagerAdapter(getSupportFragmentManager());
        if (AppGlobal.fragments == null) {
            fragments = new ArrayList<>();
            for (int i = 0; i < subscribeStates.length; ++i) {
                if (subscribeStates[i]) {
                    NewsFragment newsFragment = new NewsFragment();
                    newsFragment.setCategoryCode(i);
                    fragments.add(newsFragment);
                }
            }
            AppGlobal.fragments = fragments;
        }
        else {
            fragments = AppGlobal.fragments;
            for (int i = 0; i < fragments.size(); ++i) {
                ((NewsFragment)(fragments.get(i))).onContextUpdated(this);
            }
        }
        fixedPagerAdapter.setFragments(fragments);
        pager.setAdapter(fixedPagerAdapter);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.setupWithViewPager(pager);
    }

    private void initListener() {
        onIgnoreKeysChangeListeners = new ArrayList<>();
        for (int i = 0; i < fragments.size(); ++i) {
            onIgnoreKeysChangeListeners.add((NewsFragment)(fragments.get(i)));
        }
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
//            case R.id.item_night:
//                SharedPreferencesUtil.setBoolean(this, AppConstants.ISNIGHT, true);
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                removeFragmentFromActivity();
//                recreate();
//                break;
//            case R.id.item_light:
//                SharedPreferencesUtil.setBoolean(this, AppConstants.ISNIGHT, false);
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                removeFragmentFromActivity();
//                recreate();
//                break;
            case R.id.item_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
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
            onSubscribeClicked();
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, FavorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            onSettingClicked();


        }  else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onSubscribeClicked() {
        Intent intent = new Intent(MainActivity.this, SubscribeActivity.class);
        intent.putExtra(SubscribeActivity.KEY, subscribeStates);
        startActivityForResult(intent, SUBSCRIBE_ACTIVITY_REQUEST_CODE);
    }

    private void onSettingClicked() {
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.setting_dialog_layout,(ViewGroup)findViewById(R.id.dialog));

        // initViewRef
        final Switch saveDataUsageSwitch = dialog.findViewById(R.id.save_data_usage_switch);
        final Switch nightModeSwitch = dialog.findViewById(R.id.night_mode_switch);
        final EditText ignoreKeysInput = dialog.findViewById(R.id.ignore_keys_input);

        // initView
        saveDataUsageSwitch.setChecked(AppGlobal.saveDataUsage);
        if (AppGlobal.ignoreKeys.length != 0) {
            ignoreKeysInput.setText(SevenEncoder.encodeShieldKeyWords(AppGlobal.ignoreKeys));
        }

        nightModeSwitch.setChecked(AppGlobal.isNight);

        // set dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Setting");
        builder.setView(dialog);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // data usage
                boolean saveDataUsage = saveDataUsageSwitch.isChecked();
                SharedPreferencesUtil.setBoolean(MainActivity.this, AppConstants.PREF_KEY_SAVE_DATA_USAGE, saveDataUsage);
                AppGlobal.saveDataUsage = saveDataUsage;
                // ignore
                String ignoreKeysStr = ignoreKeysInput.getText().toString();
                String[] ignoreKeys = SevenDecoder.decodeShieldKeyWords(ignoreKeysStr);
                ignoreKeysStr = SevenEncoder.encodeShieldKeyWords(ignoreKeys);
                SharedPreferencesUtil.setString(MainActivity.this, AppConstants.PREF_KEY_IGNORE, ignoreKeysStr);
                AppGlobal.ignoreKeys = ignoreKeys;
                for (int i = 0; i < onIgnoreKeysChangeListeners.size(); ++i) {
                    onIgnoreKeysChangeListeners.get(i).onIgnoreKeysChange(ignoreKeys);
                }
                // night mode
                final boolean setNight = nightModeSwitch.isChecked();
                if(AppGlobal.isNight != setNight) {
                    AppGlobal.isNight = setNight;
                    SharedPreferencesUtil.setBoolean(MainActivity.this, AppConstants.ISNIGHT, setNight);
                    AppCompatDelegate.setDefaultNightMode(setNight?AppCompatDelegate.MODE_NIGHT_YES:AppCompatDelegate.MODE_NIGHT_NO);
                    removeFragmentFromActivity();
                    MainActivity.this.runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            recreate();
                        }
                    });
                }
            }
        });
        builder.setNegativeButton("CANCEL", null);
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SUBSCRIBE_ACTIVITY_REQUEST_CODE &&
                resultCode == SubscribeActivity.RESULT_CODE) {
            subscribeStates = data.getBooleanArrayExtra(SubscribeActivity.KEY);
            String subscribeStatesStr = SevenEncoder.encodeSubscribeStates(subscribeStates);
            SharedPreferencesUtil.setString(this, AppConstants.PREF_KEY_SUBSCRIBE, subscribeStatesStr);
            AppGlobal.fragments = null;
            removeFragmentFromActivity();
            recreate();
        }

    }

    public void removeFragmentFromActivity(){
        if(fragments == null)
            return;
        for (Fragment fr: fragments) {
            if(fr != null)
                getSupportFragmentManager().beginTransaction().remove(fr).commit();
        }

    }

    public interface OnIgnoreKeysChangeListener {
        public void onIgnoreKeysChange(String[] ignoreKeys);
    }

}
