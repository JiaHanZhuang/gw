package com.zjh.website.pojo;

/**
 * @author zjh
 * @date 2018/11/14
 */
public class ApplyMessageNumber {

    private String department;

    private Long number;

    private int sex;

    public ApplyMessageNumber() {
    }

    public ApplyMessageNumber(int sex, Long number) {
        this.number = number;
        this.sex = sex;
    }

    public ApplyMessageNumber(String department, Long number) {
        this.department = department;
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
