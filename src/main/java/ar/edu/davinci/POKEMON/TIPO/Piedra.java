package ar.edu.davinci.POKEMON.TIPO;

import ar.edu.davinci.BONUS.Bonus;
import ar.edu.davinci.BONUS.Dummy;
import ar.edu.davinci.BONUS.LowMedium;
import ar.edu.davinci.POKEMON.Pokemon;

public class Piedra extends Tipo {
    public Piedra(){
        super("Piedra");
    }

    @Override
    public void atacar(Pokemon atacante, Pokemon defensor) {
        defensor.serAtacadoPorPiedra(atacante);
    }

    @Override
    public void serAtacadoPorTipoAgua(Pokemon atacante, Pokemon defensor) {
        Bonus Lowmedium = new LowMedium();
        float vidarestante = atacante.getPoder()+Lowmedium.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);
    }

    @Override
    public void serAtacadoPorTipoPlanta(Pokemon atacante, Pokemon defensor) {
        Bonus Dummy = new Dummy();
        float vidarestante = Dummy.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);

    }

    @Override
    public String toString() {
        return "Piedra";
    }
}
