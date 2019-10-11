package com.mak.ryan.myapplication.connection;

import com.mak.ryan.myapplication.entities.Post;

import java.util.List;

public interface OnDownloadCompleteListener {
    void onDataDownloadComplete(List<Post> output);
}
