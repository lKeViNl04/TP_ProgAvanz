package ar.edu.davinci.Backend.Pokemon.Type;

import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Electric extends Type {

    public Electric(){
        super("Electrico");
    }

    @Override
    public float atacar(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorElectrico(atacante);
    }
    @Override
    public float autoDanio(Pokemon atacante, Pokemon defensor){
        return defensor.recibirDanioPorAtacarAgua(atacante);
    };




    @Override
    public  String toString(){
        return "Electrico";
    }


}
