package com.mak.ryan.reddit.connection;

import com.mak.ryan.reddit.entities.Post;

import java.util.List;

public interface OnDownloadCompleteListener {
    void onDataDownloadComplete(List<Post> output);
}
