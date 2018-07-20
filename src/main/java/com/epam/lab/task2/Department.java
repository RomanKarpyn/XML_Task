package com.epam.lab.task2;

import java.util.Objects;

public class Department {

    private String dept_no;
    private String dept_name;
    private String location;

    public Department(String dept_no, String dept_name, String location) {
        this.dept_no = dept_no;
        this.dept_name = dept_name;
        this.location = location;
    }

    public void setDept_no(String dept_no) {

        this.dept_no = dept_no;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDept_no() {

        return dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dept_no='" + dept_no + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(dept_no, that.dept_no) &&
                Objects.equals(dept_name, that.dept_name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dept_no, dept_name, location);
    }
}
