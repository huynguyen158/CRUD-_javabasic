package com.company.Backend.Entity;

import java.time.LocalDate;

public class Project {
    private int id;
    private String name;
    private int memberSize;
    private Manager manager;
    private LocalDate createdDate;

    public Project(int id, String name, int memberSize, Manager manager, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.memberSize = memberSize;
        this.manager = manager;
        this.createdDate = createdDate;
    }

    public Project(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMemberSize() {
        return memberSize;
    }

    public Manager getManager() {
        return manager;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memberSize=" + memberSize +
                ", manager=" + manager +
                ", createdDate=" + createdDate +
                '}';
    }
}
