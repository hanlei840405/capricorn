package com.galaxy.framework.capricorn.entity;

import com.galaxy.framework.pisces.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jesse.han
 */
@Setter
@Getter
public class Area extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private BigDecimal acreage;

    private BigDecimal height;

    private String floorCode;

    private Floor floor;

    /**
     * 管家
     */
    private String userCode;

    private String remark;
}