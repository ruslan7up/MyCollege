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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import kz.kineu.mycollege.API.BellTimeAPI;
import kz.kineu.mycollege.Entities.BellTime;
import kz.kineu.mycollege.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class BTScheduleFragment extends Fragment {

    private final String LOG_TAG = "MyCollegeApp";
    private String url = "http://78.46.123.237:7777";
    private Retrofit client;
    private TableLayout mTableLayout;
    private BellTime[] mBellTime;
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient httpClient = builder.readTimeout(4, TimeUnit.SECONDS).writeTimeout(4,TimeUnit.SECONDS).connectTimeout(4,TimeUnit.SECONDS).build();
        client = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create()).baseUrl(url).client(httpClient).build();
    }
    public BTScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_btschedule, container, false);
        mTableLayout = (TableLayout) view.findViewById(R.id.tlBellTime);
        loadBellTime();
        return view;
    }


    private void loadBellTime() {
        BellTimeAPI bellTimeAPI = client.create(BellTimeAPI.class);
        Call<BellTime[]> call = bellTimeAPI.getBellTime();
        call.enqueue(new Callback<BellTime[]>() {
            @Override
            public void onResponse(Call<BellTime[]> call, Response<BellTime[]> response) {
                if(response.isSuccessful()) {
                    mTableLayout.removeAllViews();
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View head = inflater.inflate(R.layout.btschedule_item, null);
                    TextView tvHeadNumber = (TextView) head.findViewById(R.id.tvBellTimeNumber);
                    TextView tvHeadStart = (TextView) head.findViewById(R.id.tvBellTimeStart);
                    TextView tvHeadEnd = (TextView) head.findViewById(R.id.tvBellTimeEnd);

                    tvHeadNumber.setText("№");
                    tvHeadStart.setText("Начало пары");
                    tvHeadEnd.setText("Конец пары");

                    mTableLayout.addView(head);

                    for (BellTime belltime :response.body()) {
                        View view = inflater.inflate(R.layout.btschedule_item, null);
                        TextView tvNumber = (TextView) view.findViewById(R.id.tvBellTimeNumber);
                        TextView tvStart = (TextView) view.findViewById(R.id.tvBellTimeStart);
                        TextView tvEnd = (TextView) view.findViewById(R.id.tvBellTimeEnd);

                        tvNumber.setText(belltime.getId().toString());
                        tvStart.setText(belltime.getStartTime());
                        tvEnd.setText(belltime.getEndTime());
                        mTableLayout.addView(view);
                    }
                }
            }

            @Override
            public void onFailure(Call<BellTime[]> call, Throwable t) {

            }
        });
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
            case R.id.action_refresh: loadBellTime();
        }
        return super.onOptionsItemSelected(item);
    }

}
