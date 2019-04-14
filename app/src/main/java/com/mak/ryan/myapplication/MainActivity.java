package com.mak.ryan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mak.ryan.myapplication.connection.GetJSONDataTask;
import com.mak.ryan.myapplication.connection.ConnectionUtils;
import com.mak.ryan.myapplication.entities.Post;
import com.mak.ryan.myapplication.ui.PostListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Post> postList = new ArrayList<>();

    private RecyclerView postRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postRecycler = findViewById(R.id.post_recyclerview);
        postRecycler.setLayoutManager(new LinearLayoutManager(this));

        if (ConnectionUtils.isNetworkAvailable(this)) {
            new GetJSONDataTask(this::init).execute();
        }
    }

    private void init(String output) {
        postList.add(new Post());
        postList.add(new Post());

        System.out.println(output);

        PostListAdapter adapter = new PostListAdapter(this, postList);
        postRecycler.setAdapter(adapter);
    }
}
