package com.gofit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="activities")
public class Activities {

    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="activity_id")
    private int activity_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,
                                                  CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @NotEmpty(message = "Activity name must not be empty.")
    @Column(name="activity_name")
    private String activityName;

    @NotNull
    @NotEmpty(message = "Activity type must not be empty.")
    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "category")
    private String category;

    @Column(name = "is_custom")
    @NotNull
    @NotEmpty(message = "isCustom must be inputted.")
    private boolean isCustom = false;

    public Activities() {}

    public Activities(User user, String activityName, String activityType, String category, boolean isCustom) {
        this.user = user;
        this.activityName = activityName;
        this.activityType = activityType;
        this.category = category;
        this.isCustom = isCustom;
    }

    public Activities(String activityName, String activityType, String category, boolean isCustom) {
        this.activityName = activityName;
        this.activityType = activityType;
        this.category = category;
        this.isCustom = isCustom;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    @Override
    public String toString() {
        return "Activities{" +
                "activityName='" + activityName + '\'' +
                ", activityType='" + activityType + '\'' +
                ", category='" + category + '\'' +
                ", isCustom=" + isCustom +
                ", activity_id=" + activity_id +
                ", user=" + user +
                '}';
    }
}
