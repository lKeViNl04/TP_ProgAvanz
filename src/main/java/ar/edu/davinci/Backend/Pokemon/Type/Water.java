package ar.edu.davinci.Backend.Pokemon.Type;


import ar.edu.davinci.Backend.Pokemon.Pokemon;

public class Water extends Type {

    public Water(){
        super("Agua");
    }

    @Override
    public float atacar(Pokemon atacante, Pokemon defensor) {
       return defensor.serAtacadoPorAgua(atacante);
    }

    @Override
    public float serAtacadoPorTipoElectrico(Pokemon atacante, Pokemon defensor) {

        float danioCausado = atacante.getPoder()+ (0.50f * atacante.getPoder());
        defensor.restarVida(danioCausado);


        return danioCausado;

    }


    public float recibirDanioPorAtacarAgua(Pokemon atacante) {
        float daniodevuelta = atacante.getPoder()*0.05f;
        atacante.restarVida(daniodevuelta);

        return daniodevuelta;
    }


    @Override
    public String toString() {
        return "Agua";
    }
}
