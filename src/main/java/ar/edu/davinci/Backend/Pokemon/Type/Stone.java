package ar.edu.davinci.Backend.Pokemon.Type;

import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Stone extends Type {
    public Stone(){
        super("Piedra");
    }

    @Override
    public float atacar(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorPiedra(atacante);
    }


    @Override
    public float serAtacadoPorTipoPlanta(Pokemon atacante, Pokemon defensor) {
        float danioCausado = 0F * atacante.getPoder();
        defensor.restarVida(danioCausado);
        return danioCausado;

    }

    @Override
    public String toString() {
        return "Piedra";
    }
}
