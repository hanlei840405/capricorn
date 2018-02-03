package com.galaxy.framework.capricorn.feign;

import com.galaxy.framework.pisces.vo.aquarius.LocationVo;
import org.springframework.stereotype.Component;

@Component
public class LocationHystrix implements LocationFeign {
    @Override
    public LocationVo get(String code, String status) {
        return null;
    }
}
