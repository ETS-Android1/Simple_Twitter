package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailedActivity extends AppCompatActivity {
    Context context;
    TextView tvScreenName;
    TextView tvCreatedAt;
    TextView tvBody;
    ImageView ivProfileImage;
    ImageView ivImages;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        tvScreenName = findViewById(R.id.tvScreenName);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        tvBody = findViewById(R.id.tvBody);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        ivImages = findViewById(R.id.ivImages);

        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));

        tvScreenName.setText(tweet.user.screenName);
        tvCreatedAt.setText(tweet.createdAt);
        tvBody.setText(tweet.body);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImage);
        Glide.with(this).load(tweet.media).into(ivImages);
    }



}