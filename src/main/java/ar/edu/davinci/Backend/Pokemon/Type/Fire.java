package ar.edu.davinci.Backend.Pokemon.Type;

import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Fire extends Type {
    public Fire(){
        super("Fuego");
    }

    @Override
    public float atacar(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorFuego(atacante);
    }

    @Override
    public float serAtacadoPorTipoAgua(Pokemon atacante, Pokemon defensor) {

        float danioCausado = atacante.getPoder() + (0.25F*atacante.getPoder());
        defensor.restarVida(danioCausado);
        return danioCausado;
    }

    @Override
    public String toString() {
        return "Fuego";
    }

}
