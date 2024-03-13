package com.zonni.model;

public class Client {
    protected int ID;
    private static int COUNTER;
    protected String name;
    protected String CPF;
    protected String birthday;

    public Client(String name, String cpf, String birthday) {
        this.name = name;
        this.CPF = cpf;
        this.birthday = birthday;
        this.ID = Client.COUNTER;
        Client.COUNTER++;
    }

    public String getCPF() {
        return this.CPF;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthday() {
        return this.birthday;
    }

    @Override
    public String toString() {
        return "Client with id: " + this.ID + " and Name: " + this.name + " [" + this.getCPF() + "]";
    }
}
