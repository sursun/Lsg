package com.sursun.houck.bdapi;

import com.baidu.location.BDLocation;

/**
 * Created by houck on 2015/8/25.
 */
public interface HKLocationListener {
    void onReceiveLocation(BDLocation location);
}
