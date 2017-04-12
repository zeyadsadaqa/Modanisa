package com.zeyad.modanisa.app_engine.url_builders;


import com.zeyad.modanisa.app_engine.constants.URLContstants;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class builds the url for Flicker APIs.
 * Builds on the top of builder pattern.
 */

public class PhotoListURL {
    private String baseURL = "https://api.flickr.com/services/rest/?";

    public static class PhotoListURLBuilder {
        private String method;
        private String apiKey;
        private String format;
        private String tags;
        private String page;
        private String perPage;
        private String noJsonCallback;

        public PhotoListURLBuilder(String method, String apiKey){
            this.method = method;
            this.apiKey = apiKey;
        }

        public PhotoListURLBuilder format(String format){
            this.format = format;
            return this;
        }
        public PhotoListURLBuilder tags(String tags){
            this.tags = tags;
            return this;
        }
        public PhotoListURLBuilder page(String page){
            this.page = page;
            return this;
        }
        public PhotoListURLBuilder perPage(String perPage){
            this.perPage = perPage;
            return this;
        }
        public PhotoListURLBuilder noJsonCallback(boolean noJsonCallback){
            if(noJsonCallback) {
                this.noJsonCallback = "1";
                return this;
            } else {
                this.noJsonCallback = "0";
                return this;
            }
        }

        public PhotoListURL build(){
            return new PhotoListURL(this);
        }
    }
    private PhotoListURL( PhotoListURLBuilder builder){
        baseURL+= (URLContstants.METHOD_KEY +"="+builder.method + "&"+URLContstants.API_KEY_NAME+"="+builder.apiKey);
        if (builder.format != null){
            baseURL+=("&"+URLContstants.FORMAT_KEY+"="+builder.format);
        }
        if(builder.tags !=null){
            baseURL+=("&" +URLContstants.TAGS_KEY+"="+builder.tags);
        }
        if(builder.page != null){
            baseURL+=("&"+URLContstants.PAGE_KEY+"="+builder.page);
        }
        if(builder.perPage != null){
            baseURL+=("&"+URLContstants.PER_PAGE_KEY+"="+builder.perPage);
        }
        if(builder.noJsonCallback != null){
            baseURL+=("&"+URLContstants.NO_JSON_CALLBACK_KEY+"="+builder.noJsonCallback);
        }
    }

    @Override
    public String toString() {
        return baseURL;
    }
}
