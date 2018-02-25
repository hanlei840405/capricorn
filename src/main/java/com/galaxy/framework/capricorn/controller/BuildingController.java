package com.galaxy.framework.capricorn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.capricorn.entity.Building;
import com.galaxy.framework.capricorn.feign.LocationFeign;
import com.galaxy.framework.capricorn.service.BuildingService;
import com.galaxy.framework.pisces.exception.db.NotExistException;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.galaxy.framework.pisces.vo.aquarius.LocationVo;
import com.galaxy.framework.pisces.vo.capricorn.BuildingVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private LocationFeign locationFeign;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/code")
    public BuildingVo getByCode(String code) {
        Building building = buildingService.get(code);
        if (building != null) {
            BuildingVo buildingVo = new BuildingVo();
            BeanUtils.copyProperties(building, buildingVo, "id");
            return buildingVo;
        }
        throw new NotExistException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<Building> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<Building> pageInfo = buildingService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        pageInfo.getList().forEach(building -> {
            LocationVo locationVo = locationFeign.get(building.getLocationCode(), "启用");
            if (locationVo != null) {
                building.setLocationName(locationVo.getName());
            }
        });
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/findAll")
    public List<Building> findAll(String locationCode, String status) {
        Map<String, Object> search = new HashMap<>();
        search.put("locationCode", locationCode);
        search.put("status", status);
        List<Building> buildings = buildingService.findAll(search);
        return buildings;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public Building save(@RequestBody Building building) {
        if (building != null) {
            building.setStatus("启用");
            buildingService.save(building);
            return building;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody Building building) {
        if (building != null && !StringUtils.isEmpty(building.getCode())) {
            return buildingService.deleteByCode(building.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody Building building) {
        if (building != null && !StringUtils.isEmpty(building.getCode())) {
            return buildingService.reuse(building.getCode());
        }
        throw new EmptyException();
    }
}
