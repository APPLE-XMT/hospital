package com.xhu.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "position_name")
    private String positionName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return position_name
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * @param positionName
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}