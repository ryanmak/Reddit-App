package com.mak.ryan.myapplication.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mak.ryan.myapplication.R;
import com.mak.ryan.myapplication.entities.Post;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<Post> list;

    public PostListAdapter(Context context, List<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_post_layout, parent, false);
        return new PostListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
        PostListViewHolder holder = (PostListViewHolder) h;
        Post post = list.get(position);

        String karma = post.getKarma() + "";
        String timeStamp = post.getTimeStampUTCSeconds() + "";
        String flair = post.getFlairText().replace("null", "");

        holder.title.setText(post.getTitle());
        holder.karma.setText(karma);
        holder.timestamp.setText(timeStamp);
        holder.flair.setText(flair);
        holder.username.setText(post.getUsername());
        holder.subreddit.setText(post.getSubreddit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class PostListViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    Button upvoteBtn;
    Button downvoteBtn;
    TextView karma;
    ImageView thumbnail;
    TextView timestamp;
    TextView flair;
    TextView username;
    TextView subreddit;
    Button commentsBtn;
    Button saveBtn;
    Button shareBtn;
    Button moreOptionsBtn;

    PostListViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        upvoteBtn = itemView.findViewById(R.id.upvote);
        downvoteBtn = itemView.findViewById(R.id.downvote);
        karma = itemView.findViewById(R.id.karma_count);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        timestamp = itemView.findViewById(R.id.timestamp);
        flair = itemView.findViewById(R.id.tag);
        username = itemView.findViewById(R.id.username);
        subreddit = itemView.findViewById(R.id.subreddit);
        commentsBtn = itemView.findViewById(R.id.comments);
        saveBtn = itemView.findViewById(R.id.favourite);
        shareBtn = itemView.findViewById(R.id.share);
        moreOptionsBtn = itemView.findViewById(R.id.more_options);

        // set up button listeners
    }
}
