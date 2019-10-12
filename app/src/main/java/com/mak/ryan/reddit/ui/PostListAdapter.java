package com.mak.ryan.reddit.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mak.ryan.reddit.R;
import com.mak.ryan.reddit.entities.Post;

import java.util.List;

import ru.noties.markwon.Markwon;
import ru.noties.markwon.html.HtmlPlugin;

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
        String timeStamp = post.getTimeStamp();
        String flair = post.getFlairText().replace("null", "");
        String selfText = post.getSelfText().replaceAll("###", "").replaceAll("&amp;#x200B;", "");

        holder.title.setText(post.getTitle());
        holder.karma.setText(karma);
        holder.timestamp.setText(timeStamp);
        holder.username.setText(post.getUsername());
        holder.subreddit.setText(post.getSubreddit());

        // if post does/doesn't have selfText
        if (selfText.isEmpty()) {
            holder.selfText.setVisibility(View.INVISIBLE);
            holder.thumbnail.setVisibility(View.VISIBLE);
            holder.selfText.setText("");
        } else {
            holder.selfText.setVisibility(View.VISIBLE);
            holder.thumbnail.setVisibility(View.INVISIBLE);

            final Markwon markwon = Markwon.builder(context).usePlugin(HtmlPlugin.create()).build();
            markwon.setMarkdown(holder.selfText, selfText);
        }

        // if flair text is empty/exists
        if (flair.isEmpty()) {
            holder.flair.setText("");
        } else {
            String f = " " + flair + " ";
            SpannableString str = new SpannableString(f);
            str.setSpan(new BackgroundColorSpan(Color.LTGRAY), 0, f.length(), SpannableString.SPAN_PARAGRAPH);
            holder.flair.setText(str);
        }
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
    NoScrollTextView selfText;
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
        selfText = itemView.findViewById(R.id.self_text);
        timestamp = itemView.findViewById(R.id.timestamp);
        flair = itemView.findViewById(R.id.tag);
        username = itemView.findViewById(R.id.username);
        subreddit = itemView.findViewById(R.id.subreddit);
        commentsBtn = itemView.findViewById(R.id.comments);
        saveBtn = itemView.findViewById(R.id.favourite);
        shareBtn = itemView.findViewById(R.id.share);
        moreOptionsBtn = itemView.findViewById(R.id.more_options);

        // in combination with round_outline_<view>.xml
        // the below views now have rounded corners
        thumbnail.setClipToOutline(true);
        selfText.setClipToOutline(true);

        // set up button listeners
    }
}
