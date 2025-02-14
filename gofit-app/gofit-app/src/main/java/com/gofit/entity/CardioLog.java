package com.gofit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


// this class is designed for information about cardiovascular exercises performed by the user
// it is mapped to an activity log

@Entity
@Table(name = "cardio_log")
public class CardioLog {

    @NotNull
    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_log_id")
    private ActivityLog activityLog;

    @NotNull
    @Column(name = "log_time")
    private LocalDateTime time;

    @Column(name = "minutes")
    private int minutes;

    @Column(name = "seconds")
    private int seconds;

    @Column(name = "distance")
    private int distance;

    @Column(name = "notes")
    private String notes;

    public CardioLog() {
    }

    public CardioLog(ActivityLog activityLog, LocalDateTime time) {
        this.activityLog = activityLog;
        this.time = time;
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(ActivityLog activityLog) {
        this.activityLog = activityLog;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CardioLog{" +
                "activityLog=" + activityLog +
                ", time=" + time +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", distance=" + distance +
                ", notes='" + notes + '\'' +
                '}';
    }
}
