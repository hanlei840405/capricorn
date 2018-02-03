package com.galaxy.framework.capricorn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.capricorn.entity.Floor;
import com.galaxy.framework.capricorn.feign.LocationFeign;
import com.galaxy.framework.capricorn.service.FloorService;
import com.galaxy.framework.pisces.exception.db.NotExistException;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.galaxy.framework.pisces.vo.aquarius.LocationVo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @Autowired
    private LocationFeign locationFeign;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("get")
    public Floor get(String code) {
        Floor floor = floorService.get(code);
        if (floor != null) {
            return floor;
        }
        throw new NotExistException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<Floor> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<Floor> pageInfo = floorService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        pageInfo.getList().forEach(floor -> {
            if (floor.getBuilding() != null) {
                LocationVo locationVo = locationFeign.get(floor.getBuilding().getLocationCode(), "启用");
                if (locationVo != null) {
                    floor.getBuilding().setLocationName(locationVo.getName());
                }
            }
        });
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/findAll")
    public List<Floor> findAll(String locationCode, String buildingCode, String status) {
        Map<String, Object> search = new HashMap<>();
        search.put("locationCode", locationCode);
        search.put("buildingCode", buildingCode);
        search.put("status", status);
        List<Floor> departments = floorService.findAll(search);
        return departments;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public Floor save(@RequestBody Floor floor) {
        if (floor != null) {
            floor.setStatus("启用");
            floorService.save(floor);
            return floor;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody Floor floor) {
        if (floor != null && !StringUtils.isEmpty(floor.getCode())) {
            return floorService.deleteByCode(floor.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody Floor floor) {
        if (floor != null && !StringUtils.isEmpty(floor.getCode())) {
            return floorService.reuse(floor.getCode());
        }
        throw new EmptyException();
    }
}
