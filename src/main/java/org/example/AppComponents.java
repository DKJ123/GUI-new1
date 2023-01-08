package org.example;

import Schedule.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppComponents extends App {

    //-- Stages --


    //-- Buttons --
    Button getScheduleButton;
    Button setScheduleButton;

    //-- Textfields --
    TextField idTextField;
    TextField placeTextField;

    TextField courseCodeTextField;

    TextField teacherTextField;

    //-- ComboBoxes --
    DatePicker startDatePicker;

    DatePicker endDatePicker;

    //-- Labels --
    Label idLabel;
    Label placeLabel;

    Label startDateLabel;

    Label endDateLabel;

    Label courseCodeLabel;

    Label teacherLabel;


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
        idTextField = new TextField();
        idTextField.setPrefSize(150, 30);

        placeTextField = new TextField();
        placeTextField.setPrefSize(150, 30);

        courseCodeTextField = new TextField();
        courseCodeTextField.setPrefSize(150, 30);

        teacherTextField = new TextField();
        teacherTextField.setPrefSize(150, 30);
    }

    //Comboboxes setter
    public void setDatePickers() {
        startDatePicker = new DatePicker();
        startDatePicker.setPrefSize(150, 30);


        endDatePicker = new DatePicker();
        endDatePicker.setPrefSize(150, 30);
    }



    public TextField getIdTextField() {
        return idTextField;
    }

    public TextField getPlaceTextField() {
        return placeTextField;
    }




    //-- Labels setters and getters --

    public void setLabels() {
        idLabel = new Label();
        idLabel.setText("ID:");
        idLabel.setId("id-label");

        placeLabel = new Label();
        placeLabel.setText("Plats");
        placeLabel.setId("place-label");

        startDateLabel = new Label();
        startDateLabel.setText("Datum from:");
        startDateLabel.setId("start-date-label");

        endDateLabel = new Label();
        endDateLabel.setText("Tom:");
        endDateLabel.setId("end-date-label");

        courseCodeLabel = new Label();
        courseCodeLabel.setText("Kurs-kod:");
        courseCodeLabel.setId("course-code-label");

        teacherLabel = new Label();
        teacherLabel.setText("Lärare:");
        teacherLabel.setId("teacher-label");
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public Label getPlaceLabel() {
        return placeLabel;
    }


    // -- table setter and getter --

    public void setTable() {
        table = new TableView<CalendarEntry>();
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
        IdColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("id"));
        ((TableColumn<CalendarEntry, String>) IdColumn).setOnEditCommit(evt -> evt.getRowValue().setId(evt.getNewValue()));

        //-- startDateColumn --
        startDateColumn = new TableColumn("Start-datum");
        startDateColumn.setPrefWidth(80);
        startDateColumn.setEditable(true);
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startDateColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("startDate"));
        ((TableColumn<CalendarEntry, String>) startDateColumn).setOnEditCommit(evt -> evt.getRowValue().setStartDate(evt.getNewValue()));

        //-- endDateColumn --
        endDateColumn = new TableColumn("Slut-datum");
        endDateColumn.setPrefWidth(80);
        endDateColumn.setEditable(true);
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        endDateColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("endDate"));
        ((TableColumn<CalendarEntry, String>) endDateColumn).setOnEditCommit(evt -> evt.getRowValue().setEndDate(evt.getNewValue()));

        //-- startTimeColumn --
        startTimeColumn = new TableColumn("Start-tid");
        startTimeColumn.setPrefWidth(60);
        startTimeColumn.setEditable(true);
        startTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("startTime"));
        ((TableColumn<CalendarEntry, String>) startTimeColumn).setOnEditCommit(evt -> evt.getRowValue().setStartTime(evt.getNewValue()));

        //-- endTimeColumn --
        endTimeColumn = new TableColumn("Slut-tid");
        endTimeColumn.setPrefWidth(60);
        endTimeColumn.setEditable(true);
        endTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("endTime"));
        ((TableColumn<CalendarEntry, String>) endTimeColumn).setOnEditCommit(evt -> evt.getRowValue().setEndTime(evt.getNewValue()));

        //-- locationColumn --
        locationColumn = new TableColumn("Plats");
        locationColumn.setPrefWidth(100);
        locationColumn.setEditable(true);
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        locationColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("location"));
        ((TableColumn<CalendarEntry, String>) locationColumn).setOnEditCommit(evt -> evt.getRowValue().setLocation(evt.getNewValue()));

        // -- courseCodeColumn --
        courseCodeColumn = new TableColumn("Kurs-kod");
        courseCodeColumn.setPrefWidth(100);
        courseCodeColumn.setEditable(true);
        courseCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("courseCode"));
        ((TableColumn<CalendarEntry, String>) courseCodeColumn).setOnEditCommit(evt -> evt.getRowValue().setCourseCode(evt.getNewValue()));
        courseCodeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                //-- Check for invalid inputs -- // vet inte riktigt hur denna ska fungera
                Object oldVal = cellEditEvent.getOldValue();
                ObservableList<CalendarEntry> list = courseCodeColumn.getTableView().getItems();
                CalendarEntry data = (CalendarEntry) courseCodeColumn.getTableView().getSelectionModel().getSelectedItem();

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
        activityColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("activityType"));
        ((TableColumn<CalendarEntry, String>) activityColumn).setOnEditCommit(evt -> evt.getRowValue().setActivityType(evt.getNewValue()));

        //-- teacherColumn --
        teacherColumn = new TableColumn("Lärare");
        teacherColumn.setPrefWidth(200);
        teacherColumn.setEditable(true);
        teacherColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        teacherColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("teacher"));
        ((TableColumn<CalendarEntry, String>) teacherColumn).setOnEditCommit(evt -> evt.getRowValue().setTeacher(evt.getNewValue()));

        commentColumn = new TableColumn("Kommentar");
        commentColumn.setPrefWidth(700);
        commentColumn.setEditable(true);
        commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        commentColumn.setCellValueFactory(new PropertyValueFactory<CalendarEntry, String>("description"));
        ((TableColumn<CalendarEntry, String>) commentColumn).setOnEditCommit(evt -> evt.getRowValue().setDescription(evt.getNewValue()));

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

    public TextField getCourseCodeTextField() {
        return courseCodeTextField;
    }

    public TextField getTeacherTextField() {
        return teacherTextField;
    }

    public DatePicker getStartDatePicker() {
        return startDatePicker;
    }

    public DatePicker getEndDatePicker() {
        return endDatePicker;
    }

    public Label getStartDateLabel() {
        return startDateLabel;
    }

    public Label getEndDateLabel() {
        return endDateLabel;
    }

    public Label getCourseCodeLabel() {
        return courseCodeLabel;
    }

    public Label getTeacherLabel() {
        return teacherLabel;
    }
}
