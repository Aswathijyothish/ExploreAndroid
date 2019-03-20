package com.ashgeek.exploreandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostsViewHolder> {

    private Context context;
    private List<Posts> posts;

    public PostAdapter(List<Posts> posts , Context context){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_post, null);
        PostsViewHolder myViewHolder = new PostsViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {

        Posts post = posts.get(position);

        holder.id.setText(Integer.toString(post.getId()));
        holder.userId.setText(Integer.toString(post.getUserId()));
        holder.title.setText(post.getTitle());
        holder.text.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return (null!=posts ? posts.size() :0);
    }
}
