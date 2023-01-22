package org.example.config.model;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int jobid;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int age, String email, int jobid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.jobid = jobid;
    }

    public Employee(Long id, String firstName, String lastName, int age, String email, int jobid) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.jobid = jobid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "\nid=" + id +
                "\n firstName='" + firstName +
                "\n lastName='" + lastName +
                "\n age=" + age +
                "\n email='" + email +
                "\n jobid=" + jobid +
                '}';
    }
}
