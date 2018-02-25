package com.galaxy.framework.capricorn.service;

import com.galaxy.framework.capricorn.entity.Dictionary;
import com.galaxy.framework.capricorn.entity.DictionaryItem;
import com.galaxy.framework.capricorn.mapper.DictionaryItemMapper;
import com.galaxy.framework.capricorn.mapper.DictionaryMapper;
import com.galaxy.framework.pisces.exception.db.UpdateException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;
    @Autowired
    private RedisSequenceService redisSequenceService;

    public Dictionary getDictionary(String code) {
        return dictionaryMapper.selectByCode(code);
    }

    public DictionaryItem getDictionaryItem(String code) {
        return dictionaryItemMapper.selectByCode(code);
    }

    public Dictionary getDictionaryByKey(String key) {
        return dictionaryMapper.selectByKey(key);
    }

    public DictionaryItem getDictionaryItemByKey(String key) {
        return dictionaryItemMapper.selectByKey(key);
    }


    public List<Dictionary> findAllDictionary(Map<String, Object> search) {
        return dictionaryMapper.findAll(search);
    }


    public List<DictionaryItem> findDictionaryItemsByDictionary(Map<String, Object> search) {
        return dictionaryItemMapper.findByDictionary(search);
    }

    public PageInfo<Dictionary> page(Map<String, Object> search, int pageNo, int pageSize) {
        PageInfo<Dictionary> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> findAllDictionary(search));

        return pageInfo;
    }

    @Transactional
    public void save(Dictionary dictionary) {
        if (!StringUtils.isEmpty(dictionary.getCode())) { // 执行更新操作
            Dictionary exist = dictionaryMapper.selectByCode(dictionary.getCode());
            BeanUtils.copyProperties(dictionary, exist, "id");
            dictionaryMapper.updateByPrimaryKey(exist);
        } else { // 执行新增
            String code = redisSequenceService.generate(Dictionary.class.getName());
            dictionary.setCode(code);
            dictionaryMapper.insert(dictionary);
        }
    }

    @Transactional
    public void save(DictionaryItem dictionaryItem) {
        if (!StringUtils.isEmpty(dictionaryItem.getCode())) { // 执行更新操作
            DictionaryItem exist = dictionaryItemMapper.selectByCode(dictionaryItem.getCode());
            BeanUtils.copyProperties(dictionaryItem, exist, "id");
            dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
        } else { // 执行新增
            String code = redisSequenceService.generate(DictionaryItem.class.getName());
            dictionaryItem.setCode(code);
            dictionaryItemMapper.insert(dictionaryItem);
        }
    }

    @Transactional
    public int deleteDictionary(String code) {
        Dictionary dictionary = getDictionary(code);
        try {
            dictionary.setStatus("删除");
            return dictionaryMapper.updateByPrimaryKey(dictionary);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int deleteDictionaryItem(String code) {
        DictionaryItem dictionaryItem = getDictionaryItem(code);
        try {
            dictionaryItem.setStatus("删除");
            return dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int reuseDictionary(String code) {
        Dictionary dictionary = getDictionary(code);
        try {
            dictionary.setStatus("启用");
            return dictionaryMapper.updateByPrimaryKey(dictionary);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }

    @Transactional
    public int reuseDictionaryItem(String code) {
        DictionaryItem dictionaryItem = getDictionaryItem(code);
        try {
            dictionaryItem.setStatus("启用");
            return dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
}
