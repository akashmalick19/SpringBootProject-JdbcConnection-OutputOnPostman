package com.example.demoJdbcConnectionOutputOnPostman.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    private Long empId;
    private Long empAge;
    private String empName;

    public EmployeeEntity(Long empId, Long empAge, String empName) {
        this.empId = empId;
        this.empAge = empAge;
        this.empName = empName;
    }

}
