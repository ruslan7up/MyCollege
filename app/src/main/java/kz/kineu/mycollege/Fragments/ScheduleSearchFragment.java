package kz.kineu.mycollege.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import kz.kineu.mycollege.API.ScheduleAPI;
import kz.kineu.mycollege.Entities.Schedule;
import kz.kineu.mycollege.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ScheduleSearchFragment extends Fragment {

    private Retrofit client;
    private String url = "http://78.46.123.237:7777";
    private TextView tvError;

    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient httpClient = builder.readTimeout(4, TimeUnit.SECONDS).writeTimeout(4,TimeUnit.SECONDS).connectTimeout(4,TimeUnit.SECONDS).build();
        client = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create()).baseUrl(url).client(httpClient).build();
    }
    public ScheduleSearchFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_search, container, false);
        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);
        final EditText editText = (EditText) view.findViewById(R.id.etScheduleGroupName);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvError.setVisibility(View.GONE);
                if(!editText.getText().toString().isEmpty()) {
                    searchSchedule(editText.getText().toString());
                } else {
                    tvError.setText("Введите название группы");
                    tvError.setVisibility(View.VISIBLE);
                }
            }
        });
        tvError = (TextView) view.findViewById(R.id.tvError);
        return view;
    }


    private void searchSchedule(String name) {
        ScheduleAPI API = client.create(ScheduleAPI.class);
        Call<Schedule> call = API.getSchedule(name);
        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if(response.isSuccessful()) {
                    Schedule schedule = response.body();
                    Intent intent = new Intent(getActivity(), kz.kineu.mycollege.Acitivity.FragmentActivity.class);
                    intent.putExtra("schedule",schedule);
                    intent.putExtra("fragment","schedule");
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                tvError.setText("Расписание не найдено");
                tvError.setVisibility(View.VISIBLE);
            }
        });
    }
}
