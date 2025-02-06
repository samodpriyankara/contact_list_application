package com.cantact_list.com.cantact_list.controller;

import com.cantact_list.com.cantact_list.domain.Employee;
import com.cantact_list.com.cantact_list.dto.EmployeeDto;
import com.cantact_list.com.cantact_list.service.EmployeeService;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("save")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok().body(employeeService.saveEmployee(employeeDto));
    }
}
