package kz.kineu.mycollege.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import kz.kineu.mycollege.API.BellTimeAPI;
import kz.kineu.mycollege.API.SubstitutionAPI;
import kz.kineu.mycollege.Entities.BellTime;
import kz.kineu.mycollege.Entities.Substitution;
import kz.kineu.mycollege.R;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static java.util.Collections.addAll;


public class SubsFragment extends Fragment {
    private TableLayout mTableLayout;
    private final String LOG_TAG = "MyCollegeApp";
    private String url = "http://78.46.123.237:7777";
    private Retrofit client;
    public SubsFragment() {
        // Required empty public constructor
    }

    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient httpClient = builder.readTimeout(4, TimeUnit.SECONDS).writeTimeout(4,TimeUnit.SECONDS).connectTimeout(4,TimeUnit.SECONDS).build();
        client = new Retrofit.Builder().addConverterFactory(JacksonConverterFactory.create()).baseUrl(url).client(httpClient).build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subs, container, false);
        mTableLayout = (TableLayout) view.findViewById(R.id.tlLesson);
        Button button = (Button) view.findViewById(R.id.datebtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getFragmentManager(), "datePicker");
            }
        });
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        loadSubs(new Date(cal.getTimeInMillis()));
        System.out.println(cal.getTimeInMillis());
        return view;
    }

    public void loadSubs(Date date) {
        SubstitutionAPI bellTimeAPI = client.create(SubstitutionAPI.class);

        Call<Substitution[]> call = bellTimeAPI.getSchedule(Long.toString(date.getTime()));
        call.enqueue(new Callback<Substitution[]>() {
            @Override
            public void onResponse(Call<Substitution[]> call, Response<Substitution[]> response) {
                if(response.isSuccessful()) {
                    mTableLayout.removeAllViews();
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View head = inflater.inflate(R.layout.btschedule_item, null);
                    TextView tvHeadNumber = (TextView) head.findViewById(R.id.tvBellTimeNumber);
                    TextView tvHeadStart = (TextView) head.findViewById(R.id.tvBellTimeStart);
                    TextView tvHeadEnd = (TextView) head.findViewById(R.id.tvBellTimeEnd);

                    tvHeadNumber.setText("Группа");
                    tvHeadStart.setText("Пара");
                    tvHeadEnd.setText("Замена");

                    mTableLayout.addView(head);

                    for (Substitution substitution :response.body()) {

                        View view = inflater.inflate(R.layout.btschedule_item, null);
                        TextView tvNumber = (TextView) view.findViewById(R.id.tvBellTimeNumber);
                        TextView tvStart = (TextView) view.findViewById(R.id.tvBellTimeStart);
                        TextView tvEnd = (TextView) view.findViewById(R.id.tvBellTimeEnd);

                        tvNumber.setText(substitution.getGroupName());
                        tvStart.setText(substitution.getLesson1());
                        tvEnd.setText(substitution.getLesson2());
                        mTableLayout.addView(view);
                    }
                }
            }

            @Override
            public void onFailure(Call<Substitution[]> call, Throwable t) {

            }
        });
    }

}
