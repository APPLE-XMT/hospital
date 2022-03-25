package com.xhu.entity;

import javax.persistence.*;

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_phone")
    private String departmentPhone;

    @Column(name = "department_description")
    private String departmentDescription;

    /**
     * 1表示已删除，0表示未删除
     */
    @Column(name = "is_enabled")
    private Integer isEnabled;

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
     * @return department_name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return
     */
    public String getDepartmentPhone() {
        return departmentPhone;
    }

    /**
     *
     * @param departmentPhone
     */
    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    /**
     * @return department_description
     */
    public String getDepartmentDescription() {
        return departmentDescription;
    }

    /**
     * @param departmentDescription
     */
    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    /**
     * 获取1表示已删除，0表示未删除
     *
     * @return is_enabled - 1表示已删除，0表示未删除
     */
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置1表示已删除，0表示未删除
     *
     * @param isEnabled 1表示已删除，0表示未删除
     */
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
}