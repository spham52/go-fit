package com.gofit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "activity_log")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_log_id")
    private int activityLogID;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                                                  CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "session_id")
    private Session session;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                                                  CascadeType.REFRESH, CascadeType.DETACH})
    private Activities activities;

    public ActivityLog() {}

    public ActivityLog(Session session, Activities activities) {
        this.session = session;
        this.activities = activities;
    }

    public int getActivityLogId() {
        return activityLogID;
    }

    public void setActivityLogId(int activityLogId) {
        this.activityLogID = activityLogId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "activityLogId=" + activityLogID +
                ", session=" + session +
                ", activities=" + activities +
                '}';
    }
}
