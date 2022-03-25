package com.xhu.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "medical_record")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name="record_name")
    private String recordName;

    @Column(name = "patient_name")
    private String patientName;

    private String history;

    private String symptom;

    private String preason;

    private String prescription;

    private String advice;

    @Column(name = "image_description")
    private String imageDescription;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "record_time")
    private Date recordTime;

    private String image;
}