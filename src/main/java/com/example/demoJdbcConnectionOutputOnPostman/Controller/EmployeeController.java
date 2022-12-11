package com.example.demoJdbcConnectionOutputOnPostman.Controller;

import com.example.demoJdbcConnectionOutputOnPostman.Entity.EmployeeEntity;
import com.example.demoJdbcConnectionOutputOnPostman.Service.EmployeeService;
import com.example.demoJdbcConnectionOutputOnPostman.Service.EmployeeServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController implements EmployeeServiceImplement{

    @Autowired
    private EmployeeService employeeService;

    @Override
    //@PostMapping("/saveData")
    @RequestMapping(value = "/saveData",method = RequestMethod.POST)
    public EmployeeEntity saveData(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.saveData(employeeEntity);
    }

    @Override
   // @GetMapping("/showAll")
    @RequestMapping(value = "/showAll",method = RequestMethod.GET)
    public Map<String,Object> fetchAllData(){
        return employeeService.fetchAllData();
    }

    @Override
   // @GetMapping("/showSpecificData")
    @RequestMapping(value = "/showSpecificData",method = RequestMethod.GET)
    public Map<String, Object> fetchOnlyData(String empName) {
        if(StringUtils.hasText(empName)){
            return employeeService.fetchOnlyData(empName);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("Status ", HttpStatus.BAD_REQUEST);
        map.put("Message ","empName should not be Null.");
        return map;
    }

    /* Its very important logic */
    @Override
    //@PutMapping("/update")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Map<String, Object> updateSpecificData(Long empId, Long empAge) {
        Map<String,Object> map=new HashMap<>();
        try{
            if(empId==null || empAge==null) {
                throw new NullPointerException();
            }
            return employeeService.updateSpecificData(empId, empAge);
        }
       catch (NullPointerException e){
           map.put("Status ", HttpStatus.BAD_REQUEST);
           map.put("Message ",e.toString() + " empId and empAge should not be Null.");
           return map;
       }
    }

    @Override
    //@DeleteMapping("/deleteSpecificData")
    @RequestMapping(value = "/deleteSpecificData",method = RequestMethod.DELETE)
    public Map<String, Object> deleteSpecificData(Long empId) {
        Map<String,Object> map=new HashMap<>();
        //** inside @requestParam "required = false" should be needed because of here empId is Long type,
        // otherwise give an error when checking this condition.
        //method declaration is in the EmployeeServiceImplementation interface.
        try{
            if(empId==null){
                throw new NullPointerException();
            }
            return employeeService.deleteSpecificData(empId);
        }
        catch (NullPointerException e){
            map.put("Status ", HttpStatus.BAD_REQUEST);
            map.put("Message ",e.toString() + " empId should not be Null.");
            return map;
        }
    }

}
