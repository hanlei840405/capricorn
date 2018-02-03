package com.galaxy.framework.capricorn.feign;

import com.galaxy.framework.pisces.vo.aquarius.LocationVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "system", fallback = LocationHystrix.class)
public interface LocationFeign {
    @RequestMapping(value = "/location/get", method = RequestMethod.GET)
    LocationVo get(@RequestParam(value = "code") String code, @RequestParam(value = "status") String status);
}
