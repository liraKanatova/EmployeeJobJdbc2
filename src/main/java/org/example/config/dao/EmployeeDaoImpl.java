package org.example.config.dao;

import org.example.config.Util;
import org.example.config.model.Employee;
import org.example.config.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao{

    private Connection connection;
    public EmployeeDaoImpl(){
        this.connection = Util.getConnection();
    }
    public void createEmployee() {
        String create = """
                create table if not exists employees(
                id serial primary key,
                first_name varchar(50) not null,
                last_name varchar(50) not null,
                email varchar unique ,
                job_id int references jobs(id)
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
            System.out.println("Successfully create table employee!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void addEmployee(Employee employee) {
    String add = """
            insert into employees(first_name,last_name,
            age,email,job_id
            );
            """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(add)){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5,employee.getJobid());
            preparedStatement.executeUpdate();
    }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void dropTable() {
        String drop = """
                drop table employees;
                """;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(drop);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void cleanTable() {
        String clean = """
                delete  from employees;
                """;
        try (Statement statement = connection.createStatement()) {
           statement.executeUpdate(clean);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void updateEmployee(Long id, Employee employee) {
        String update = """
                update employees 
                set first_name = ?,
                last_name = ?,
                age = ?,
                email = ?
                job_id = ?,
                WHERE id = ?;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(update)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setLong(5,employee.getJobid());
            preparedStatement.setLong(6,id);
            int i = preparedStatement.executeUpdate();
            if(i>0){
                System.out.println("successfully upated!");
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String all = """
                select * from employees;
                """;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(all);
            while (resultSet.next()) {
                employeeList.add(new Employee(resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")));
            }
            resultSet.close();
            return employeeList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Employee findByEmail(String email) {
        String email1 = """
            select * from employees where email = ?;
            """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(email1)){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = new Employee();
            while (resultSet.next()){
                employee.setId(resultSet.getLong(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobid(resultSet.getInt("job_id"));
            }
            resultSet.close();
        return employee;
    }catch (SQLException e){
            throw new RuntimeException();
        }
}
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        String map = """
                select * from jobs as j join employees e  on e.job_id= j.id where e.id = ?; 
                """;
        Map<Employee,Job> employeeJobMap = new HashMap<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(map)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employeeJobMap.put(new Employee(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")),
                        new Job(resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")));
            }
            resultSet.close();

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return employeeJobMap;
    }

    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employeeList = new ArrayList<>();
        String p = """
                select * from employees as e
                join jobs j on e.job_id = j.id where j.position = ?;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(p)){
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employeeList.add(new Employee(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")));
            }
            resultSet.close();
        }catch (SQLException e){
            throw new RuntimeException();
        }

        return employeeList;
    }
}
