package ar.edu.davinci.POKEMON.TIPO;

import ar.edu.davinci.BONUS.Bonus;
import ar.edu.davinci.BONUS.Low;
import ar.edu.davinci.BONUS.Medium;
import ar.edu.davinci.BONUS.Minimal;
import ar.edu.davinci.POKEMON.Pokemon;

public class Agua extends Tipo {

    public Agua(){}

    @Override
    public void atacar(Pokemon atacante, Pokemon defensor) {
        defensor.serAtacadoPorAgua(atacante);
    }

    @Override
    public void serAtacadoPorTipoElectrico(Pokemon atacante, Pokemon defensor) {
        Bonus medium = new Medium();
        Bonus minimal = new Minimal();

        float vidarestante = atacante.getPoder()+medium.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);

        float dañodevuelta = minimal.calcularbonus(atacante.getPoder());
        atacante.restarVida(dañodevuelta);

    }

    @Override
    public void serAtacadoPorTipoPlanta(Pokemon atacante, Pokemon defensor) {
        Bonus Low = new Low();
        float vidarestante = atacante.getPoder()+Low.calcularbonus(atacante.getPoder());
        defensor.restarVida(vidarestante);
    }

    @Override
    public String toString() {
        return "Agua";
    }
}
