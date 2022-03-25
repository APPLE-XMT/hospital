package com.xhu.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "doctor_name")
    private String doctorName;

    private String sex;

    private Integer age;

    private String card;

    private String phone;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "position_id")
    private Integer positionId;

    @Column(name = "work_time")
    private Integer workTime;
}