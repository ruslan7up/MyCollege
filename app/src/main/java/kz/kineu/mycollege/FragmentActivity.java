package kz.kineu.mycollege;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kz.kineu.mycollege.Fragments.BTScheduleFragment;
import kz.kineu.mycollege.Fragments.ChatFragment;
import kz.kineu.mycollege.Fragments.NewsListFragment;
import kz.kineu.mycollege.Fragments.NotificationsFragment;
import kz.kineu.mycollege.Fragments.ScheduleFragment;
import kz.kineu.mycollege.Fragments.SettingsFragment;

public class FragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NewsListFragment mNewsListFragment;
    private ScheduleFragment scheduleFragment;
    private BTScheduleFragment btScheduleFragment;
    private ChatFragment chatFragment;
    private NotificationsFragment notificationsFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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

        if(getIntent()!=null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, mNewsListFragment);
            transaction.commit();
        } else {
            String fragmentname = getIntent().getStringExtra("fragment");
            if(fragmentname.equals("notifications")) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, notificationsFragment);
                transaction.commit();
            } else {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, mNewsListFragment);
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
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        if (id == R.id.nav_news) {
            transaction.replace(R.id.container, mNewsListFragment);
        } else if (id == R.id.nav_schedule) {
            transaction.replace(R.id.container, scheduleFragment);
        } else if (id == R.id.nav_btschedule) {
            transaction.replace(R.id.container, btScheduleFragment);
        } else if (id == R.id.nav_chat) {
            transaction.replace(R.id.container, chatFragment);
        } else if (id == R.id.nav_notifications) {
            transaction.replace(R.id.container, notificationsFragment);
        } else if (id == R.id.nav_settings) {
            transaction.replace(R.id.container, settingsFragment);
        }

        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}