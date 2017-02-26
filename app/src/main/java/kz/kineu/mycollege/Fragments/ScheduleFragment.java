package kz.kineu.mycollege.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import kz.kineu.mycollege.Adapters.ScheduleAdapter;
import kz.kineu.mycollege.Entities.Schedule;
import kz.kineu.mycollege.R;


public class ScheduleFragment extends Fragment {


    private ViewPager mViewPager;
    private TextView info;

    public ScheduleFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vpSchedule);
        ScheduleAdapter adapter = new ScheduleAdapter(getActivity().getSupportFragmentManager(),(Schedule) getActivity().getIntent().getParcelableExtra("schedule"));
        viewPager.setAdapter(adapter);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY: viewPager.setCurrentItem(1); break;
            case Calendar.TUESDAY: viewPager.setCurrentItem(2); break;
            case Calendar.WEDNESDAY: viewPager.setCurrentItem(3); break;
            case Calendar.THURSDAY: viewPager.setCurrentItem(4); break;
            case Calendar.FRIDAY: viewPager.setCurrentItem(5); break;
        }
        info = (TextView) view.findViewById(R.id.tvScheduleInfo);
        Schedule schedule = getActivity().getIntent().getParcelableExtra("schedule");
        info.setText("Группа: "+schedule.getName()+" Cмена: "+schedule.getShift());
        return view;
    }




}
