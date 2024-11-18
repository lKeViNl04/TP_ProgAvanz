package ar.edu.davinci.POKEMON.TIPO;

import ar.edu.davinci.POKEMON.Pokemon;

public abstract class Tipo {

    public  abstract void atacar(Pokemon atacante, Pokemon defensor );

    public  void serAtacadoPorTipoAgua(Pokemon atacante, Pokemon defensor){
        float vidarestante = atacante.getPoder();
        defensor.restarVida(vidarestante);
    };
    public  void serAtacadoPorTipoFuego(Pokemon atacante, Pokemon defensor){
        float vidarestante =atacante.getPoder();
        defensor.restarVida(vidarestante);
    };
    public  void serAtacadoPorTipoPiedra(Pokemon atacante, Pokemon defensor){
        float vidarestante =  atacante.getPoder();
        defensor.restarVida(vidarestante);
    };
    public  void serAtacadoPorTipoElectrico(Pokemon atacante, Pokemon defensor){
        float vidarestante =  atacante.getPoder();
        defensor.restarVida(vidarestante);
    };
    public  void serAtacadoPorTipoPlanta(Pokemon atacante, Pokemon defensor) {
        float vidarestante =  atacante.getPoder();
        defensor.restarVida(vidarestante);
    };


    public abstract String toString();
}
