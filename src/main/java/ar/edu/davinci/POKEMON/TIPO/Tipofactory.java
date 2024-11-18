package ar.edu.davinci.POKEMON.TIPO;

import java.util.ArrayList;
import java.util.List;

public class Tipofactory {

    private static final List<Tipo> tiposRegistrados = new ArrayList<>();

     static  {
         tiposRegistrados.add(new Agua());
         tiposRegistrados.add(new Fuego());
         tiposRegistrados.add(new Planta());
         tiposRegistrados.add(new Piedra());
         tiposRegistrados.add(new Electrico());
    }


    public static List<Tipo> obtenerlist() {
        return tiposRegistrados;
    }

    public static Tipo crearInstancia(String p_tipo) {
        return tiposRegistrados.stream()
                .filter(tipo -> tipo.toString().equalsIgnoreCase(p_tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo no reconocido: " + p_tipo));
    }



}
