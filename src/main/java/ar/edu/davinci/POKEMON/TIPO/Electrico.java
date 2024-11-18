package ar.edu.davinci.POKEMON.TIPO;

import ar.edu.davinci.BONUS.Bonus;
import ar.edu.davinci.BONUS.Low;
import ar.edu.davinci.POKEMON.Pokemon;

public class Electrico extends Tipo {

    public Electrico(){}


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
