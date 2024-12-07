package ar.edu.davinci.Frontend.Manager;

import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;

public class SessionManager {

    private User currentUser;
    private Trainer selectedTrainer;
    private static SessionManager instance;

    private SessionManager() {}


    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setUser(User user) {
        currentUser = user;
    }

    public User getUser() {
        return currentUser;
    }

    public void setTrainer(Trainer trainer) {
        selectedTrainer = trainer;
    }

    public Trainer getTrainer() {
        return selectedTrainer;
    }

    public void clearSession() {
        currentUser = null;
        selectedTrainer = null;
    }
}

