package com.zeyad.modanisa.user_interface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

import com.zeyad.modanisa.app_engine.services.Service;
import com.zeyad.modanisa.app_engine.services.ServiceException;
import com.zeyad.modanisa.app_engine.services.ServiceManager;
import com.zeyad.modanisa.app_engine.services.ServiceType;
import com.zeyad.modanisa.app_engine.services.interfaces.OnServiceChangeListener;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class is the base class for the activities deals with internet calls.
 * Its abstract so it is only inherited.
 */

public abstract class BaseServiceActivity extends AppCompatActivity implements OnServiceChangeListener{
    @Nullable
    private Service service;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void executeService(@NonNull final ServiceType serviceType) {
        service = ServiceManager.getInstance().getService(serviceType);
        if (service != null) {
            service.executeService(this, this,serviceType, getUiData(serviceType));
        }

        // release service resource
        service = null;

    }

    @Nullable
    protected abstract ArrayMap<String, String> getUiData(ServiceType serviceType);
    protected abstract void sendDataTobeShown(Object data, ServiceType serviceType);
    protected abstract void handleException(ServiceException exception);

    @Override
    public void onServiceDataFinish(ServiceType serviceType, Object data) {
        sendDataTobeShown(data,serviceType);
    }

    @Override
    public void onServiceExecutionError(ServiceType serviceType, ServiceException exception) {
        handleException(exception);
    }
}
