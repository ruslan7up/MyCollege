package kz.kineu.mycollege.Entities;

import java.util.Date;

/**
 * Created by ruslan on 25.05.2017.
 */

public class Substitution {

    private long Id;


    private Date date;
    private String groupName;
    private String lesson1;
    private String lesson2;

    public Substitution() {
    }

    public Substitution(long id, Date date, String groupName, String lesson1, String lesson2) {
        Id = id;
        this.date = date;
        this.groupName = groupName;
        this.lesson1 = lesson1;
        this.lesson2 = lesson2;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLesson1() {
        return lesson1;
    }

    public void setLesson1(String lesson1) {
        this.lesson1 = lesson1;
    }

    public String getLesson2() {
        return lesson2;
    }

    public void setLesson2(String lesson2) {
        this.lesson2 = lesson2;
    }
}
