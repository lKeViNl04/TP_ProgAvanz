package ar.edu.davinci.POKEMON.TIPO;

import java.util.ArrayList;
import java.util.List;

public class TipoFactory {

    private List<Tipo> tiposRegistrados;
    private static TipoFactory instance;

    private TipoFactory (){
        tiposRegistrados = new ArrayList<>();
        tiposRegistrados.add(new Agua());
        tiposRegistrados.add(new Fuego());
        tiposRegistrados.add(new Planta());
        tiposRegistrados.add(new Piedra());
        tiposRegistrados.add(new Electrico());
    }


    public static TipoFactory getInstance() {
        if ( instance == null){
            instance = new TipoFactory();
        }
        return instance;
    }

    public  List<Tipo> obtenerlist() {
        return tiposRegistrados;
    }

    public  Tipo crearInstancia(String p_tipo) {
        return tiposRegistrados.stream()
                .filter(tipo -> tipo.estipo(p_tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo no reconocido: " + p_tipo));

    }

    public void agregartipo (Tipo p_tipo) {

        if(existeTipo(p_tipo)) {
            throw new IllegalArgumentException("Tipo ya existe");
        }
        tiposRegistrados.add(p_tipo);
    }

    private boolean existeTipo (Tipo p_tipo) {
        return tiposRegistrados.stream().anyMatch(tipo -> tipo.estipo(p_tipo.getNombre()));
    }



}
