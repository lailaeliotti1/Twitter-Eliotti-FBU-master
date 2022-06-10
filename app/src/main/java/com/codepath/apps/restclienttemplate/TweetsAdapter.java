package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    //passes in context and list of tweets
    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets = tweets;
    }
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }
    //bind values based on the position of the element.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }
    //define viewholder
    @Override
    public int getItemCount() {
        return tweets.size();
    }
    //for each row, inflate layout

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvScreenName;
        TextView tvBody;
        ImageView ivTweetImage;

         public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody = itemView.findViewById(R.id.tvBody);
            ivTweetImage = itemView.findViewById(R.id.ivTweetImage);


        }

        public void bind(Tweet tweet) {
             tvBody.setText(tweet.body);
             tvScreenName.setText(tweet.user.screenName);
             Glide.with(context).load(tweet.user.publicImageUrl).into(ivProfileImage);
             if (tweet.imgUrl != null){
                 ivTweetImage.setVisibility(View.VISIBLE);
                 Glide.with(context).load(tweet.imgUrl).into(ivTweetImage);
             }
             else{
                 ivTweetImage.setVisibility(View.GONE);
             }

        }
    }
}
