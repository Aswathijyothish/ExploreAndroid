package com.ashgeek.exploreandroid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsViewHolder extends RecyclerView.ViewHolder {
    public TextView id,userId,title,text;

    public PostsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.id= (TextView) itemView.findViewById(R.id.txt_id);
        this.userId= (TextView) itemView.findViewById(R.id.txt_userid);
        this.title= (TextView) itemView.findViewById(R.id.txt_title);
        this.text= (TextView) itemView.findViewById(R.id.txt_text);

    }
}
