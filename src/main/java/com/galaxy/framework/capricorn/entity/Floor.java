package com.galaxy.framework.capricorn.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
@Setter
@Getter
public class Floor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private BigDecimal acreage;

    private BigDecimal height;

    private String buildingCode;

    private String remark;

    private Building building;
}