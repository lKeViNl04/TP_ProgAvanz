package ar.edu.davinci.Backend.User;

import ar.edu.davinci.Backend.Trainer.Entrenador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {


    private String nombre;
    private String apellido;
    private String email;
    private String nickname;
    private int telefono;
    private final List<Entrenador> entrenadores;

    public User(String p_nombre, String p_apellido, String p_email, String p_nickname, int p_telefono) {
        this.nombre = p_nombre;
        this.apellido = p_apellido;
        this.email = p_email;
        this.nickname = p_nickname;
        this.telefono = p_telefono;
        this.entrenadores= new ArrayList<Entrenador>();
    }
    //GETTERS
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getEmail() {
        return email;
    }
    public String getNickname() {
        return nickname;
    }
    public int getTelefono() {
        return telefono;
    }

    //SETTERS
    public void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }
    public void setApellido(String p_apellido) {
        this.apellido = p_apellido;
    }
    public void setEmail(String p_email) {
        this.email = p_email;
    }
    public void setNickname(String p_nickname) {
        this.nickname = p_nickname;
    }
    public void setTelefono(int p_telefono) {
        this.telefono = p_telefono;
    }

    //COMPORTAMIENTOS

    public void anadirEntrenador(Entrenador entrenador){
        if(entrenadores.size()<3){
            entrenadores.add(entrenador);
        }else {
            System.out.println("No se puede añadir mas entrenadores");
        }
    }

    public void mostrarEntrenadores() {
        System.out.println("Lista de Entrenadores de " + getNombre() + " : " + "\n");
        for (Entrenador entrenador : entrenadores) {
            System.out.println(entrenador);
        }
    }

    public void enfrentarseAOtro(User otroUsuario) {

        Entrenador mientrenador = this.elegirEntrenador();
        Entrenador rivalentrenador = otroUsuario.elegirEntrenador();

        mientrenador.enfrentarseAOtro(rivalentrenador);

    }


    public Entrenador elegirEntrenador() {
        Scanner sc = new Scanner(System.in);

        Entrenador entrenador = null;

        do {
            mostrarEntrenadores();

            System.out.println("Elegir pokemon");
            int elegido = sc.nextInt();

            try {

                entrenador = this.entrenadores.get(elegido);
                System.out.println("Entrenador Elegido");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("El índice está fuera del rango. Intenta nuevamente.");
            }

        } while (entrenador == null);

        return entrenador;

    }
}
