package ar.edu.davinci.Backend.Pokemon.TIPO;

import ar.edu.davinci.Backend.Bonus.Bonus;
import ar.edu.davinci.Backend.Bonus.Low;
import ar.edu.davinci.Backend.Bonus.Minimal;
import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Planta extends Tipo {
    public Planta(){
        super("Planta");
    }


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


}
