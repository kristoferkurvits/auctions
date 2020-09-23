package com.Kurvits.bacchusback.Auction;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Auction {

    private static String apiEndpoint = "http://uptime-auction-api.azurewebsites.net/api/Auction";

    public static JSONArray fetchAuctions() throws IOException, ParseException {

        URL url = new URL(apiEndpoint);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();

        JSONArray jsonArray = null;

        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            Scanner sc = new Scanner(url.openStream());
            String inline = "";
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }

            sc.close();

            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(inline);
        }

        return jsonArray;

    }

}
