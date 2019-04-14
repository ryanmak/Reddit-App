package com.mak.ryan.myapplication.connection;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetJSONDataTask extends AsyncTask<String, Void, String> {

    private ResponseInterface response;

    public GetJSONDataTask(ResponseInterface r) {
        this.response =  r;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return downloadUrl(ConnectionUtils.BASE_URL);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        response.processFinish(result);
    }

    private String downloadUrl(String urlStr) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(ConnectionUtils.READ_TIMEOUT);
            conn.setConnectTimeout(ConnectionUtils.CONNECT_TIMEOUT);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            is = conn.getInputStream();

            return convertStreamToString(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    
    private String convertStreamToString(InputStream is) {
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
}