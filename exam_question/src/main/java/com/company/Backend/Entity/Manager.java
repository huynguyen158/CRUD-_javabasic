package com.company.Backend.Entity;

public class Manager {
    private User user;
    private String expInYear;

    public Manager(User user, String expInYear) {
        this.user = user;
        this.expInYear = expInYear;
    }
    public Manager(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getExpInYear() {
        return expInYear;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "user=" + user +
                ", expInYear='" + expInYear + '\'' +
                '}';
    }
}
