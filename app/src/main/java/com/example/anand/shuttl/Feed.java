package com.example.anand.shuttl;

import java.io.Serializable;

/**
 * Created by Anand on 07-Oct-17.
 */

public class Feed implements Serializable {
    private String name;
    private String imageUrl;
    private String title;
    private String text;
    private long time;
    private String description;

    public Feed(String name, String imageUrl, String title, String text, long time, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.title = title;
        this.text = text;
        this.time = time;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public long getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
