package com.spring_mvn.spring_jdbc.dao.row_mapper;

import com.spring_mvn.spring_jdbc.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
    }
}
