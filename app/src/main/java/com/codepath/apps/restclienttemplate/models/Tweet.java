package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity=User.class, parentColumns = "id", childColumns = "userId"))
public class Tweet {

    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @Ignore
    public User user;

    @ColumnInfo
    public String time;

    @ColumnInfo
    public String media;

    @ColumnInfo
    public int mediaCount;

    @ColumnInfo
    public long userId;

    public Tweet(){
    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.id = jsonObject.getLong("id");
        JSONObject entity = jsonObject.getJSONObject("entities");
        if(entity.has("media"))
        {
            JSONArray urls = entity.getJSONArray("media");
            JSONObject links = urls.getJSONObject(0);
            tweet.media = links.getString("media_url_https");
            tweet.mediaCount = 1;
            Log.i("MediaCounter","found");
        }
        tweet.userId = tweet.user.id;

        tweet.time = tweet.getFormattedTimestamp(tweet.createdAt);
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getFormattedTimestamp(String createdAt)
    {
        return TimeFormatter.getTimeDifference(createdAt);
    }
}
