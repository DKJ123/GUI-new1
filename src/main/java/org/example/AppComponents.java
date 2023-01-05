package org.example;

import Schedule.AnchorPaneNode;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppComponents extends App {

    //-- Stages --


    //-- Buttons --
    Button getScheduleButton;
    Button setScheduleButton;

    //-- Textfields --
    TextField commentTextField;
    TextField dateTextField;

    //-- Labels --
    Label dateLabel;
    Label commentLabel;


    //-- Table --
    TableView table;

    // -- TableColumns --
    TableColumn IdColumn;

    TableColumn startDateColumn;

    TableColumn endDateColumn;

    TableColumn startTimeColumn;

    TableColumn endTimeColumn;

    TableColumn locationColumn;
    TableColumn courseCodeColumn;

    TableColumn activityColumn;

    TableColumn teacherColumn;
    TableColumn timeColumn;
    TableColumn dateColumn;
    TableColumn commentColumn;


    //buttons setters and getters

    public void setButtons(){

        //setScheduleButton
        setScheduleButton = new Button("Set Schedule");
        setScheduleButton.setPrefSize(100, 30);
        setScheduleButton.setCursor(Cursor.HAND);

        setScheduleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Skicka schema till Canvas
            }
        });

        //getScheduleButton
        getScheduleButton = new Button("Get Schedule");
        getScheduleButton.setPrefSize(100, 30);
        getScheduleButton.setCursor(Cursor.HAND);

        getScheduleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Hämta schema från TimeEdit och visa i applikation
            }
        });
    }

    public Button getSetScheduleButton() {
        return setScheduleButton;
    }

    public Button getGetScheduleButton() {
        return getScheduleButton;
    }


    //textFields setters and getters

    public void setTextfields() {
        commentTextField = new TextField();
        commentTextField.setPrefSize(400, 30);

        dateTextField = new TextField();
        dateTextField.setPrefSize(100, 30);
        dateTextField.setEditable(false);
    }



    public TextField getCommentTextField() {
        return commentTextField;
    }

    public TextField getDateTextField() {
        return dateTextField;
    }




    //-- Labels setters and getters --

    public void setLabels() {
        dateLabel = new Label();
        dateLabel.setText("Date:");

        commentLabel = new Label();
        commentLabel.setText("Comment:");
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getCommentLabel() {
        return commentLabel;
    }


    // -- table setter and getter --

    public void setTable() {
        table = new TableView<ScheduleData>();
        table.setEditable(true);
        table.setPrefHeight(1000);
        table.getColumns().addAll(getIdColumn(), getStartDateColumn(), getEndDateColumn(), getStartTimeColumn(), getEndTimeColumn(),
                getLocationColumn(), getCourseCodeColumn(), getActivityColumn(), getTeacherColumn(), getCommentColumn());
    }

    public TableView getTable() {
        return table;
    }

    public void setColumns() {

        // -- IdColumn --
        IdColumn = new TableColumn("ID");
        IdColumn.setPrefWidth(70);
        IdColumn.setEditable(true);
        IdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        IdColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("comment"));

        //-- startDateColumn --
        startDateColumn = new TableColumn("Start-datum");
        startDateColumn.setPrefWidth(80);
        startDateColumn.setEditable(true);
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startDateColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("comment"));

        //-- endDateColumn --
        endDateColumn = new TableColumn("Slut-datum");
        endDateColumn.setPrefWidth(80);
        endDateColumn.setEditable(true);
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        endDateColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("comment"));

        //-- startTimeColumn --
        startTimeColumn = new TableColumn("Start-tid");
        startTimeColumn.setPrefWidth(60);
        startTimeColumn.setEditable(true);
        startTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("comment"));

        //-- endTimeColumn --
        endTimeColumn = new TableColumn("Slut-tid");
        endTimeColumn.setPrefWidth(60);
        endTimeColumn.setEditable(true);
        endTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("comment"));

        //-- locationColumn --
        locationColumn = new TableColumn("Plats");
        locationColumn.setPrefWidth(100);
        locationColumn.setEditable(true);
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        locationColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("comment"));


        // -- courseCodeColumn --
        courseCodeColumn = new TableColumn("Kurs-kod");
        courseCodeColumn.setPrefWidth(100);
        courseCodeColumn.setEditable(true);
        courseCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("courseCode"));
        courseCodeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                Object oldVal = cellEditEvent.getOldValue();
                ObservableList<ScheduleData> list = courseCodeColumn.getTableView().getItems();
                ScheduleData data = (ScheduleData) courseCodeColumn.getTableView().getSelectionModel().getSelectedItem();

                Object ob = cellEditEvent.getNewValue();
                System.out.print(ob);
                String value = ob.toString();
                int strLen = value.length();

                if (strLen > 6) {
                    // Create error dialog and show it
                    Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    VBox dialogBox = new VBox(20);
                    dialogBox.getChildren().add(new Text("ERROR"));
                    Scene dialogScene = new Scene(dialogBox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
                    data.setCourseCode(oldVal.toString());
                } else {
                    data.setCourseCode(value);
                }

            }
        });

        //-- activityColumn --
        activityColumn = new TableColumn("Aktivitet");
        activityColumn.setPrefWidth(200);
        activityColumn.setEditable(true);
        activityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        activityColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("time"));

        //-- teacherColumn --
        teacherColumn = new TableColumn("Lärare");
        teacherColumn.setPrefWidth(200);
        teacherColumn.setEditable(true);
        teacherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        teacherColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("time"));


        commentColumn = new TableColumn("Kommentar");
        commentColumn.setPrefWidth(700);
        commentColumn.setEditable(true);
        commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        commentColumn.setCellValueFactory(new PropertyValueFactory<ScheduleData, String>("comment"));
    }


    public TableColumn getCourseCodeColumn() {
        return courseCodeColumn;
    }

    public TableColumn getCommentColumn() {
        return commentColumn;
    }

    public TableColumn getIdColumn() {
        return IdColumn;
    }

    public void setIdColumn(TableColumn idColumn) {
        IdColumn = idColumn;
    }

    public TableColumn getStartDateColumn() {
        return startDateColumn;
    }

    public void setStartDateColumn(TableColumn startDateColumn) {
        this.startDateColumn = startDateColumn;
    }

    public TableColumn getEndDateColumn() {
        return endDateColumn;
    }

    public void setEndDateColumn(TableColumn endDateColumn) {
        this.endDateColumn = endDateColumn;
        // jdjd
    }

    public TableColumn getStartTimeColumn() {
        return startTimeColumn;
    }

    public void setStartTimeColumn(TableColumn startTimeColumn) {
        this.startTimeColumn = startTimeColumn;
    }

    public TableColumn getEndTimeColumn() {
        return endTimeColumn;
    }

    public void setEndTimeColumn(TableColumn endTimeColumn) {
        this.endTimeColumn = endTimeColumn;
    }

    public TableColumn getLocationColumn() {
        return locationColumn;
    }

    public void setLocationColumn(TableColumn locationColumn) {
        this.locationColumn = locationColumn;
    }

    public TableColumn getActivityColumn() {
        return activityColumn;
    }

    public void setActivityColumn(TableColumn activityColumn) {
        this.activityColumn = activityColumn;
    }

    public TableColumn getTeacherColumn() {
        return teacherColumn;
    }

    public void setTeacherColumn(TableColumn teacherColumn) {
        this.teacherColumn = teacherColumn;
    }
}
