package com.drop.salary.pojo;

import java.math.BigDecimal;

/**
 * @Description: 计算薪水所需参数
 * @Author: Leo
 * @Date: 2018-05-08 下午 3:04
 */
public class SalaryModel {

    private String name;

    private BigDecimal baseSalary = BigDecimal.ZERO;  //基本工资

    private BigDecimal totalTask = BigDecimal.ZERO;   //总计划销售任务

    private BigDecimal realTotal = BigDecimal.ZERO;   //实际销售任务

    private BigDecimal saleTask = BigDecimal.ZERO;  //销售任务

    private BigDecimal realSale = BigDecimal.ZERO;  //实际销售

    private BigDecimal equipSale = BigDecimal.ZERO;  //器材销售

    private BigDecimal lateTime = BigDecimal.ZERO;  //迟到时间(分钟)

    private BigDecimal absent = BigDecimal.ZERO;  //旷工次数

    private BigDecimal workLeave = BigDecimal.ZERO;  //工作日请假次数

    private BigDecimal holidayLeave = BigDecimal.ZERO;  //节假日请假次数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(BigDecimal totalTask) {
        this.totalTask = totalTask;
    }

    public BigDecimal getRealTotal() {
        return realTotal;
    }

    public void setRealTotal(BigDecimal realTotal) {
        this.realTotal = realTotal;
    }

    public BigDecimal getSaleTask() {
        return saleTask;
    }

    public void setSaleTask(BigDecimal saleTask) {
        this.saleTask = saleTask;
    }

    public BigDecimal getRealSale() {
        return realSale;
    }

    public void setRealSale(BigDecimal realSale) {
        this.realSale = realSale;
    }

    public BigDecimal getEquipSale() {
        return equipSale;
    }

    public void setEquipSale(BigDecimal equipSale) {
        this.equipSale = equipSale;
    }

    public BigDecimal getLateTime() {
        return lateTime;
    }

    public void setLateTime(BigDecimal lateTime) {
        this.lateTime = lateTime;
    }

    public BigDecimal getAbsent() {
        return absent;
    }

    public void setAbsent(BigDecimal absent) {
        this.absent = absent;
    }

    public BigDecimal getWorkLeave() {
        return workLeave;
    }

    public void setWorkLeave(BigDecimal workLeave) {
        this.workLeave = workLeave;
    }

    public BigDecimal getHolidayLeave() {
        return holidayLeave;
    }

    public void setHolidayLeave(BigDecimal holidayLeave) {
        this.holidayLeave = holidayLeave;
    }
}
