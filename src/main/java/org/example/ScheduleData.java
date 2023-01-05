package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableArray;

public class ScheduleData {

    SimpleStringProperty courseCode;
    SimpleStringProperty time;
    SimpleStringProperty date;
    SimpleStringProperty comment;

    public ScheduleData(String courseCode, String time, String date, String comment) {
        this.courseCode = new SimpleStringProperty(courseCode);
        this.time = new SimpleStringProperty(time);
        this.date = new SimpleStringProperty(date);
        this.comment = new SimpleStringProperty(comment);

    }


    public String getCourseCode() {
        return courseCode.get();
    }

    public SimpleStringProperty courseCodeProperty() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode.set(courseCode);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
}
