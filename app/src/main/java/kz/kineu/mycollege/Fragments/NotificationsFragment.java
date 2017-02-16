package kz.kineu.mycollege.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import kz.kineu.mycollege.Adapters.NotificationsAdapter;
import kz.kineu.mycollege.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class NotificationsFragment extends Fragment {


    private final String LOG_TAG = "MyCollegeApp";
    private String url = "http://78.46.123.237:7777";
    private NotificationsAdapter mNotificationsAdapter;
    private ListView mListView;

    public NotificationsFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        mListView = (ListView) view.findViewById(R.id.lvNotifications);
        mNotificationsAdapter = new NotificationsAdapter(getActivity(),0);
        mListView.setAdapter(mNotificationsAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       menu.clear();
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                mNotificationsAdapter.clear();
                mNotificationsAdapter.notifyDataSetChanged();
                mNotificationsAdapter.loadNotifications();
        }
        return super.onOptionsItemSelected(item);
    }
}
