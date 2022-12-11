package com.example.demoJdbcConnectionOutputOnPostman.Service;

import com.example.demoJdbcConnectionOutputOnPostman.Entity.EmployeeEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

public interface EmployeeServiceImplement {
    public EmployeeEntity saveData(EmployeeEntity employeeEntity);

    public Map<String,Object> fetchAllData();

    public Map<String,Object> fetchOnlyData(@RequestParam("empName") String empName);

    public Map<String,Object> updateSpecificData(@RequestParam(value = "empId",required = false) Long empId,
                                                 @RequestParam(value = "empAge",required = false) Long empAge);

    public Map<String,Object> deleteSpecificData(@RequestParam(value = "empId",required = false) Long empId);
}
