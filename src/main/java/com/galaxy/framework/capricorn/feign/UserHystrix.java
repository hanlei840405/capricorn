package com.galaxy.framework.capricorn.feign;

import com.galaxy.framework.pisces.vo.aquarius.UserVo;
import org.springframework.stereotype.Component;

@Component
public class UserHystrix implements UserFeign {
    @Override
    public UserVo getByCode(String code) {
        return null;
    }
}
