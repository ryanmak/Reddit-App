package com.mak.ryan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mak.ryan.myapplication.connection.JSONDataTask;
import com.mak.ryan.myapplication.entities.Post;
import com.mak.ryan.myapplication.ui.PostListAdapter;
import com.mak.ryan.myapplication.utils.ConnectionUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            new JSONDataTask(this::init).execute();
        }
    }

    private void init(String output) throws JSONException{
        JSONObject page = new JSONObject(output);
        JSONObject data = page.getJSONObject("data");
        JSONArray children = data.getJSONArray("children");

        for (int i = 0; i < children.length(); i++) {
            JSONObject child = children.getJSONObject(i);
            System.out.println("kind" + child.getString("kind"));
            postList.add(new Post(child));
        }

        PostListAdapter adapter = new PostListAdapter(this, postList);
        postRecycler.setAdapter(adapter);
    }
}
