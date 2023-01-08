package org.example;

import Schedule.AnchorPaneNode;
import Schedule.Calendar;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        //-- Initialize components
        AppComponents init = new AppComponents();
        AnchorPaneNode an = new AnchorPaneNode();
        init.setTextfields();
        init.setButtons();
        init.setLabels();
        init.setDatePickers();
        init.setColumns();
        init.setTable();


        //-- Webhook manager --
        WebhookManager webhookManager = new WebhookManager();

        //-- testsaker för backend --
        String canvasTestCreateEventString = "{\"calendar_event\": {\"context_code\":\"user_98107\",\"title\":\"api test\",\"start_at\":\"2022-12-18T10:15:00Z\",\"end_at\":\"2022-12-18T11:45:00Z\"}}";
        String userIDBjorn = "98107";
        String startDate = "2022-11-16T16:00:00Z";
        String endDate = "2023-01-16T16:00:00Z";


        init.getGetScheduleButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String getCanvasRes = null;
                String postCanvasRes = null;
                String getTimeEditRes = null; //Ska populeras in i tabellen vid. getCanvas/postCanvas lämnas kvar som "dokumentation" på hur de används.

                try {
                    getCanvasRes = webhookManager.getCanvas(userIDBjorn, startDate, endDate);
                    postCanvasRes = webhookManager.postCanvas(canvasTestCreateEventString);
                    getTimeEditRes = webhookManager.getTimeEdit("");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(getCanvasRes);
                System.out.println();
                System.out.println(postCanvasRes);
                System.out.println();
                System.out.println(getTimeEditRes);
                System.out.println();
                System.out.println(webhookManager.jsonToCalendarEntryList(getTimeEditRes));
                System.out.println();

                ObservableList<CalendarEntry> data = webhookManager.jsonToCalendarEntryList(getTimeEditRes);
                init.getTable().setItems(data);
            }
        });

        init.getSetScheduleButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //kolla ifall något fält i tabellen är tomt, låt ej lärare skicka in om något fält är tomt
                System.out.println("SetSchedule");
                ObservableList<CalendarEntry> schedule = init.getTable().getItems();
                for (CalendarEntry ce : schedule) {
                    System.out.println(ce.toString());
                    System.out.println();
                    try {
                        System.out.println(ce.toJson().toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });




        //Panes
        //top - header
        HBox header = new HBox();
        header.setPrefSize(1920, 100);
        header.setId("header");

        //top of root
        HBox bottomRoot = new HBox();
        bottomRoot.setPrefSize(1920, 40);
        bottomRoot.setSpacing(30);
        bottomRoot.setAlignment(Pos.CENTER_RIGHT);
        //bottomRoot.getChildren().add(init.getDateLabel());
        //bottomRoot.getChildren().add(init.getDateTextField());
        //bottomRoot.getChildren().add(init.getCommentLabel());
        //bottomRoot.getChildren().add(init.getCommentTextField());
        bottomRoot.getChildren().add(init.getSetScheduleButton());


        VBox rootCenter = new VBox();
        rootCenter.setSpacing(5);
        rootCenter.setPadding(new Insets(10, 0, 0, 10));
        rootCenter.setPrefHeight(1000);
        rootCenter.getChildren().add(init.getTable());


        //root - center - schedule
        BorderPane rootSchedule = new BorderPane();
        rootSchedule.setPadding(new Insets(20));
        rootSchedule.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //rootSchedule.setCenter(new Calendar(YearMonth.now()).getView());
        rootSchedule.setCenter(rootCenter);
        rootSchedule.setBottom(bottomRoot);

        //Vbox for getSchedule button
        VBox getScheduleVbox = new VBox();
        getScheduleVbox.setPrefSize(200, 100);
        getScheduleVbox.setAlignment(Pos.BOTTOM_RIGHT);
        getScheduleVbox.setSpacing(15);
        getScheduleVbox.getChildren().add(init.getGetScheduleButton());



        //Left menu
        VBox leftMenu = new VBox();
        leftMenu.setPrefSize(200, 830);
        leftMenu.setMaxSize(200, 1080);
        leftMenu.setAlignment(Pos.TOP_LEFT);
        leftMenu.setSpacing(5);
        leftMenu.setPadding(new Insets(10, 10, 10, 10));
        leftMenu.setId("left-menu");
        // -- add buttons etc --
        leftMenu.getChildren().addAll(init.getIdLabel(), init.getIdTextField(), init.getStartDateLabel(), init.getStartDatePicker()
                , init.getEndDateLabel(), init.getEndDatePicker(), init.getPlaceLabel(), init.getPlaceTextField(), init.getCourseCodeLabel(),
                init.getCourseCodeTextField(), init.getTeacherLabel(), init.getTeacherTextField(), getScheduleVbox);

        //bottom - footer









        //main
        BorderPane mainStage = new BorderPane();
        mainStage.setMinSize(1280, 830);
        mainStage.setPrefSize(1280, 830);
        mainStage.setMaxSize(1920, 1080);
        mainStage.setLeft(leftMenu);
        mainStage.setTop(header);
        mainStage.setCenter(rootSchedule);


        mainStage.setId("main-stage");

        //-- SET SCENE --
        Scene scene = new Scene(mainStage, 1280, 830);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(1080);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMinHeight(830);
        primaryStage.setMinWidth(1280);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Schemaprogram");
        ResizeHelper.addResizeListener(primaryStage);
        primaryStage.show();



    }

    public ObservableList<ScheduleData> populateTable() {
       ObservableList<ScheduleData> oa =  FXCollections.observableArrayList(
                new ScheduleData("D0023E", "08:15", "2023-01-10", "Zoom-länk: www.zoom.com/testtest"),
                new ScheduleData("D0023E", "08:15", "2023-01-14", "Zoom-länk: www.zoom.com/testtest")
        );

        return oa;

    }

    public static void main(String[] args) {
        launch();
    }

}