package com.zeyad.modanisa.app_engine.parsers;

import com.zeyad.modanisa.app_engine.services.ServiceType;

/**
 * Created by Zeyad Assem on 11/04/17.
 * This interface is used to hide what type of parser are used,
 * it has only a parse method that implemented by all parsers.
 */

public interface DataParser {
    Object parseData(String response);
}
