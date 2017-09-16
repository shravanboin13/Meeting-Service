package com.accion.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * This class will have the base fields which needs to be
 * inherited for all other model objects.
 */
public abstract class BaseDomain implements Serializable{
    //Created date
    @CreatedDate
    protected Date createdOn ;
    //modified date
    @LastModifiedDate
    protected Date modifiedOn;
    //created by user
    @CreatedBy
    protected String createdUser;
    //modified user
    @LastModifiedBy
    protected String modifiedUser;



    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }




}
