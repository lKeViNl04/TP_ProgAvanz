package ar.edu.davinci.Backend.Pokemon.Type;

import ar.edu.davinci.Backend.Pokemon.Pokemon;

public abstract class Type {

    private String nombre;

    public Type(String p_nombre) {
        nombre = p_nombre;
    }

    public boolean estipo(String tipo) {
        return nombre.equalsIgnoreCase(tipo);
    }

    public  abstract float atacar(Pokemon atacante, Pokemon defensor );

    public float autoDanio(Pokemon atacante,Pokemon defensor){
        return  0;
    };

    public  float serAtacadoPorTipoAgua(Pokemon atacante, Pokemon defensor){
        float danioCausado = atacante.getPoder();
        defensor.restarVida(danioCausado);
        return  danioCausado;
    };
    public  float serAtacadoPorTipoFuego(Pokemon atacante, Pokemon defensor){
        float danioCausado =atacante.getPoder();
        defensor.restarVida(danioCausado);
        return danioCausado;
    };
    public  float serAtacadoPorTipoPiedra(Pokemon atacante, Pokemon defensor){
        float danioCausado =  atacante.getPoder();
        defensor.restarVida(danioCausado);
        return  danioCausado;
    };
    public  float serAtacadoPorTipoElectrico(Pokemon atacante, Pokemon defensor){
        float danioCausado =  atacante.getPoder();
        defensor.restarVida(danioCausado);
        return  danioCausado;
    };
    public  float serAtacadoPorTipoPlanta(Pokemon atacante, Pokemon defensor) {
        float danioCausado =  atacante.getPoder();
        defensor.restarVida(danioCausado);
        return  danioCausado;
    };

    public float recibirDanioPorAtacarAgua(Pokemon p_atacante){
        return 0;
    }


    public  String getNombre(){
        return nombre;
    };
}
