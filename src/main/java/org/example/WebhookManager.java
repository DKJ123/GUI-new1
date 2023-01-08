package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebhookManager {

    String requestType;
    String body;
    String url;
    String query;
    String authorizationToken;

    String lastResponse;

    String canvasUrl = "https://ltu.instructure.com/api/v1/";
    String canvasToken = "3755~buPUF77WBixkcXA6X5yfMnnCGOiPwOtPQW4a8Vw2i96ihLZoaWy23wIEZsTXhrL7";

    String testUrl = "https://webhook.site/55fe39a4-02dc-4cb0-801d-a156ab01b970";

    String timeEditUrl = "https://cloud.timeedit.net/ltu/web/schedule1/ri105656X45Z0XQ6Z36g1Y40y3036Y32107gQY6Q547520876YQ837.json";


    public String getCanvas(String userId, String startDate, String endDate) throws IOException {
        requestType = "GET";
        body = "";
        url = canvasUrl + "users/" + userId + "/calendar_events";
        query = "start_date=" + startDate + "&end_date=" + endDate;
        authorizationToken = canvasToken;
        return sendRequest(requestType, body, url, query, authorizationToken);
    }

    public String postCanvas(String content) throws IOException {
        requestType = "POST";
        body = content;
        url = canvasUrl + "calendar_events";
        query = "";
        authorizationToken = canvasToken;
        return sendRequest(requestType, body, url, query, authorizationToken);
    }

    public String getTimeEdit(String content) throws IOException {
        requestType = "GET";
        body = "";
        url = timeEditUrl;
        query = "";
        authorizationToken = "";
        return sendRequest(requestType, body, url, query, authorizationToken);
    }

    public String repeatLastRequest() throws IOException {
        return sendRequest(requestType, body, url, query, authorizationToken);
    }


    private String sendRequest(String requestType, String body, String url, String query, String authorizationToken) throws IOException {

        if (query != "") {
            url = url + "?" + query;
        }

        HttpURLConnection connection = createConnection(url, requestType, authorizationToken);

        if (requestType == "POST") {
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        int responseCode = connection.getResponseCode();
        System.out.println(requestType +  " Response code :: " + responseCode);

        if ((responseCode == HttpURLConnection.HTTP_OK) || (responseCode == HttpURLConnection.HTTP_CREATED)) {
            return readResponse(connection);
        } else {
            System.out.println(requestType + " request failed");
            return null;
        }
    }


    private HttpURLConnection createConnection(String url, String requestType, String authorizationToken) throws IOException {
        URL destinationUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)destinationUrl.openConnection();

        connection.setRequestMethod(requestType);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        if (authorizationToken != "") {
            connection.setRequestProperty("Authorization", "Bearer " + authorizationToken);
        }
        connection.setDoOutput(true);

        System.out.println("connection.getRequestMethod() = " + connection.getRequestMethod());
        System.out.println("DestinationUrl = " + destinationUrl);
        System.out.println("Authorizaitontoken = " + authorizationToken);
        System.out.println("RequestType = " + requestType);

        return connection;
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        lastResponse = response.toString();
        return lastResponse;
    }

    public void testRunWebhook() throws IOException {
        //Används för att göra calls till TimeEdit / Canvas
        WebhookManager webhookManager = new WebhookManager();

        String canvasTestCreateEventString = "{\"calendar_event\": {\"context_code\":\"user_98107\",\"title\":\"api test\",\"start_at\":\"2022-12-18T10:15:00Z\",\"end_at\":\"2022-12-18T11:45:00Z\"}}";
        String userIDBjorn = "98107";
        String startDate = "2022-11-16T16:00:00Z";
        String endDate = "2023-01-16T16:00:00Z";

        String getCanvasRes = webhookManager.getCanvas(userIDBjorn, startDate, endDate);
        String postCanvasRes = webhookManager.postCanvas(canvasTestCreateEventString);
        String getTimeEditRes = webhookManager.getTimeEdit("");
        System.out.println(getCanvasRes);
        System.out.println();
        System.out.println(postCanvasRes);
        System.out.println();
        System.out.println(getTimeEditRes);
        System.out.println();
        System.out.println(jsonToCalendarEntryList(getTimeEditRes));
        System.out.println();
    }


    public static ObservableList<CalendarEntry> jsonToCalendarEntryList(String jsonContent) {
        ObservableList<CalendarEntry> calendarEntries = FXCollections.observableArrayList();
        try {
            JSONObject jsonObject = new JSONObject(jsonContent);
            JSONObject info = (JSONObject) jsonObject.get("info");
            int reservationCount = Integer.parseInt(info.get("reservationcount").toString());
            JSONArray reservations = (JSONArray) jsonObject.get("reservations");

            // <Print shit för test>
            System.out.println(reservations);
            System.out.println(1);
            System.out.println(reservations);
            System.out.println(2);
            for(int i=0; i <reservations.length(); i++) {
                System.out.println(reservations.get(i));
            }
            System.out.println(3);
            // </Print shit för test>

            for(int i=0; i <reservations.length(); i++) {
                System.out.println(reservations.get(i));
                CalendarEntry calendarEntry = new CalendarEntry((JSONObject)reservations.get(i));
                System.out.println(calendarEntry.toString());
                calendarEntries.add(calendarEntry);
                System.out.println();
                System.out.println();
                System.out.println();
            }



        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return calendarEntries;
    }

}
