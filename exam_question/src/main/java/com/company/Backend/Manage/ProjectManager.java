package com.company.Backend.Manage;

import com.company.Backend.Entity.Department;
import com.company.Backend.Entity.Project;
import com.company.Backend.Repository.DepartmentRepository;
import com.company.Backend.Repository.ProjectRepository;

import java.sql.SQLException;
import java.util.List;

public class ProjectManager {
    private ProjectRepository projectRepository;

    public ProjectManager() {
        projectRepository = new ProjectRepository();
    }

    public void getAllProject() throws SQLException {
        System.out.println("=================GET ALL DEPARTMENT================");
        List<Project> projects = projectRepository.getAllProject();
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    public void printProjectDetails(int projectId) throws SQLException {
        System.out.println("Your current project: ");
        Project project = projectRepository.getDetailProjectId(projectId);
        System.out.println(project);
        }
    }


