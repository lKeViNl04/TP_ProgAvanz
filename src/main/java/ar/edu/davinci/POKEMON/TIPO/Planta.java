package ar.edu.davinci.POKEMON.TIPO;

import ar.edu.davinci.BONUS.Bonus;
import ar.edu.davinci.BONUS.Low;
import ar.edu.davinci.BONUS.Minimal;
import ar.edu.davinci.POKEMON.Pokemon;

public class Planta extends Tipo {
    public Planta(){}


    @Override
    public void atacar(Pokemon atacante, Pokemon defensor) {
        defensor.serAtacadoPorPlanta(atacante);
    }

    @Override
    public void serAtacadoPorTipoFuego(Pokemon atacante, Pokemon defensor) {
        Bonus Low = new Low();
        float vidarestante =  atacante.getPoder() + Low.calcularbonus(atacante.getPoder()) ;
        defensor.restarVida(vidarestante);
    }

    @Override
    public void serAtacadoPorTipoPiedra(Pokemon atacante, Pokemon defensor) {
        Bonus Minimal = new Minimal();
        float vidarestante = atacante.getPoder() + Minimal.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);
    }

    @Override
    public String toString() {
        return "Planta";
    }


}
