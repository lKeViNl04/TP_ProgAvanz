package ar.edu.davinci.Backend.Excepion;

public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException() {
        super("Usuario o contraseña incorrectos");
    }

}
