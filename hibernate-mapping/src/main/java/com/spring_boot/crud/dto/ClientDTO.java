package com.spring_boot.crud.dto;

public class ClientDTO {

    private String clientName;
    private String location;

    public ClientDTO() {
    }

    public ClientDTO(String clientName, String location) {
        this.clientName = clientName;
        this.location = location;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "clientName='" + clientName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
