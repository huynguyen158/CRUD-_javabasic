package com.company.Backend.Entity;

import java.time.LocalDate;

public class Department {
    private int id;
    private String name;
    private int memberSize;
    private LocalDate createdDate;

    public Department(int id, String name, int memberSize, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.memberSize = memberSize;
        this.createdDate = createdDate;
    }

    public Department(String name) {
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memberSize=" + memberSize +
                ", createdDate=" + createdDate +
                '}';
    }
}
