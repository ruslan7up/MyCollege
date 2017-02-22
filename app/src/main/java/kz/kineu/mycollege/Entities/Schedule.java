package kz.kineu.mycollege.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslan on 22.02.2017.
 */

public class Schedule implements Parcelable {

    private Long id;

    private String name;
    private int shift;

    private List<Class> monday;
    private List<Class> tuesday;
    private List<Class> wednesday;
    private List<Class> thursday;
    private List<Class> friday;



    public Schedule() {
    }


    public Schedule(String name, int shift, List<Class> monday, List<Class> tuesday, List<Class> wednesday, List<Class> thursday, List<Class> friday) {
        this.name = name;
        this.shift = shift;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    protected Schedule(Parcel in) {
        name = in.readString();
        shift = in.readInt();
        monday = new ArrayList<>();
        tuesday = new ArrayList<>();
        wednesday = new ArrayList<>();
        thursday = new ArrayList<>();
        friday = new ArrayList<>();

        in.readTypedList(monday, Class.CREATOR);
        in.readTypedList(tuesday, Class.CREATOR);
        in.readTypedList(wednesday, Class.CREATOR);
        in.readTypedList(thursday, Class.CREATOR);
        in.readTypedList(friday, Class.CREATOR);
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public List<Class> getMonday() {
        return monday;
    }

    public void setMonday(List<Class> monday) {
        this.monday = monday;
    }

    public List<Class> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<Class> tuesday) {
        this.tuesday = tuesday;
    }

    public List<Class> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<Class> wednesday) {
        this.wednesday = wednesday;
    }

    public List<Class> getThursday() {
        return thursday;
    }

    public void setThursday(List<Class> thursday) {
        this.thursday = thursday;
    }

    public List<Class> getFriday() {
        return friday;
    }

    public void setFriday(List<Class> friday) {
        this.friday = friday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(shift);
        dest.writeTypedList(monday);
        dest.writeTypedList(tuesday);
        dest.writeTypedList(wednesday);
        dest.writeTypedList(thursday);
        dest.writeTypedList(friday);
    }
}
