package org.example.config.service;

import org.example.config.model.Employee;
import org.example.config.model.Job;

import java.util.List;
import java.util.Map;

public interface ServiceEmployee {
    void createEmployee();
    void addEmployee(Employee employee);
    void dropTable();
    void cleanTable();
    void updateEmployee(Long id,Employee employee);
    List<Employee> getAllEmployees();
    Employee findByEmail(String email);
    Map<Employee, Job> getEmployeeById(Long employeeId);
    List<Employee> getEmployeeByPosition(String position);

}
