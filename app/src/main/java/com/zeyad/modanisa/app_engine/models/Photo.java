package com.zeyad.modanisa.app_engine.models;

/**
 * Created by Zeyad Assem on 10/04/17.
 * This class represents the java object for the photo json object in flicker APIs.
 */

public class Photo {

    private String id;
    private String owner;
    private String secret;
    private String server;
    private Integer farm;
    private String title;
    private int isPublic;
    private int isFriend;
    private int isFamily;

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public Integer getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public int getIsFamily() {
        return isFamily;
    }

}
