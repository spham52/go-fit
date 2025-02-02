package com.gofit.dto;

public class ActivityLogDTO {
    private int activityID;

    public ActivityLogDTO(int activityID) {
        this.activityID = activityID;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }
}
