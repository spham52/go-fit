package com.gofit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session")
public class Session {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int sessionID;

    @NotBlank
    @Column(name = "session_name")
    private String sessionName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "log_time")
    private LocalDateTime time;

    // each session holds multiple activityLogs
    // activityLogs are exercises/workouts recorded by the user in a single session
    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    List<ActivityLog> activityLog;

    public Session() {
    }

    public Session(String sessionName, User user) {
        this.sessionName = sessionName;
        this.user = user;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<ActivityLog> getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(List<ActivityLog> activityLog) {
        this.activityLog = activityLog;
    }

    @Override
    public String toString() {
        return "Session{" +
                "time='" + time + '\'' +
                ", sessionID=" + sessionID +
                '}';
    }
}
