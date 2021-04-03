package com.spring_mvn.dao;

import com.spring_mvn.dao.row_mapper.EmployeeRowMapper;
import com.spring_mvn.entity.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//@Component("employee")
public class EmployeeDaoImpl implements EmployeeDao {
    //    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Employee employee) {
        String sql = "insert into Employee values(?,?,?)";
        return jdbcTemplate.update(sql, employee.getId(), employee.getFirstName(), employee.getLastName());
    }

    @Override
    public int update(Employee employee) {
        String sql = "update Employee set firstname=?, lastname=? where id=?";
        return jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getId());
    }

    @Override
    public int delete(Employee employee) {
        String sql = "delete from Employee where id=?";
        return jdbcTemplate.update(sql, employee.getId());
    }

    @Override
    public Employee read(int id) {
        String sql = "select * from Employee where id=?";
        EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();
        return jdbcTemplate.queryForObject(sql, employeeRowMapper, id);
    }

    @Override
    public List<Employee> realAll() {
        String sql = "select * from Employee";
        EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();
        return jdbcTemplate.query(sql, employeeRowMapper);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
