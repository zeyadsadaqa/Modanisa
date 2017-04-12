package com.zeyad.modanisa.app_engine.services;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.zeyad.modanisa.app_engine.constants.URLContstants;
import com.zeyad.modanisa.app_engine.services.interfaces.OnServiceChangeListener;
import com.zeyad.modanisa.app_engine.url_builders.PhotoListURL;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class extends Service, to implement how we gonna call the APIs.
 * Also it knows the url requirements, and build the url.
 * The parent method getDataFromServer, gets the data from the server , and calling the listner to notify the UI.
 */

public class FlickerService extends Service{
    protected void executeService() {

    }

    @Override
    public void executeService(OnServiceChangeListener onServiceChangeListener, Context context, ServiceType serviceType, ArrayMap<String, String> uiData) {
        PhotoListURL.PhotoListURLBuilder urlBuilder = new PhotoListURL.PhotoListURLBuilder(URLContstants.PHOTO_SEARCH_METHOD, URLContstants.API_KEY);
        urlBuilder.format(URLContstants.JSON_FORMAT).tags(URLContstants.FASHION_TAG).noJsonCallback(true);

        if(uiData.containsKey(URLContstants.PAGE_KEY)){
            urlBuilder.page(uiData.get(URLContstants.PAGE_KEY));
        }if(uiData.containsKey(URLContstants.PER_PAGE_KEY)){
            urlBuilder.perPage(uiData.get(URLContstants.PER_PAGE_KEY));
        }
        PhotoListURL url = urlBuilder.build();
        getDataFromServer(onServiceChangeListener, url.toString(), context, serviceType);
    }
}
