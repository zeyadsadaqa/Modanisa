package com.zeyad.modanisa.app_engine.url_builders;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class represents the photo url to be loaded with glide.
 */

public class PhotoURL implements Parcelable {
    private String url;
    private String farmId;
    private String serverId;
    private String id;
    private String secret;
    private String photoType;

    public PhotoURL(String farmId, String serverId, String id, String secret, String photoType){
       this.farmId = farmId;
        this.serverId = serverId;
        this.id = id;
        this.secret = secret;
        this.photoType = photoType;
    }

    public void setPhotoType(String photoType){
        this.photoType = photoType;
    }

    @Override
    public String toString() {
        url = "https://farm"+farmId+".staticflickr.com/"+serverId+"/"+id+"_"+secret+"_"+photoType+".jpg";
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.farmId);
        dest.writeString(this.serverId);
        dest.writeString(this.id);
        dest.writeString(this.secret);
        dest.writeString(this.photoType);
    }

    protected PhotoURL(Parcel in) {
        this.url = in.readString();
        this.farmId = in.readString();
        this.serverId = in.readString();
        this.id = in.readString();
        this.secret = in.readString();
        this.photoType = in.readString();
    }

    public static final Parcelable.Creator<PhotoURL> CREATOR = new Parcelable.Creator<PhotoURL>() {
        @Override
        public PhotoURL createFromParcel(Parcel source) {
            return new PhotoURL(source);
        }

        @Override
        public PhotoURL[] newArray(int size) {
            return new PhotoURL[size];
        }
    };
}
