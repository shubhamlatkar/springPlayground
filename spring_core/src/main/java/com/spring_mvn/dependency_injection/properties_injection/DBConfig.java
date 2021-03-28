package com.spring_mvn.dependency_injection.properties_injection;

public class DBConfig {
    private String dbUrl;

    public DBConfig() {
    }

    public DBConfig(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public String toString() {
        return "DBConfig{" +
                "dbUrl='" + dbUrl + '\'' +
                '}';
    }
}
