package com.example.viet.demonewsapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vaio on 1/26/2017.
 */

public class NewsItem {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("contentPreview")
    @Expose
    String contentPreview;
    @SerializedName("timeStamp")
    @Expose
    String timeStamp;
    @SerializedName("contentLink")
    @Expose
    String contentLink;
    @SerializedName("imageLinkWrapper")
    @Expose
    String imageLinkWrapper;
    @SerializedName("topicName")
    @Expose
    String topicName;
    @SerializedName("author")
    @Expose
    String author;

    public NewsItem(String name, String contentPreview, String timeStamp, String contentLink, String imageLinkWrapper, String topicName, String author) {
        this.name = name;
        this.contentPreview = contentPreview;
        this.timeStamp = timeStamp;
        this.contentLink = contentLink;
        this.imageLinkWrapper = imageLinkWrapper;
        this.topicName = topicName;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getContentPreview() {
        return contentPreview;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getContentLink() {
        return contentLink;
    }

    public String getImageLinkWrapper() {
        return imageLinkWrapper;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return name + " : " + contentPreview;
    }
}
