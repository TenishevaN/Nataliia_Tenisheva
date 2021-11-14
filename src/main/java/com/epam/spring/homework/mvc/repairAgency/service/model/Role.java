package com.epam.spring.homework.mvc.repairAgency.service.model;


public enum Role {
    ADMIN(1), MANAGER(2), MASTER(3), USER(4);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId - 1];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
