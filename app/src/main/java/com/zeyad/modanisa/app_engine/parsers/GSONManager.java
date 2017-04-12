package com.zeyad.modanisa.app_engine.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Zeyad Assem on 11/04/17.
 * A singleton class that returns the GSON object.
 * Build on the top of singleton pattern, because we need to use it once and initialize it whenever we need.
 * Also to be globally accessed.
 */

public class GSONManager {
    private static GSONManager instance;
    private Gson gson;

    private GSONManager() {
        gson = new GsonBuilder().create();
    }

    public static GSONManager getInstance() {
        if (instance == null) {
            synchronized (GSONManager.class) {
                if (instance == null) {
                    instance = new GSONManager();
                }
            }
        }

        return instance;
    }

    public Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }
}
