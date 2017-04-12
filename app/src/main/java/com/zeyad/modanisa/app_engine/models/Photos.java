package com.zeyad.modanisa.app_engine.models;

import java.util.ArrayList;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class represents the java object for the photos json object in flicker APIs.
 */

public class Photos {
    public Integer getPage() {
        return page;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public String getTotal() {
        return total;
    }

    public ArrayList<Photo> getPhoto() {
        return photo;
    }

    private Integer page;
    private Integer pages;
    private Integer perPage;
    private String total;
    private ArrayList<Photo> photo;
}
