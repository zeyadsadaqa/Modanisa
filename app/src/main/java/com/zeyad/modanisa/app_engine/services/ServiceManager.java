package com.zeyad.modanisa.app_engine.services;

/**
 * Created by Zeyad Assem on 11/04/17.
 * Singleton class returns the child service class based on the  service type.
 */

public class ServiceManager {
    private static ServiceManager serviceManager =null;
    private ServiceManager(){}
    public static ServiceManager getInstance(){
        if(serviceManager == null){
            synchronized (ServiceManager.class){
                if(serviceManager==null){
                    serviceManager = new ServiceManager();
                }
            }
        }
        return serviceManager;
    }

    public static Service getService(ServiceType serviceType){
        switch (serviceType){
            case FLICKER_PHOTOS:
                return new FlickerService();
            default:
                return null;
        }
    }
}
