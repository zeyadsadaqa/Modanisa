package com.zeyad.modanisa.app_engine.responses;

import com.zeyad.modanisa.app_engine.models.Photos;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class represents the photolist response we get directly from flicker.
 */

public class PhotosListResponse {
    private Photos photos;
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }


}
