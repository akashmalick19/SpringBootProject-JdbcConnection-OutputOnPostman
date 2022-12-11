package com.example.demoJdbcConnectionOutputOnPostman.Service;

import com.example.demoJdbcConnectionOutputOnPostman.Entity.EmployeeEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperImplement implements RowMapper<EmployeeEntity> {
    @Override
    public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeEntity employeeEntity=new EmployeeEntity();
        /* order is followed  by Data Base column */
        employeeEntity.setEmpId(rs.getLong(1));
        employeeEntity.setEmpAge(rs.getLong(2));
        employeeEntity.setEmpName(rs.getString(3));
        return employeeEntity;
    }
}
