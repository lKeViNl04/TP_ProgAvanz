package ar.edu.davinci.Backend.Excepion;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Usuario o contraseña incorrectos");
    }
}
