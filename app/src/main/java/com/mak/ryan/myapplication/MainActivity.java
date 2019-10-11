package com.mak.ryan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mak.ryan.myapplication.connection.DataDownloaderThread;
import com.mak.ryan.myapplication.entities.Post;
import com.mak.ryan.myapplication.ui.PostListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Post> postDataList = new ArrayList<>();

    private RecyclerView postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postList = findViewById(R.id.post_recyclerview);
        postList.setLayoutManager(new LinearLayoutManager(this));

        PostListAdapter adapter = new PostListAdapter(this, postDataList);
        postList.setAdapter(adapter);

        DataDownloaderThread dl = new DataDownloaderThread();
        dl.setOnDownloadCompleteListener((posts) -> {
            this.postDataList.addAll(posts);
            refresh();
        });
        dl.start();
    }

    private void refresh() {
        runOnUiThread(() -> {
            System.out.println(postDataList);
            postList.getAdapter().notifyDataSetChanged();
        });
    }
}
