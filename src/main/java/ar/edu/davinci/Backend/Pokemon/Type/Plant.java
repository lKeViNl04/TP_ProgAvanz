package ar.edu.davinci.Backend.Pokemon.Type;

import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Plant extends Type {
    public Plant(){
        super("Planta");
    }


    @Override
    public float atacar(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorPlanta(atacante);
    }

    @Override
    public float serAtacadoPorTipoFuego(Pokemon atacante, Pokemon defensor) {
        float danioCausado =  atacante.getPoder() +(0.20f * atacante.getPoder()) ;
        defensor.restarVida(danioCausado);
        return danioCausado;
    }



}
