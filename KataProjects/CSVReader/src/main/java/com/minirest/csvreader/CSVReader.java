package com.minirest.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author gerald barnes
 */
public class CSVReader {

    public static void main(String[] args) {
        String csvFilePath = "C:\\path\\TestCSV.csv";
        String apiUrl = "http://localhost:8080";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Skip the header line
            br.readLine();

            //loop through lines of csv file
            while ((line = br.readLine()) != null) {
                // Split the CSV line into fields
                String[] fields = line.split(",");

                
                String jsonPayload = constructJsonPayload(fields);

                // Send the data to the REST API
                sendPostRequest(apiUrl, jsonPayload);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String constructJsonPayload(String[] fields) {
        // creates json from csv row 
        return String.format("{\"customerRef\":\"%s\", \"customerName\":\"%s\", \"addressLine1\":\"%s\", \"addressLine2\":\"%s\", \"town\":\"%s\", \"county\":\"%s\", \"country\":\"%s\", \"postcode\":\"%s\"}",
                fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]);
    }

    private static void sendPostRequest(String apiUrl, String jsonPayload) {
        try {
            URL url = new URL(apiUrl + "/api/customer");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);


            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
