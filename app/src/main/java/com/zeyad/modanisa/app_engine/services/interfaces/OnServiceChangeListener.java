package com.zeyad.modanisa.app_engine.services.interfaces;

import com.zeyad.modanisa.app_engine.services.ServiceException;
import com.zeyad.modanisa.app_engine.services.ServiceType;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This interface used to know when we got the data from the server.
 */

public interface OnServiceChangeListener {
    //Called when we got a response from the server.
    void onServiceDataFinish(ServiceType serviceType, Object data);
    //Called when we got any knid of errors.
    void onServiceExecutionError(ServiceType serviceType, ServiceException exception);
}
