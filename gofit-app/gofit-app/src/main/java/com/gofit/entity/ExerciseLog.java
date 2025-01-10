package com.gofit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise_log")
public class ExerciseLog {

    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "activityLogID")
    private ActivityLog activityLog;

    @Column(name = "log_time")
    private String time;

    @Column(name = "minutes")
    private int minutes;

    @Column(name = "seconds")
    private int seconds;

    @Column(name = "distance")
    private int distance;

    @Column(name = "notes")
    private String notes;

    public ExerciseLog() {
    }

    public ExerciseLog(ActivityLog activityLog, String time) {
        this.activityLog = activityLog;
        this.time = time;
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(ActivityLog activityLog) {
        this.activityLog = activityLog;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
        return "ExerciseLog{" +
                "activityLog=" + activityLog +
                ", time='" + time + '\'' +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", distance=" + distance +
                ", notes='" + notes + '\'' +
                '}';
    }
}
