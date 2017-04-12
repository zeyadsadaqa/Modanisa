package com.zeyad.modanisa.user_interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zeyad.modanisa.R;
import com.zeyad.modanisa.app_engine.constants.PhotoType;
import com.zeyad.modanisa.app_engine.url_builders.PhotoURL;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class represents the photo details class.
 */
public class PhotoDetailsActivity extends AppCompatActivity {
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        setToolBar();
        photo = (ImageView) findViewById(R.id.details_image);
        //getting the photo url from the adapter, changing the photo size, then load it.
        if(getIntent().getExtras().containsKey("url")){
            PhotoURL url = getIntent().getParcelableExtra("url");
            url.setPhotoType(PhotoType.LARGE_1600.toString());
            Glide.with(this).load(url.toString()).into(photo);
        }
    }

    //This method used to iniliza the toolbar
    private void setToolBar(){
        Toolbar toolBar = (Toolbar)findViewById(R.id.toolbar);
        toolBar.setTitle("Details Photo");
        toolBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //This method handle the back frim the back icon in the tool bar.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
