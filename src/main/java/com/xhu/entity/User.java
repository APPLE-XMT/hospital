package com.xhu.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String username;

    private String password;

    @Column(name = "real_name")
    private String realName;

    private String card;

    private String phone;

    private String email;

    private String address;

    private Integer state;

}