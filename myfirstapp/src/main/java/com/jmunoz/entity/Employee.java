package com.jmunoz.entity;

public class Employee {

    private final long id;
    private final String name;
    private final String address;
    private final String phone;
    private final String profession;

    public Employee(long id, String name, String address, String phone, String profession) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfession() {
        return profession;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", profession='" + profession + "'";
    }
}
