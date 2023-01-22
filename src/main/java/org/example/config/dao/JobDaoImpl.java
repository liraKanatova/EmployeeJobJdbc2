package org.example.config.dao;

import org.example.config.Util;
import org.example.config.model.Employee;
import org.example.config.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobDaoImpl implements JobDao {
    private Connection connection;

    public JobDaoImpl() {
        this.connection = Util.getConnection();
    }

    public void createJobTable() {
        String create = """
               create table if not exists jobs(
               id serial primary key,
               position varchar not null ,
               profession varchar not null,
               description varchar not null,
               experience smallint not null
               );
               """;
        try(Statement statement = connection.createStatement()){
           statement.executeUpdate(create);
            System.out.println("Successfully create table job!");
    }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void addJob(Job job) {
       String add = """
               insert into jobs(position,profession,description,experience)
               values(?,?,?,?);
               """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(add)){
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4,job.getExperience());
            preparedStatement.executeUpdate();
            System.out.println("Job is successfully saved!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Job getJobById(Long jobId) {
       String get = """
               select * from jobs where id = ?;
               """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(get)){
            preparedStatement.setLong(1,jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Job job = new Job();
           while (resultSet.next()){
                System.out.println("Does not exist!");
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));
            }
            resultSet.close();
            return job;

        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    public List<Job> sortByExperience(String ascOrDesc) {
       List<Job> jobList = new ArrayList<>();
       String sort = null;
            if(ascOrDesc.equals("asc")){
               sort = "select * from jobs order by experience";
        }else if(ascOrDesc.equals("desc")) {
                sort = " select * from order by experience desc";
            }
        try(PreparedStatement preparedStatement = connection.prepareStatement(sort)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                        job.setPosition(resultSet.getString("profession"));
                        job.setDescription(resultSet.getString("description"));
                        job.setExperience(resultSet.getInt("experience"));

                jobList.add(job);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return jobList;
    }

    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        String getEmployeeId = """
               select * from jobs join employees e on e.jobid = jobs.id;
               """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(getEmployeeId)){
            preparedStatement.setLong(1,employeeId);
                ResultSet resultSet = preparedStatement.executeQuery();
              while (resultSet.next()){
                    System.out.println("Does not exist!");
                  job.setId(resultSet.getLong(1));
                  job.setPosition(resultSet.getString(1));
                  job.setProfession(resultSet.getString(2));
                  job.setDescription(resultSet.getString(3));
                  job.setExperience(resultSet.getInt(4));

                }
                resultSet.close();
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }return  job;
    }





    public void deleteDescriptionColumn() {
       String delete = """
               alter table jobs drop column description; 
               """;
        try(Statement statement = connection.createStatement()){
            statement.executeQuery(delete);

    }catch (SQLException e){
            System.out.println(e.getMessage());
        }
}
}