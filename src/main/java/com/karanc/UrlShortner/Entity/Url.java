package com.karanc.UrlShortner.Entity;

import java.util.List;

public class Url {

    private int id;

    private String longUrl;

    private String customAlias;

    private String shortUrl;

    private String ttlSeconds;

    List<String> timeAccessed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getCustomAlias() {
        return customAlias;
    }

    public void setCustomAlias(String customAlias) {
        this.customAlias = customAlias;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getTtlSeconds() {
        return ttlSeconds;
    }

    public void setTtlSeconds(String ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    public List<String> getTimeAccessed() {
        return timeAccessed;
    }

    public void setTimeAccessed(List<String> timeAccessed) {
        this.timeAccessed = timeAccessed;
    }
}
