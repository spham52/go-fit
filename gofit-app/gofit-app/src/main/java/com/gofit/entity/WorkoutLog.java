package com.gofit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_log")
public class WorkoutLog {

    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_log_id")
    private ActivityLog activityLog;

    @Column(name = "reps")
    private int reps;

    @Column(name = "sets")
    private int sets;

    @Column(name = "log_time")
    private String time;

    @Column(name = "notes")
    private String notes;

    public WorkoutLog() {
    }

    public WorkoutLog(ActivityLog activityLog, String time) {
        this.activityLog = activityLog;
        this.time = time;
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(ActivityLog activityLog) {
        this.activityLog = activityLog;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "WorkoutLog{" +
                "activityLog=" + activityLog +
                ", reps=" + reps +
                ", sets=" + sets +
                ", time='" + time + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
