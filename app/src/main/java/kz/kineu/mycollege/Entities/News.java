package kz.kineu.mycollege.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ruslan on 10.02.2017.
 */

public class News implements Parcelable{

    private Long id;
    private String title;
    private Date date;
    private String text;
    private ArrayList<Link> links;

    public News() {
    }

    public News(Long id, String title, Date date, String text, ArrayList<Link> links) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.text = text;
        this.links = links;
    }


    private News(Parcel parcel) {
        id = parcel.readLong();
        title = parcel.readString();
        date = new Date(parcel.readLong());
        text = parcel.readString();
        links = new ArrayList<Link>();
        parcel.readTypedList(links, Link.CREATOR);
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeLong(date.getTime());
        dest.writeString(text);
        dest.writeTypedList(links);
    }


}
