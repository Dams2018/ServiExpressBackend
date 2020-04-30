package com.serviexpress.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLES")
public class UserRole implements Serializable {



    @Id
    private Long userid;

    private Long roleid;
    public UserRole() {
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }


}