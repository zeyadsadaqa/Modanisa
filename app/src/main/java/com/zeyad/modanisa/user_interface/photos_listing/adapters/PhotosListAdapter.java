package com.zeyad.modanisa.user_interface.photos_listing.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeyad.modanisa.R;
import com.zeyad.modanisa.app_engine.constants.PhotoType;
import com.zeyad.modanisa.app_engine.models.Photo;
import com.zeyad.modanisa.app_engine.url_builders.PhotoURL;
import com.zeyad.modanisa.user_interface.PhotoDetailsActivity;

import java.util.ArrayList;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This is the adapter class for the recycler view used in the main activity.
 */

public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.PhotosViewHolder>{
    private Context mContext;
    private ArrayList<Photo> photosList;

    //The constructor that takes data from UI.
    public PhotosListAdapter(Context mContext,ArrayList<Photo> photosList) {
        this.mContext = mContext;
        this.photosList = photosList;
    }

    //setter for photolist used inloading more
    public void setPhotosList(ArrayList<Photo> photos){
        this.photosList = photos;
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_card_item, parent, false);
        return new PhotosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotosViewHolder holder, int position) {
        final PhotoURL url = new PhotoURL(Integer.toString(photosList.get(position).getFarm()), photosList.get(position).getServer(), photosList.get(position).getId(), photosList.get(position).getSecret(), PhotoType.LARGE_SQUARE.toString());
        Glide.with(mContext).load(url.toString()).fitCenter().into(holder.thumbnail);
        holder.title.setText(photosList.get(position).getTitle());
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.thumbnail.getContext(), PhotoDetailsActivity.class);
                intent.putExtra("url", url);
                //This is used if the OS is lolipop or above to apply the new matrial design transition.
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    Pair<View, String> pair1 = Pair.create((View)holder.thumbnail, holder.thumbnail.getTransitionName());
                    ActivityOptionsCompat options = ActivityOptionsCompat. makeSceneTransitionAnimation((Activity) mContext, pair1);
                    mContext.startActivity(intent, options.toBundle());
                } else{
                    mContext.startService(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public PhotosViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

}
