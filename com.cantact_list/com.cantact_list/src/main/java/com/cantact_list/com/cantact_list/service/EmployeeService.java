package com.cantact_list.com.cantact_list.service;


import com.cantact_list.com.cantact_list.domain.Employee;
import com.cantact_list.com.cantact_list.dto.EmployeeDto;
import com.cantact_list.com.cantact_list.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
            Employee employee = new Employee(
                    employeeDto.getId(),
                    employeeDto.getName());

        Employee employee1 =  employeeRepo.save(employee);

        EmployeeDto employeeDto1 = new EmployeeDto(
                employee1.getId(),
                employee1.getName());

            return employeeDto1;

}}
