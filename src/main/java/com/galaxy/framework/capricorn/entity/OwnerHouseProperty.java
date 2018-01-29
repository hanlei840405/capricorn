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
public class OwnerHouseProperty extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ownerCode;

    private String housePropertyCode;

    private String forever;

    private Date serviceFrom;

    private Date serviceTo;

    private Owner owner;

    private OwnerHouseProperty ownerHouseProperty;
}