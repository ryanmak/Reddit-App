package com.mak.ryan.reddit.connection;

import android.util.Log;

import com.mak.ryan.reddit.entities.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataDownloaderThread extends Thread {

    private static final String TAG = DataDownloaderThread.class.getSimpleName();

    private static final int READ_TIMEOUT = 10_000; // ms

    private static final int CONNECT_TIMEOUT = 15_000; // ms

    private static String BASE_URL = "https://www.reddit.com/r/leagueoflegends/.json";

    private String urlAddress;

    private OnDownloadCompleteListener listener;

    public DataDownloaderThread() {
        this.urlAddress = BASE_URL;
    }

    public DataDownloaderThread(String url) {
        this.urlAddress = url;
    }

    @Override
    public void run() {
        if (listener == null) {
            throw new NullPointerException("OnDownloadCompleteListener not set! Call setOnDownloadCompleteListener");
        }

        String data = downloadUrl();
        List<Post> posts = parseData(data);
        listener.onDataDownloadComplete(posts);
    }

    public void setOnDownloadCompleteListener(OnDownloadCompleteListener listener) {
        this.listener = listener;
    }

    private String downloadUrl() {
        InputStream is = null;
        HttpURLConnection conn = null;
        String data = "";

        try {
            URL url = new URL(urlAddress);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            is = conn.getInputStream();
            data = getDataFromStream(is);
        } catch (MalformedURLException e) {
            Log.e(TAG, "URL was incorrect: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Could not connect to URL: " + e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e(TAG, "Could not close stream: " + e.toString());
                }
            }
        }
        return data;
    }

    private String getDataFromStream(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private List<Post> parseData(String output) {
        List<Post> posts = new ArrayList<>();

        try {
            JSONObject page = new JSONObject(output);
            JSONObject data = page.getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < children.length(); i++) {
                JSONObject child = children.getJSONObject(i);
                System.out.println("kind" + child.getString("kind"));
                posts.add(new Post(child));
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON could not be parsed: " + e.toString());
        }

        return posts;
    }
}
