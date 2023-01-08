package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalendarEntry {
    //Initiated with data from TimeEdit
    String id;
    String startDate;

    String endDate;
    String startTime;
    String endTime;

    String location;
    String courseCode;
    String activityType;
    String teacher;


    //Initiated by user
    String zoomLink;
    String activityContent;
    String title;
    String description;

    public CalendarEntry(String id, String startDate, String endDate, String startTime, String endTime, String location, String courseCode, String activityType, String teacher) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.courseCode = courseCode;
        this.activityType = activityType;
        this.teacher = teacher;
    }

    public CalendarEntry(JSONObject content) throws JSONException {
        JSONArray columns = (JSONArray) content.get("columns");

        this.id = content.get("id").toString();
        this.startDate = content.get("startdate").toString();
        this.endDate = content.get("enddate").toString();
        this.startTime = content.get("starttime").toString();
        this.endTime = content.get("endtime").toString();
        this.location = columns.get(2).toString();
        this.courseCode = columns.get(7).toString();
        this.activityType = columns.get(1).toString();
        this.teacher = columns.get(3).toString();
    }


    public String toString() {
        return  "ID = " + id +
                "\nStartDate = " + startDate +
                "\nEndDate = " + endDate +
                "\nStartTime = " + startTime +
                "\nEndTime = " + endTime +
                "\nLocation = " + location +
                "\nCourseCode = " + courseCode +
                "\nActivityType = " + activityType +
                "\nTeacher = " + teacher +
                "\nzoomLink = " + zoomLink +
                "\nactivityContent = " + activityContent +
                "\ntitle = " + title +
                "\ndescription = " + description;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonFull = new JSONObject();
        JSONObject json = new JSONObject();
        json.append("title", title);
        json.append("ID", id);
        json.append("StartDate", startDate);
        json.append("EndDate", endDate);
        json.append("StartTime", startTime);
        json.append("EndTime", endTime);
        json.append("Location", location);
        json.append("CourseCode", courseCode);
        json.append("ActivityType", activityType);
        json.append("Teacher", teacher);
        json.append("description", description + " " + zoomLink);
        json.append("activityContent", activityContent);
        json.append("title", title);
        json.append("context_code", "user_98107");

        jsonFull.append("calendar_event",json);

        return jsonFull;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
