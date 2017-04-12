package com.zeyad.modanisa.user_interface.photos_listing;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.zeyad.modanisa.R;
import com.zeyad.modanisa.app_engine.constants.URLContstants;
import com.zeyad.modanisa.app_engine.models.Photo;
import com.zeyad.modanisa.app_engine.responses.PhotosListResponse;
import com.zeyad.modanisa.app_engine.services.ServiceException;
import com.zeyad.modanisa.app_engine.services.ServiceType;
import com.zeyad.modanisa.user_interface.BaseServiceActivity;
import com.zeyad.modanisa.user_interface.photos_listing.adapters.PhotosListAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseServiceActivity implements SwipeRefreshLayout.OnRefreshListener{
    //private variable of the activity.
    private RelativeLayout mainLayout, errorLayout;
    private ProgressBar progressBar;
    private RecyclerView list;
    private Toolbar toolbar;
    private PhotosListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Photo> photos;
    private int page =1;
    private int perPage =10;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AppBarLayout appBarLayout;
    private  AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;

    private EndlessScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        //calling the service to get data from the server.
        executeService(ServiceType.FLICKER_PHOTOS);
    }

    //This method graps some data from the UI to pass it to the service
    //Example: the page number, items per page and maybe resolution targeted.
    @Nullable
    @Override
    protected ArrayMap<String, String> getUiData(ServiceType serviceType) {
        ArrayMap<String, String > uiData = new ArrayMap<>();
        uiData.put(URLContstants.PAGE_KEY, Integer.toString(page));
        uiData.put(URLContstants.PER_PAGE_KEY, Integer.toString(perPage));
        return uiData;
    }

    //This method called when the response came from the server.
    @Override
    protected void sendDataTobeShown(Object data, ServiceType serviceType) {
        //first we need to make sure that error layout in gone.
        if(errorLayout.getVisibility() == View.VISIBLE){
            errorLayout.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);
        }
        //Then we need to make sure that loading icon of swipe refresh is disappeared.
        swipeRefreshLayout.setRefreshing(false);
        //Then we need to make sure that progress bar is disappeared.
        progressBar.setVisibility(View.GONE);
        //adding photo to the adapter.
        if(data instanceof PhotosListResponse){
            final PhotosListResponse photosListResponse = (PhotosListResponse)data;
            photos.addAll(photosListResponse.getPhotos().getPhoto());
            //check if the adapter is null, to avoid create new adapter each time.
            if(adapter == null){
                adapter = new PhotosListAdapter(this, photos );
                list.setAdapter(adapter);
            }else {
                adapter.setPhotosList(photos);
            }
            //notify the recycler view adapter with the change.
            adapter.notifyDataSetChanged();
        }
    }

    //This method called if we have any problems throwed by the service class
    @Override
    protected void handleException(ServiceException exception) {
        mainLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }

    //This is a class method called in onCreate method to initialize all the UI views
    private void initViews(){
        mainLayout = (RelativeLayout)findViewById(R.id.main_layout);
        errorLayout = (RelativeLayout)findViewById(R.id.error_layout);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        list = (RecyclerView)findViewById(R.id.list);
        layoutManager = new GridLayoutManager(this,2);
        list.setLayoutManager(layoutManager);
        scrollListener = new EndlessScrollListener((GridLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };
        list.addOnScrollListener(scrollListener);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh) ;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);
        photos = new ArrayList<>();
    }

    //To apply the load more for recycler view.
    private void loadNextDataFromApi(int page) {
        this.page++;
        executeService(ServiceType.FLICKER_PHOTOS);
    }

    //This decide the collapsing toolbar behavior.
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (collapsingToolbarLayout.getHeight() + verticalOffset < ViewCompat.getMinimumHeight(appBarLayout)) {
                    swipeRefreshLayout.setEnabled(false);
                } else {
                    swipeRefreshLayout.setEnabled(true);
                }
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Fashion");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        };
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(onOffsetChangedListener);
    }
    //This is an implementation for swipe to refresh layout in the UI.
    @Override
    public void onRefresh() {
        photos.clear();
        adapter  = null;
        page=1;
        executeService(ServiceType.FLICKER_PHOTOS);
    }
}
