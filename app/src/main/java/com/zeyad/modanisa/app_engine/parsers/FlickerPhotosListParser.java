package com.zeyad.modanisa.app_engine.parsers;

import com.zeyad.modanisa.app_engine.models.Photos;
import com.zeyad.modanisa.app_engine.responses.PhotosListResponse;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class represents a DataParser type.
 * used to parse flicker photo list response.
 */

public class FlickerPhotosListParser implements DataParser {


    @Override
    public Object parseData(String response) {
        PhotosListResponse photos = GSONManager.getInstance().getGson().fromJson(response, PhotosListResponse.class);
        return photos;
    }
}
