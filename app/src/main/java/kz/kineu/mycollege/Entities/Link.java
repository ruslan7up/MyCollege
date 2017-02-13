package kz.kineu.mycollege.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ruslan on 10.02.2017.
 */

public class Link implements Parcelable {
    private Long id;
    private String link;

    public Link() {
    }

    public Link(Long id, String link) {
        this.id = id;
        this.link = link;
    }

    protected Link(Parcel in) {
        id = in.readLong();
        link = in.readString();
    }

    public static final Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(link);
    }
}
