package ar.edu.davinci.POKEMON.TIPO;

import ar.edu.davinci.BONUS.Bonus;
import ar.edu.davinci.BONUS.Low;
import ar.edu.davinci.BONUS.LowMedium;
import ar.edu.davinci.POKEMON.Pokemon;

public class Fuego extends Tipo {
    public Fuego(){}

    @Override
    public void atacar(Pokemon atacante, Pokemon defensor) {
        defensor.serAtacadoPorFuego(atacante);
    }

    @Override
    public void serAtacadoPorTipoAgua(Pokemon atacante, Pokemon defensor) {
        Bonus LowMedium = new LowMedium();
        float vidarestante = atacante.getPoder() + LowMedium.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);
    }

    @Override
    public void serAtacadoPorTipoPiedra(Pokemon atacante, Pokemon defensor) {
        Bonus Low = new Low();
        float vidarestante = atacante.getPoder() + Low.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);
    }

    @Override
    public String toString() {
        return "Fuego";
    }

}
