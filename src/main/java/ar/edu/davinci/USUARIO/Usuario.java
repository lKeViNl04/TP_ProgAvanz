package ar.edu.davinci.USUARIO;

public class Usuario {


    private String nombre;
    private String apellido;
    private String email;
    private String nickname;
    private int telefono;

    public Usuario(String p_nombre, String p_apellido, String p_email, String p_nickname, int p_telefono) {
        this.nombre = p_nombre;
        this.apellido = p_apellido;
        this.email = p_email;
        this.nickname = p_nickname;
        this.telefono = p_telefono;
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









}
