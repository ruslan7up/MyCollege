package kz.kineu.mycollege.Acitivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import java.io.File;

import kz.kineu.mycollege.Fragments.BTScheduleFragment;
import kz.kineu.mycollege.Fragments.ChatFragment;
import kz.kineu.mycollege.Fragments.NewsListFragment;
import kz.kineu.mycollege.Fragments.NotificationsFragment;
import kz.kineu.mycollege.Fragments.ReadNewsFragment;
import kz.kineu.mycollege.Fragments.ScheduleFragment;
import kz.kineu.mycollege.Fragments.SettingsFragment;
import kz.kineu.mycollege.R;

public class FragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = "MyCollegeApp";
    private NewsListFragment mNewsListFragment;
    private ScheduleFragment scheduleFragment;
    private BTScheduleFragment btScheduleFragment;
    private ChatFragment chatFragment;
    private NotificationsFragment notificationsFragment;
    private SettingsFragment settingsFragment;
    private ReadNewsFragment mReadNewsFragment;
    private String lastTag = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset != 0) {
                    InputMethodManager inputManager = (InputMethodManager) FragmentActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(FragmentActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mNewsListFragment = new NewsListFragment();
        scheduleFragment = new ScheduleFragment();
        btScheduleFragment = new BTScheduleFragment();
        chatFragment = new ChatFragment();
        notificationsFragment = new NotificationsFragment();
        settingsFragment = new SettingsFragment();
        mReadNewsFragment = new ReadNewsFragment();

        if(getIntent()==null || getIntent().getStringExtra("fragment")==null) {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            this.setTitle(R.string.news);
            FragmentTransaction transaction = getSupportFragmentManager().
                    beginTransaction();
            transaction.add(R.id.container, mNewsListFragment,"news");
            lastTag = "news";
            navigationView.getMenu().getItem(0).setChecked(true);
            transaction.commit();
        } else {
            String fragmentname = getIntent().getStringExtra("fragment");
            if(fragmentname.equals("notifications")) {
                this.setTitle(R.string.notifications);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, notificationsFragment,"notifications");
                lastTag = "notifications";
                navigationView.getMenu().getItem(4).setChecked(true);
                transaction.commit();
            } else if (fragmentname.equals("readnews")){
                this.setTitle(R.string.news);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container,mReadNewsFragment,"readnews");
                lastTag = "readnews";
                navigationView.getMenu().getItem(0).setChecked(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                transaction.commit();
            } else {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, mNewsListFragment);
                navigationView.getMenu().getItem(0).setChecked(true);
                transaction.commit();
            }

        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (id == R.id.nav_news) {
            this.setTitle(R.string.news);
            transaction.hide(manager.findFragmentByTag(lastTag));
            if(manager.findFragmentByTag("news")!=null) {
                transaction.show(manager.findFragmentByTag("news"));
                lastTag = "news";
            } else {
                transaction.add(R.id.container, mNewsListFragment,"news");
                lastTag = "news";
            }

        } else if (id == R.id.nav_schedule) {
            this.setTitle(R.string.schedule);
            transaction.hide(manager.findFragmentByTag(lastTag));
            if(manager.findFragmentByTag("schedule")!=null) {
                transaction.show(manager.findFragmentByTag("schedule"));
                lastTag = "schedule";
            } else {
                transaction.add(R.id.container, scheduleFragment,"schedule");
                lastTag = "schedule";
            }
        } else if (id == R.id.nav_btschedule) {
            this.setTitle(R.string.btschedule);
            transaction.hide(manager.findFragmentByTag(lastTag));
            if(manager.findFragmentByTag("btschedule")!=null) {
                transaction.show(manager.findFragmentByTag("btschedule"));
                lastTag = "btschedule";
            } else {
                transaction.add(R.id.container, btScheduleFragment,"btschedule");
                lastTag = "btschedule";
            }
        } else if (id == R.id.nav_chat) {
            this.setTitle(R.string.chat);
            transaction.hide(manager.findFragmentByTag(lastTag));
            if(manager.findFragmentByTag("chat")!=null) {
                transaction.show(manager.findFragmentByTag("chat"));
                lastTag = "chat";
            } else {
                transaction.add(R.id.container, chatFragment,"chat");
                lastTag = "chat";
            }
        } else if (id == R.id.nav_notifications) {
            this.setTitle(R.string.notifications);
            transaction.hide(manager.findFragmentByTag(lastTag));
            if(manager.findFragmentByTag("notifications")!=null) {
                transaction.show(manager.findFragmentByTag("notifications"));
                lastTag = "notifications";
            } else {
                transaction.add(R.id.container, notificationsFragment,"notifications");
                lastTag = "notifications";
            }
        } else if (id == R.id.nav_settings) {
            this.setTitle(R.string.settings);
            transaction.hide(manager.findFragmentByTag(lastTag));
            if(manager.findFragmentByTag("settings")!=null) {
                transaction.show(manager.findFragmentByTag("settings"));
                lastTag = "settings";
            } else {
                transaction.add(R.id.container, settingsFragment,"settings");
                lastTag = "settings";
            }
        }

        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}