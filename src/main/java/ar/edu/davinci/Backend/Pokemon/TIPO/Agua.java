package ar.edu.davinci.Backend.Pokemon.TIPO;

import ar.edu.davinci.Backend.Bonus.Bonus;
import ar.edu.davinci.Backend.Bonus.Low;
import ar.edu.davinci.Backend.Bonus.Medium;
import ar.edu.davinci.Backend.Bonus.Minimal;
import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Agua extends Tipo {

    public Agua(){
        super("Agua");
    }

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
