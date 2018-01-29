package com.galaxy.framework.capricorn.entity;

import com.galaxy.framework.pisces.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Setter
@Getter
public class DictionaryItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String dictionaryCode;

    private Dictionary dictionary;
}