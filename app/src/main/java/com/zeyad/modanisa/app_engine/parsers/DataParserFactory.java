package com.zeyad.modanisa.app_engine.parsers;

import com.zeyad.modanisa.app_engine.services.ServiceType;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This class will return the parser type depends on the server.
 * It build on the top of the factory pattern.
 */

public class DataParserFactory {
    /*
    * the factory method that returns the abstract data parser.
    * */
    public DataParser getDataParser(ServiceType serviceType){
        switch (serviceType){
            case FLICKER_PHOTOS:
                return new FlickerPhotosListParser();
            default:
                return null;
        }
    }
}
