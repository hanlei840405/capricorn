package com.galaxy.framework.capricorn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.capricorn.entity.Owner;
import com.galaxy.framework.capricorn.service.OwnerService;
import com.galaxy.framework.pisces.exception.db.NotExistException;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("get")
    public Owner get(String code) {
        Owner owner = ownerService.get(code);
        if (owner != null) {
            return owner;
        }
        throw new NotExistException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<Owner> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<Owner> pageInfo = ownerService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/findAll")
    public List<Owner> findAll(String locationCode, String status) {
        Map<String, Object> search = new HashMap<>();
        search.put("locationCode", locationCode);
        search.put("status", status);
        List<Owner> buildings = ownerService.findAll(search);
        return buildings;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public Owner save(@RequestBody Owner owner) {
        if (owner != null) {
            owner.setStatus("启用");
            ownerService.save(owner);
            return owner;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody Owner owner) {
        if (owner != null && !StringUtils.isEmpty(owner.getCode())) {
            return ownerService.deleteByCode(owner.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody Owner owner) {
        if (owner != null && !StringUtils.isEmpty(owner.getCode())) {
            return ownerService.reuse(owner.getCode());
        }
        throw new EmptyException();
    }
}
