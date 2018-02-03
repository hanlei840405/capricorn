package com.galaxy.framework.capricorn.feign;

import com.galaxy.framework.pisces.vo.aquarius.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "system", fallback = UserHystrix.class)
public interface UserFeign {
    @RequestMapping(value = "/user/getByCode", method = RequestMethod.GET)
    UserVo getByCode(@RequestParam(value = "code") String code);
}
