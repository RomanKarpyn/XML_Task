package com.epam.lab.task25.tables;

import java.sql.Date;
import java.util.Objects;

public class WorksOn {

    private int emp_no;
    private String project_no;
    private String job;
    private Date enter_date;

    public WorksOn(){

    }

    public WorksOn(int emp_no, String project_no, String job, Date enter_date) {
        this.emp_no = emp_no;
        this.project_no = project_no;
        this.job = job;
        this.enter_date = enter_date;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getProject_no() {
        return project_no;
    }

    public void setProject_no(String project_no) {
        this.project_no = project_no;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getEnter_date() {
        return enter_date;
    }

    public void setEnter_date(Date enter_date) {
        this.enter_date = enter_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorksOn works_on = (WorksOn) o;
        return emp_no == works_on.emp_no &&
                Objects.equals(project_no, works_on.project_no) &&
                Objects.equals(job, works_on.job) &&
                Objects.equals(enter_date, works_on.enter_date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(emp_no, project_no, job, enter_date);
    }

    @Override
    public String toString() {
        return "WorksOn{" +
                "emp_no=" + emp_no +
                ", project_no='" + project_no + '\'' +
                ", job='" + job + '\'' +
                ", enter_date=" + enter_date +
                '}';
    }
}
