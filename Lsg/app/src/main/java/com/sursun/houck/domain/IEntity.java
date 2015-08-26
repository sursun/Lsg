package com.sursun.houck.domain;

import java.util.HashMap;

/**
 * Created by houck on 2015/8/17.
 */
public interface IEntity {
    String getGeotableId();

    HashMap<String, String> getFilterParams();
}
