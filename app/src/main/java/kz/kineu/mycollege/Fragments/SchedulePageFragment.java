package kz.kineu.mycollege.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import kz.kineu.mycollege.Entities.Class;
import kz.kineu.mycollege.Entities.Schedule;
import kz.kineu.mycollege.R;



public class SchedulePageFragment extends Fragment {


    private Schedule mClassList;
    private TableLayout mTableLayout;
    private Integer day;
    private Schedule schedule;

    public static SchedulePageFragment newInstance(Integer position,Schedule schedule) {
        SchedulePageFragment fragment = new SchedulePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("day", position);
        bundle.putParcelable("schedule", schedule);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            schedule = getArguments().getParcelable("schedule");
            day = getArguments().getInt("day");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_page, container, false);
        mTableLayout = (TableLayout) view.findViewById(R.id.lvClasses);
            Log.d("MyCollegeApp","CALLED");
            switch (day) {
                case 0: loadSchedule(schedule.getMonday()); break;
                case 1: loadSchedule(schedule.getTuesday()); break;
                case 2: loadSchedule(schedule.getWednesday()); break;
                case 3: loadSchedule(schedule.getThursday()); break;
                case 4: loadSchedule(schedule.getFriday()); break;
            }
        return view;
    }

    private void loadSchedule(List<Class> classes) {
        if(classes!=null) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            int startindex = 1;
            if(schedule.getShift()==2)
            {
                startindex = 4;
                if(classes.size()==4) {
                    startindex = 3;
                }
            }
            for (int i = 0; i < classes.size() ; i++) {
                View view = inflater.inflate(R.layout.schedule_item, null);
                TextView tvClassNumber = (TextView) view.findViewById(R.id.tvClassNumber);
                TextView tvClassName = (TextView) view.findViewById(R.id.tvClassName);
                TextView tvClassCabinet = (TextView) view.findViewById(R.id.tvClassCabinet);
                tvClassNumber.setText(Integer.toString(startindex++));
                tvClassName.setText(classes.get(i).getNameofasubject());
                tvClassCabinet.setText(classes.get(i).getCabinetnumber());
                mTableLayout.addView(view);
            }
        }
    }
}
