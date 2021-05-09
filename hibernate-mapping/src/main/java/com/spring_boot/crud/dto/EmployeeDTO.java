package com.spring_boot.crud.dto;

public class EmployeeDTO {
    private long id;
    private String name;
    private Long aadhaarNumber;
    private String dob;
    private String token;
    private String clientName;
    private String roleName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(long id, String name, Long aadhaarNumber, String dob, String token, String clientName, String roleName) {
        this.id = id;
        this.name = name;
        this.aadhaarNumber = aadhaarNumber;
        this.dob = dob;
        this.token = token;
        this.clientName = clientName;
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(Long aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aadhaarNumber=" + aadhaarNumber +
                ", dob='" + dob + '\'' +
                ", token='" + token + '\'' +
                ", clientName='" + clientName + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
