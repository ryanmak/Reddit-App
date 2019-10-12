package com.mak.ryan.reddit.entities;

import com.mak.ryan.reddit.util.TimestampUtility;

import org.json.JSONException;
import org.json.JSONObject;

public class Post {
    private String title;

    private int karma;

    private String timeStamp;

    private String flairText;

    private String username;

    private String subreddit;

    private String url;

    private String selfText;

    public Post(JSONObject jsonData) {
        String image = null;
        try {
            JSONObject jsonPost = jsonData.getJSONObject("data");

            this.title = jsonPost.getString("title");
            this.karma = jsonPost.getInt("score");
            this.timeStamp = TimestampUtility.format(jsonPost.getInt("created_utc"));
            this.selfText = jsonPost.optString("selftext", "");
            this.flairText = jsonPost.optString("link_flair_text", "");
            this.username = "u/" + jsonPost.getString("author");
            this.subreddit = jsonPost.getString("subreddit_name_prefixed");
            this.url = jsonPost.getString("permalink");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(boolean add) {
        karma = add ? (karma += 1) : (karma -= 1);
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSelfText() {
        return selfText;
    }

    public String getFlairText() {
        return flairText;
    }

    public String getUsername() {
        return username;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getUrl() {
        return url;
    }
}
