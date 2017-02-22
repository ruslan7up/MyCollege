package kz.kineu.mycollege.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ruslan on 22.02.2017.
 */

public class Class implements Parcelable {

    private Long id;


    private String nameofasubject;
    private String cabinetnumber;

    public Class() {
    }

    public Class(String nameofasubject, String cabinetnumber) {
        this.nameofasubject = nameofasubject;
        this.cabinetnumber = cabinetnumber;
    }


    protected Class(Parcel in) {
        nameofasubject = in.readString();
        cabinetnumber = in.readString();
    }

    public static final Creator<Class> CREATOR = new Creator<Class>() {
        @Override
        public Class createFromParcel(Parcel in) {
            return new Class(in);
        }

        @Override
        public Class[] newArray(int size) {
            return new Class[size];
        }
    };

    public String getNameofasubject() {
        return nameofasubject;
    }

    public void setNameofasubject(String nameofasubject) {
        this.nameofasubject = nameofasubject;
    }

    public String getCabinetnumber() {
        return cabinetnumber;
    }

    public void setCabinetnumber(String cabinetnumber) {
        this.cabinetnumber = cabinetnumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameofasubject);
        dest.writeString(cabinetnumber);
    }
}
