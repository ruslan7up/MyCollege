package kz.kineu.mycollege.Entities;

/**
 * Created by ruslan on 21.02.2017.
 */

public class BellTime {
    private Long id;
    private String startTime;
    private String endTime;

    public BellTime() {
    }

    public BellTime(Long id, String startTime, String endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
