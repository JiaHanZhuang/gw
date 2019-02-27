package com.zjh.website.pojo;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author zjh
 *@date 2018.10.08
 */

@Entity
@Table(name = "apply_message")
public class ApplyMessage {

    /**
     * 标示id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @Basic
    @Column(length = 20)
    @NotNull
    private String name;

    /**
     * 性别,0为女性，1位男性
     */
    @Basic
    @Column(length = 2)
    @Min(value = 0)
    @Max(value = 1)
    private Integer sex;

    /**
     * 专业
     */
    @Basic
    @Column(length = 20)
    private String profession;

    /**
     * 手机号码
     */
    @Basic
    @Column(length = 25)
    @Pattern(regexp = "^((13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])+\\d{8})$",message = "{apply.phone}")
    private String phone;

    /**
     * 邮箱
     */
    @Basic
    @Column(length = 30)
    @Email(regexp = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$",message = "{apply.email}")
    private String email;

    /**
     * qq号码
     */
    @Basic
    @Column(length = 25)
    private String qq;

    /**
     * 报名部门
     */
    @Basic
    @Column(length = 20)
    private String department;

    /**
     * 简介
     */
    @Basic
    @Column(length = 255)
    private String resume;

    /**
     * 通过标识，0为不通过，1为通过
     */
    @Basic
    @Column(length = 2)
    @Min(value = 0)
    @Max(value = 1)
    private Integer mark;

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq() {
        return qq;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getResume() {
        return resume;
    }

    @Override
    public String toString() {
        return "{"+this.id+","+this.name+","+this.department+","+this.sex+","+this.email+","
                +this.phone+","+this.profession+","+this.qq+","+this.resume+","+this.mark+"}";
    }
}
