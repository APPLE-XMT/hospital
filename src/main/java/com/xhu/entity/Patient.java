package com.xhu.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "patient_name")
    private String patientName;

    private String sex;

    private Integer age;

    private String country;

    private String nation;

    private String address;

    private String card;

    private String phone;

    @Column(name = "belong_department")
    private Integer belongDepartment;

    @Column(name = "belong_doctor")
    private Integer belongDoctor;

    @Column(name = "patient_state")
    private String patientState;

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
     * @return patient_name
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return belong_department
     */
    public Integer getBelongDepartment() {
        return belongDepartment;
    }

    /**
     * @param belongDepartment
     */
    public void setBelongDepartment(Integer belongDepartment) {
        this.belongDepartment = belongDepartment;
    }

    /**
     * @return belong_doctor
     */
    public Integer getBelongDoctor() {
        return belongDoctor;
    }

    /**
     * @param belongDoctor
     */
    public void setBelongDoctor(Integer belongDoctor) {
        this.belongDoctor = belongDoctor;
    }

    /**
     * @return patient_state
     */
    public String getPatientState() {
        return patientState;
    }

    /**
     * @param patientState
     */
    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }
}