package com.galaxy.framework.capricorn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.capricorn.entity.Area;
import com.galaxy.framework.capricorn.feign.LocationFeign;
import com.galaxy.framework.capricorn.feign.UserFeign;
import com.galaxy.framework.capricorn.service.AreaService;
import com.galaxy.framework.pisces.exception.db.NotExistException;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.galaxy.framework.pisces.vo.aquarius.LocationVo;
import com.galaxy.framework.pisces.vo.aquarius.UserVo;
import com.galaxy.framework.pisces.vo.capricorn.AreaVo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private LocationFeign locationFeign;

    @Autowired
    private UserFeign userFeign;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/code")
    public AreaVo getByCode(String code) {
        Area area = areaService.get(code);
        if (area != null) {
            AreaVo areaVo = new AreaVo();
            BeanUtils.copyProperties(area, areaVo, "id", "floor");
            if (area.getFloor() != null) {
                areaVo.setFloorName(area.getFloor().getName());
            }
            return areaVo;
        }
        throw new NotExistException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<Area> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<Area> pageInfo = areaService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        pageInfo.getList().forEach(area -> {
            if (area.getFloor() != null && area.getFloor().getBuilding() != null) {
                LocationVo locationVo = locationFeign.get(area.getFloor().getBuilding().getLocationCode(), "启用");
                if (locationVo != null) {
                    area.getFloor().getBuilding().setLocationName(locationVo.getName());
                }
            }
            if (!StringUtils.isEmpty(area.getUserCode())) {
                UserVo userVo = userFeign.getByCode(area.getFloor().getBuilding().getLocationCode());
                if (userVo != null) {
                    area.setUsername(userVo.getName());
                }
            }
        });
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/findAll")
    public List<Area> findAll(String floorCode, String status) {
        Map<String, Object> search = Maps.newHashMap();
        search.put("floorCode", floorCode);
        search.put("status", status);
        List<Area> areas = areaService.findAll(search);
        return areas;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public Area save(@RequestBody Area area) {
        if (area != null) {
            area.setStatus("启用");
            areaService.save(area);
            return area;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody Area area) {
        if (area != null && !StringUtils.isEmpty(area.getCode())) {
            return areaService.deleteByCode(area.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody Area area) {
        if (area != null && !StringUtils.isEmpty(area.getCode())) {
            return areaService.reuse(area.getCode());
        }
        throw new EmptyException();
    }
}
