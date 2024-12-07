package ar.edu.davinci.Backend.User;

import ar.edu.davinci.Backend.Trainer.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    private int userId;
    private String name;
    private String surname;
    private String email;
    private String nickname;
    private String password;
    private final List<Trainer> trainers;

    public User(String P_name, String P_surname, String P_email, String P_nickname, String P_password) {
        this.name = P_name;
        this.surname = P_surname;
        this.email = P_email;
        this.nickname = P_nickname;
        this.password = P_password;
        this.trainers = new ArrayList<Trainer>();
    }

    public User(int P_id, String P_name, String P_surname, String P_email, String P_nickname, String P_password) {
        this.userId = P_id;
        this.name = P_name;
        this.surname = P_surname;
        this.email = P_email;
        this.nickname = P_nickname;
        this.password = P_password;
        this.trainers = new ArrayList<Trainer>();
    }

    public int getUserId() {
        return this.userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setname(String P_name) {
        this.name = P_name;
    }

    public void setsurname(String P_surname) {
        this.surname = P_surname;
    }

    public void setEmail(String P_email) {
        this.email = P_email;
    }

    public void setNickname(String P_nickname) {
        this.nickname = P_nickname;
    }

    public void setPassword(String P_password) {
        this.password = P_password;
    }


    public void addEntrenador(Trainer trainer) {
        if (trainers.size() < 3) {
            trainers.add(trainer);
        }
    }

    public void addEntrenadores(List<Trainer> trainers) {
        this.trainers.addAll(trainers);
    }

    public void mostrartrainers() {
        System.out.println("Lista de trainers de " + getName() + " : " + "\n");
        for (Trainer trainer : trainers) {
            System.out.println(trainer);
        }
    }


}
