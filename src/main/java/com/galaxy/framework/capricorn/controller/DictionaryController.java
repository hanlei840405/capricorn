package com.galaxy.framework.capricorn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galaxy.framework.capricorn.entity.Dictionary;
import com.galaxy.framework.capricorn.entity.DictionaryItem;
import com.galaxy.framework.capricorn.service.DictionaryService;
import com.galaxy.framework.pisces.exception.rule.EmptyException;
import com.galaxy.framework.pisces.vo.capricorn.DictionaryItemVo;
import com.galaxy.framework.pisces.vo.capricorn.DictionaryVo;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/key")
    public DictionaryVo getByKey(String key) {
        Dictionary dictionary = dictionaryService.getDictionaryByKey(key);
        if (dictionary != null) {
            DictionaryVo dictionaryVo = new DictionaryVo();
            BeanUtils.copyProperties(dictionary, dictionaryVo, "id");
            return dictionaryVo;
        }
        throw null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/dictionaryItems")
    public List<DictionaryItemVo> dictionaryItems(String dictionaryCode, String dictionaryKey, String status) {
        Map<String, Object> search = new HashMap<>();
        search.put("dictionaryCode", dictionaryCode);
        search.put("dictionaryKey", dictionaryKey);
        search.put("status", status);
        List<DictionaryItem> dictionaryItems = dictionaryService.findDictionaryItemsByDictionary(search);
        List<DictionaryItemVo> dictionaryItemVos = Lists.newArrayList();
        dictionaryItems.forEach(dictionaryItem -> {
            DictionaryItemVo dictionaryItemVo = new DictionaryItemVo();
            BeanUtils.copyProperties(dictionaryItem, dictionaryItemVo, "id", "dictionary");
            dictionaryItemVos.add(dictionaryItemVo);
        });
        return dictionaryItemVos;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/page")
    public PageInfo<Dictionary> page(String search, Integer pageNo, Integer pageSize) throws IOException {

        PageInfo<Dictionary> pageInfo = dictionaryService.page(objectMapper.readValue(search, new TypeReference<Map<String, Object>>() {
        }), pageNo, pageSize);
        return pageInfo;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public Dictionary save(@RequestBody Dictionary dictionary) {
        if (dictionary != null) {
            dictionary.setStatus("启用");
            dictionaryService.save(dictionary);
            return dictionary;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/saveDictionaryItem")
    public DictionaryItem save(@RequestBody DictionaryItem dictionaryItem) {
        if (dictionaryItem != null) {
            dictionaryItem.setStatus("启用");
            dictionaryService.save(dictionaryItem);
            return dictionaryItem;
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete")
    public int delete(@RequestBody Dictionary dictionary) {
        if (dictionary != null && !StringUtils.isEmpty(dictionary.getCode())) {
            return dictionaryService.deleteDictionary(dictionary.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuse")
    public int reuse(@RequestBody Dictionary dictionary) {
        if (dictionary != null && !StringUtils.isEmpty(dictionary.getCode())) {
            return dictionaryService.reuseDictionary(dictionary.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/deleteDictionary")
    public int deleteDictionary(@RequestBody DictionaryItem dictionaryItem) {
        if (dictionaryItem != null && !StringUtils.isEmpty(dictionaryItem.getCode())) {
            return dictionaryService.deleteDictionaryItem(dictionaryItem.getCode());
        }
        throw new EmptyException();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reuseDictionary")
    public int reuseDictionary(@RequestBody DictionaryItem dictionaryItem) {
        if (dictionaryItem != null && !StringUtils.isEmpty(dictionaryItem.getCode())) {
            return dictionaryService.reuseDictionaryItem(dictionaryItem.getCode());
        }
        throw new EmptyException();
    }
}
