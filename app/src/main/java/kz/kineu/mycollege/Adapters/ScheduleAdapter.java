package kz.kineu.mycollege.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kz.kineu.mycollege.Entities.Schedule;
import kz.kineu.mycollege.Fragments.SchedulePageFragment;

/**
 * Created by ruslan on 22.02.2017.
 */

public class ScheduleAdapter extends FragmentPagerAdapter {

    Schedule mSchedule;

    public ScheduleAdapter(FragmentManager fm, Schedule schedule) {
        super(fm);
        this.mSchedule = schedule;
    }

    @Override
    public Fragment getItem(int position) {
        return SchedulePageFragment.newInstance(position,mSchedule);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Понедельник";
            case 1: return "Вторник";
            case 2: return "Среда";
            case 3: return "Четверг";
            case 4: return "Пятница";
        }
        return super.getPageTitle(position);
    }
}
