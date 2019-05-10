package com.mak.ryan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mak.ryan.myapplication.connection.JSONDataTask;
import com.mak.ryan.myapplication.connection.ResponseInterface;
import com.mak.ryan.myapplication.entities.Post;
import com.mak.ryan.myapplication.ui.PostListAdapter;
import com.mak.ryan.myapplication.utils.ConnectionUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ResponseInterface {

    private List<Post> postList = new ArrayList<>();

    private RecyclerView postRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postRecycler = findViewById(R.id.post_recyclerview);
        postRecycler.setLayoutManager(new LinearLayoutManager(this));

        if (ConnectionUtils.isNetworkAvailable(this)) {
            new JSONDataTask(this::init).execute();
        }
    }

    private void init(String output) {
        postList.add(new Post());
        postList.add(new Post());

        System.out.println(output);

        PostListAdapter adapter = new PostListAdapter(this, postList);
        postRecycler.setAdapter(adapter);
    }

    @Override
    public void processFinish(String output) throws JSONException{
        JSONObject list = new JSONObject(output);

        list.getString("");
        System.out.println(output);
    }
}
