package kz.kineu.mycollege.Acitivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import kz.kineu.mycollege.Fragments.BTScheduleFragment;
import kz.kineu.mycollege.Fragments.ChatFragment;
import kz.kineu.mycollege.Fragments.NewsListFragment;
import kz.kineu.mycollege.Fragments.NotificationsFragment;
import kz.kineu.mycollege.Fragments.ReadNewsFragment;
import kz.kineu.mycollege.Fragments.ScheduleFragment;
import kz.kineu.mycollege.Fragments.ScheduleSearchFragment;
import kz.kineu.mycollege.Fragments.SubsFragment;
import kz.kineu.mycollege.R;

public class FragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = "MyCollegeApp";
    private NewsListFragment mNewsListFragment;
    private ScheduleFragment scheduleFragment;
    private BTScheduleFragment btScheduleFragment;
    private ChatFragment chatFragment;
    private NotificationsFragment notificationsFragment;
    private ReadNewsFragment mReadNewsFragment;
    private ScheduleSearchFragment mScheduleSearchFragment;
    private SubsFragment mSubsFragment;

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
        mReadNewsFragment = new ReadNewsFragment();
        mScheduleSearchFragment = new ScheduleSearchFragment();
        mSubsFragment = new SubsFragment();
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
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                navigationView.getMenu().getItem(4).setChecked(true);
                transaction.commit();
            } else if (fragmentname.equals("readnews")){
                this.setTitle(R.string.news);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container,mReadNewsFragment,"readnews");
                lastTag = "readnews";
                navigationView.getMenu().getItem(0).setChecked(true);
                toggle.setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                transaction.commit();
            }else if(fragmentname.equals("schedule")) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container,scheduleFragment,"scheduleopened");
                lastTag = "scheduleopened";
                navigationView.getMenu().getItem(1).setChecked(true);
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
                transaction.add(R.id.container, mScheduleSearchFragment,"schedule");
                lastTag = "schedule";
            }
        } else if(id == R.id.nav_subs) {
            this.setTitle(R.string.subs);
            transaction.hide(manager.findFragmentByTag(lastTag));
            if(manager.findFragmentByTag("substitution")!=null) {
                transaction.show(manager.findFragmentByTag("substitution"));
                lastTag = "substitution";
            } else {
                transaction.add(R.id.container, mSubsFragment,"substitution");
                lastTag = "substitution";
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
        }

        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}