package ar.edu.davinci.Backend.Pokemon.TIPO;

import ar.edu.davinci.Backend.Bonus.Bonus;
import ar.edu.davinci.Backend.Bonus.Low;
import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Electrico extends Tipo {

    public Electrico(){
        super("Electrico");
    }


    @Override
    public void atacar(Pokemon atacante, Pokemon defensor) {
        defensor.serAtacadoPorElectrico(atacante);
    }


    @Override
    public void serAtacadoPorTipoPiedra(Pokemon atacante, Pokemon defensor) {
        Bonus Low = new Low();
        float vidarestante =  atacante.getPoder()+Low.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);
    }
    @Override
    public  String toString(){
        return "Electrico";
    }


}
