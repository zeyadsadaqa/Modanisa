package com.zeyad.modanisa.app_engine.services;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zeyad.modanisa.app_engine.parsers.DataParserFactory;
import com.zeyad.modanisa.app_engine.services.interfaces.OnServiceChangeListener;

/**
 * Created by Zeyad Assem on 11/04/17.
 * The parent class that gets data from server without need to know the url, or response type.
 */

public abstract class Service {

    public abstract void executeService(final OnServiceChangeListener onServiceChangeListener, Context context, final ServiceType serviceType, ArrayMap<String, String> uiData);

    public void getDataFromServer(final OnServiceChangeListener onServiceChangeListener, String url, Context context, final ServiceType serviceType){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            DataParserFactory dataParserFactory = new DataParserFactory();
                            onServiceChangeListener.onServiceDataFinish(serviceType, dataParserFactory.getDataParser(serviceType).parseData(response));
                        }catch(Exception e){
                            onServiceChangeListener.onServiceExecutionError(serviceType,new ServiceException());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onServiceChangeListener.onServiceExecutionError(serviceType, new ServiceException());
            }
        });
        queue.add(stringRequest);

    }


}
