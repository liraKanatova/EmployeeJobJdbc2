package org.example;

import org.example.config.Util;
import org.example.config.model.Employee;
import org.example.config.model.Job;
import org.example.config.service.ServiceEmployee;
import org.example.config.service.ServiceEmployeeImpl;
import org.example.config.service.ServiceJob;
import org.example.config.service.ServiceJobImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //  Util.getConnection();
        ServiceJob serviceJob = new ServiceJobImpl();
        ServiceEmployee serviceEmployee = new ServiceEmployeeImpl();
        while (true) {
            System.out.println("""
                    ~~~~~~~~Job Commands~~~~~~~~
                    1 CreateJobTable!
                    2 AddJob!
                    3 Get JobBy Id!
                    4 Sort By Experience!
                    5 Get Job By Employee Id!
                    6 Delete Description Column!
                    ~~~~~~~Employee Commands~~~~~~
                    7 Create Employee!
                    8 Add Employee!
                    9 Drop Table!
                    10 Clean Table!
                    11 Update Employee!
                    12  Get All Employees!
                    13 Find By Email!
                    14 Get Employee By Id!
                    15 Get Employee By Position!
                    """);
            System.out.println("Enter by command :");
            int a = new Scanner(System.in).nextInt();
            switch (a) {
                case 1-> serviceJob.createJobTable();
             case 2->{
                 System.out.print("Enter write job: ");
                 serviceJob.addJob(new Job("Mentor","Java","Backend developer",1));
                  serviceJob.addJob(new Job ( "Mentor","JavaScript","Fronted developer",2));
                  serviceJob.addJob(new Job  ("Management","Java and JavaScrip","Management",3));
                  serviceJob.addJob(new Job ("Instructor","Java","Backend developer",1));
                  serviceJob.addJob(new Job("Mentor","Java","Backend developer",1));
             }
             case 3->{ System.out.print("Write the job id: ");
                 long id = new Scanner(System.in).nextLong();
                 System.out.println(serviceJob.getJobById(id));
             }
             case 4->{  System.out.print("Write the asc or desc: ");
                 String ascOrDesc = new Scanner(System.in).next();
                 System.out.println(serviceJob.sortByExperience(ascOrDesc));

             }
             case 5->{
                 System.out.print("Write the employee id: ");
                 Long id = new Scanner(System.in).nextLong();
                 System.out.println(serviceJob.getJobByEmployeeId(id));
             }
             case 6->serviceJob.deleteDescriptionColumn();
             case 7->serviceEmployee.createEmployee();
             case 8->{serviceEmployee.addEmployee (new Employee("Mukhammed","Asantegin",22,"Mukhamed@gmail.com",1));
                    serviceEmployee.addEmployee (new Employee("Nargiza","Rysbek",19,"Nargiza@gmail.com",2));
                    serviceEmployee.addEmployee (new Employee("Aizhan","Nurmatova",20,"Aizhan@gmail.com",3));
                    serviceEmployee.addEmployee(new Employee("Aijamal","Asangazieva",18,"Aijamal@gmai.com",4));
                    serviceEmployee.addEmployee( new Employee("Rahim","Bazarbai uulu",19,"Rahim@gmail.com",5));
            }
                case 9 -> serviceEmployee.dropTable();
                case 10 -> serviceEmployee.cleanTable();
                case 11->{ System.out.print("Write the id and that which you want to change: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.print("Write the firstName: ");
                    String firstName = new Scanner(System.in).next();
                    System.out.print("Write the lastName: ");
                    String lastName = new Scanner(System.in).next();
                    System.out.print("Write the age: ");
                    int age = new Scanner(System.in).nextInt();
                    System.out.print("Write the email: ");
                    String email = new Scanner(System.in).next();
                    System.out.print("Write the jobId: ");
                    int jobId = new Scanner(System.in).nextInt();
                    serviceEmployee.updateEmployee(id, new Employee(firstName, lastName, age, email, jobId));
                }
                case 12->System.out.println(serviceEmployee.getAllEmployees());
                case 13->{ System.out.print("Write the email: ");
                    String email = new Scanner(System.in).next();
                    System.out.println(serviceEmployee.findByEmail(email));
                }
                case 14->{ System.out.print("Write the id: ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println(serviceEmployee.getEmployeeById(id));
                }
                case 15->{ System.out.print("Write the position: ");
                    String position = new Scanner(System.in).next();
                    System.out.println(serviceEmployee.getEmployeeByPosition(position));

                }

            }
        }
    }
}