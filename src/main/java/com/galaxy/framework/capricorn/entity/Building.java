package com.galaxy.framework.capricorn.entity;

import com.galaxy.framework.pisces.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jesse.han
 */
@Setter
@Getter
public class Building extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String locationCode;

    private String address;

    private BigDecimal acreage;

    private BigDecimal height;

    private String remark;

    private String locationName;
}