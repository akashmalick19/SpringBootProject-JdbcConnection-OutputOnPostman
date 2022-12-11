package com.example.demoJdbcConnectionOutputOnPostman.Service;

import com.example.demoJdbcConnectionOutputOnPostman.Entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeEntity saveData(EmployeeEntity employeeEntity) {
        String sql="insert into employee_entity values(?,?,?)"; //** order is followed by Data base Table wise.
        jdbcTemplate.update(sql,employeeEntity.getEmpId(),employeeEntity.getEmpAge(),employeeEntity.getEmpName());
        return employeeEntity;
    }

    public Map<String, Object> fetchAllData() {
        Map<String,Object> map=new HashMap<>();
        String sql="select * from employee_entity";
        try {
            List<EmployeeEntity> employeeEntityList=jdbcTemplate.query(sql,new RowMapperImplement());
            if (employeeEntityList.isEmpty()){
                throw new Exception();
            }
            map.put("Status ", HttpStatus.FOUND);
            map.put("Message ","All Records are found inside the Data Base.");
            map.put("Objects ",employeeEntityList);
        }
        catch (Exception e){
            map.put("Status ", HttpStatus.NO_CONTENT);
            map.put("Message ",e.toString() + "Data Base is empty.");
        }
        return map;
    }

    public Map<String, Object> fetchOnlyData(String empName) {
        Map<String,Object> map=new HashMap<>();
        String sql="select * from employee_entity where emp_name=?";
        RowMapper<EmployeeEntity> rowMapper = new RowMapperImplement();
        try{
            List<EmployeeEntity> employee = jdbcTemplate.query(sql,rowMapper,empName);
            if(employee.isEmpty()){
                throw new Exception();
            }
            map.put("Status ", HttpStatus.FOUND);
            map.put("Message ","Specific Record is found inside the Data Base.");
            map.put("Objects ",employee);
        }
        catch(Exception e){
            map.put("Status ", HttpStatus.NOT_FOUND);
            map.put("Message ",e.toString() + " empName " + empName + " is not present inside Data Base.");
        }
        return  map;
    }

    public Map<String, Object> updateSpecificData(Long empId, Long empAge) {
        Map<String, Object> map = new HashMap<>();
        if (empId <= 0 || empAge <= 0) {
            map.put("Status ", HttpStatus.BAD_REQUEST);
            map.put("Message ", " empId and empAge should not Less than or Equal Zero.");
            return map;
        }
        try {
            String sql = "select * from employee_entity where emp_id=?";
            RowMapper<EmployeeEntity> rowMapper = new RowMapperImplement();
            EmployeeEntity employee = jdbcTemplate.queryForObject(sql, rowMapper, empId);
            if (employee!=null) {
                String sql1="update employee_entity set emp_age=? where emp_id=?";
                jdbcTemplate.update(sql1,empAge,empId);

                EmployeeEntity employee1 = jdbcTemplate.queryForObject(sql, rowMapper, empId);
                map.put("Status ", HttpStatus.OK);
                map.put("Message ","Update is Done.");
                map.put("Object ",employee1);
                return map;
            }
            throw new Exception();
        }
        catch (Exception e) {
            map.put("Status ", HttpStatus.BAD_REQUEST);
            map.put("Message ", e.toString() + " empId " + empId + " is not present inside Data Base,so empAge update does not possible.");
            return map;
        }
    }

    public Map<String, Object> deleteSpecificData(Long empId) {
        Map<String, Object> map = new HashMap<>();
        if (empId <= 0) {
            map.put("Status ", HttpStatus.BAD_REQUEST);
            map.put("Message ", " empId should not Less than or Equal Zero.");
            return map;
        }
        try{
            String sql = "select * from employee_entity where emp_id=?";
            EmployeeEntity employee = jdbcTemplate.queryForObject(sql, new RowMapperImplement(), empId);
            if(employee!=null){
                String sql1="delete from employee_entity where emp_id=?";
                jdbcTemplate.update(sql1,empId);
                map.put("Status ", HttpStatus.OK);
                map.put("Message ","Delete is done.");
                map.put("Object ",employee);
                return map;
            }
            throw new Exception();
        }
        catch (Exception e){
            map.put("Status ", HttpStatus.BAD_REQUEST);
            map.put("Message ", e.toString() + " empId " + empId + " is not present inside Data Base,so deletion does not possible.");
            return map;
        }
    }

}
