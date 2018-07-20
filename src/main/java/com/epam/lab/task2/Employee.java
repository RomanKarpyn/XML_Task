package com.epam.lab.task2;

import java.util.Objects;

public class Employee {

    private int emp_no;
    private String emp_fname;
    private String emp_lname;
    private String dept_no;
    private String emp_live;

    public Employee(){

    }

    public Employee(int emp_no, String emp_fname, String emp_lname, String dept_no, String emp_live) {
        this.emp_no = emp_no;
        this.emp_fname = emp_fname;
        this.emp_lname = emp_lname;
        this.dept_no = dept_no;
        this.emp_live = emp_live;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_fname() {
        return emp_fname;
    }

    public void setEmp_fname(String emp_fname) {
        this.emp_fname = emp_fname;
    }

    public String getEmp_lname() {
        return emp_lname;
    }

    public void setEmp_lname(String emp_lname) {
        this.emp_lname = emp_lname;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getEmp_live() {
        return emp_live;
    }

    public void setEmp_live(String emp_live) {
        this.emp_live = emp_live;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return emp_no == employee.emp_no &&
                Objects.equals(emp_fname, employee.emp_fname) &&
                Objects.equals(emp_lname, employee.emp_lname) &&
                Objects.equals(dept_no, employee.dept_no) &&
                Objects.equals(emp_live, employee.emp_live);
    }

    @Override
    public int hashCode() {

        return Objects.hash(emp_no, emp_fname, emp_lname, dept_no, emp_live);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_no=" + emp_no +
                ", emp_fname='" + emp_fname + '\'' +
                ", emp_lname='" + emp_lname + '\'' +
                ", dept_no='" + dept_no + '\'' +
                ", emp_live='" + emp_live + '\'' +
                '}';
    }
}
