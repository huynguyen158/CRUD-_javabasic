package com.company.Backend.Entity;

public class Employee {
    private User user;
    private String proSkill;

    public Employee(User user, String proSkill) {
        this.user = user;
        this.proSkill = proSkill;
    }

    public String getProSkill() {
        return proSkill;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "user=" + user +
                ", proSkill='" + proSkill + '\'' +
                '}';
    }
}
