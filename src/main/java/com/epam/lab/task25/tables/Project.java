package com.epam.lab.task25.tables;

import java.util.Objects;

public class Project {

    private String project_no;
    private String project_name;
    private int budget;

    public Project(){

    }

    public Project(String project_no, String project_name, int budget) {
        this.project_no = project_no;
        this.project_name = project_name;
        this.budget = budget;
    }

    public String getProject_no() {
        return project_no;
    }

    public void setProject_no(String project_no) {
        this.project_no = project_no;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return budget == project.budget &&
                Objects.equals(project_no, project.project_no) &&
                Objects.equals(project_name, project.project_name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(project_no, project_name, budget);
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_no='" + project_no + '\'' +
                ", project_name='" + project_name + '\'' +
                ", budget=" + budget +
                '}';
    }
}
