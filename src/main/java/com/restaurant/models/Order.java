package com.restaurant.models;

import java.time.LocalDateTime;

public class Order {
    private Integer id;
    private LocalDateTime dateTime;
    private Integer tableId;
    private Status status;
    private Integer billId;
    private Integer employeeId;
    private Integer personId;

    public Order() {
        super();
    }

    public Order(Integer id, LocalDateTime dateTime, Integer tableId, Status status, Integer billId, Integer employeeId, Integer personId) {
        this.id = id;
        this.dateTime = dateTime;
        this.tableId = tableId;
        this.status = status;
        this.billId = billId;
        this.employeeId = employeeId;
        this.personId = personId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getStatusId() {
        return status.getStatusId();
    }

    public String getStatus() {
        return status.getName();
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
