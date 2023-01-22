package org.example.config.service;

import org.example.config.dao.EmployeeDao;
import org.example.config.dao.EmployeeDaoImpl;
import org.example.config.model.Employee;
import org.example.config.model.Job;

import java.util.List;
import java.util.Map;

public class ServiceEmployeeImpl implements ServiceEmployee{
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    public void createEmployee() {
       employeeDao.createEmployee();
    }

    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

    }

    public void dropTable() {
        employeeDao.dropTable();

    }

    public void cleanTable() {
        employeeDao.cleanTable();

    }

    public void updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id, employee);

    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        employeeDao.getEmployeeById(employeeId);
        return null;
    }

    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
