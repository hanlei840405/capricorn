package com.galaxy.framework.capricorn.entity;

import com.galaxy.framework.pisces.entity.BaseEntity;
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
public class House extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private BigDecimal acreage;

    private BigDecimal height;

    private BigDecimal publicArea;

    private String areaCode;

    private String remark;

    private Area area;

    private  BigDecimal rent;
}