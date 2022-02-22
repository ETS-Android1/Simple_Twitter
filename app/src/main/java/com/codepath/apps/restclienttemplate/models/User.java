package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {

    public User() {

    }

    public String name;
    public String screenName;
    public String profileImageUrl;
    public String profileUrl;

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url_https");
        JSONObject link = jsonObject.getJSONObject("entities").getJSONObject("url");
        JSONArray urls = link.getJSONArray("urls");
        if(urls.length()>0)
        {
            user.profileUrl = urls.getJSONObject(0).getString("url");
        }
        return user;
    }
}
