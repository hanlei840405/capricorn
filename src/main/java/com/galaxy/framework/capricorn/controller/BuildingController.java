package com.galaxy.framework.capricorn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.capricorn.entity.Building;
import com.galaxy.framework.capricorn.service.BuildingService;
import com.galaxy.framework.pisces.exception.db.NotExistException;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("get")
    public Building get(String code) {
        Building building = buildingService.get(code);
        if (building != null) {
            return building;
        }
        throw new NotExistException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<Building> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<Building> pageInfo = buildingService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/findAll")
    public List<Building> findAll(String status) {
        List<Building> departments = buildingService.findAll(ImmutableMap.of("status", status));
        return departments;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public Building save(@RequestBody Building department) {
        if (department != null) {
            department.setStatus("启用");
            buildingService.save(department);
            return department;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody String code) {
        if (!StringUtils.isEmpty(code)) {
            return buildingService.deleteByCode(code);
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody String code) {
        if (!StringUtils.isEmpty(code)) {
            return buildingService.reuse(code);
        }
        throw new EmptyException();
    }
}
