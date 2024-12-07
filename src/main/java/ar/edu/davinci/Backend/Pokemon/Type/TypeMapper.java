package ar.edu.davinci.Backend.Pokemon.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeMapper {

    private List<Type> tiposRegistrados;
    private static TypeMapper instance;

    private TypeMapper(){
        tiposRegistrados = new ArrayList<>();
        tiposRegistrados.add(new Water());
        tiposRegistrados.add(new Fire());
        tiposRegistrados.add(new Plant());
        tiposRegistrados.add(new Stone());
        tiposRegistrados.add(new Electric());
    }


    public static TypeMapper getInstance() {
        if ( instance == null){
            instance = new TypeMapper();
        }
        return instance;
    }

    public  List<Type> obtenerlist() {
        return tiposRegistrados;
    }

    public Type crearInstancia(String p_tipo) {
        return tiposRegistrados.stream()
                .filter(Type -> Type.estipo(p_tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo no reconocido: " + p_tipo));

    }

    public void agregartipo (Type p_type) {

        if(existeTipo(p_type)) {
            throw new IllegalArgumentException("Tipo ya existe");
        }
        tiposRegistrados.add(p_type);
    }

    private boolean existeTipo (Type p_type) {
        return tiposRegistrados.stream().anyMatch(tipo -> tipo.estipo(p_type.getNombre()));
    }



}
