package com.galaxy.framework.capricorn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.capricorn.entity.House;
import com.galaxy.framework.capricorn.feign.LocationFeign;
import com.galaxy.framework.capricorn.feign.UserFeign;
import com.galaxy.framework.capricorn.service.HouseService;
import com.galaxy.framework.pisces.exception.db.NotExistException;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.galaxy.framework.pisces.vo.aquarius.LocationVo;
import com.galaxy.framework.pisces.vo.aquarius.UserVo;
import com.galaxy.framework.pisces.vo.capricorn.HouseVo;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private LocationFeign locationFeign;

    @Autowired
    private UserFeign userFeign;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/code")
    public HouseVo getByCode(String code) {
        House house = houseService.get(code);
        if (house != null) {
            HouseVo houseVo = new HouseVo();
            BeanUtils.copyProperties(house, houseVo, "id", "area");
            if (house.getArea() != null) {
                houseVo.setAreaName(house.getArea().getName());
            }
            return houseVo;
        }
        throw new NotExistException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/floor")
    public List<HouseVo> findByFloor(String floorCode) {
        Map<String,Object> search = ImmutableMap.of("floorCode", floorCode);
        List<House> houses = houseService.findAll(search);
        List<HouseVo> houseVos = Lists.newArrayList();
        houses.forEach(house -> {
            HouseVo houseVo = new HouseVo();
            BeanUtils.copyProperties(house, houseVo, "id", "area");
            houseVos.add(houseVo);
        });
        return houseVos;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<House> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<House> pageInfo = houseService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        pageInfo.getList().forEach(house -> {
            if (house.getArea() != null && house.getArea().getFloor() != null && house.getArea().getFloor().getBuilding() != null) {
                LocationVo locationVo = locationFeign.get(house.getArea().getFloor().getBuilding().getLocationCode(), "启用");
                if (locationVo != null) {
                    house.getArea().getFloor().getBuilding().setLocationName(locationVo.getName());
                }
            }
            if (!StringUtils.isEmpty(house.getArea().getUserCode())) {
                UserVo userVo = userFeign.getByCode(house.getArea().getFloor().getBuilding().getLocationCode());
                if (userVo != null) {
                    house.getArea().setUsername(userVo.getName());
                }
            }
        });
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/findAll")
    public List<House> findAll(String locationCode, String buildingCode, String floorCode, String areaCode, String status) {
        Map<String, Object> search = new HashMap<>();
        search.put("locationCode", locationCode);
        search.put("buildingCode", buildingCode);
        search.put("floorCode", floorCode);
        search.put("areaCode", areaCode);
        search.put("status", status);
        List<House> departments = houseService.findAll(search);
        return departments;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public House save(@RequestBody House house) {
        if (house != null) {
            house.setStatus("有效");
            houseService.save(house);
            return house;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody House house) {
        if (house != null && !StringUtils.isEmpty(house.getCode())) {
            return houseService.deleteByCode(house.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody House house) {
        if (house != null && !StringUtils.isEmpty(house.getCode())) {
            return houseService.reuse(house.getCode());
        }
        throw new EmptyException();
    }
}
