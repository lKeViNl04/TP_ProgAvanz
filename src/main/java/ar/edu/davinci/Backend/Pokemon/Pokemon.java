package ar.edu.davinci.Backend.Pokemon;

import ar.edu.davinci.Backend.Pokemon.Type.Type;
import ar.edu.davinci.Frontend.Toast.Notifications;

public class Pokemon {
    private int pokemonid;
    private Type type;
    private String especie;
    private float vida;
    private double energia;
    private int poder;

    public Pokemon(Type P_type, String P_especie, int P_poder) {
        this.type = P_type;
        this.especie = P_especie;
        this.poder = P_poder;
    }

    public Pokemon(int P_pokemonId, Type P_type, String P_especie, int P_poder)
    {
        this.pokemonid = P_pokemonId;
        this.type = P_type;
        this.especie = P_especie;
        this.vida = 100;
        this.energia = 100;
        this.poder = P_poder;
    }

    public Pokemon(int P_pokemonId, Type P_type, String P_especie, int P_vida, int P_energia, int P_poder) {
        this.pokemonid = P_pokemonId;
        this.type = P_type;
        this.especie = P_especie;
        this.vida = P_vida;
        this.energia = P_energia;
        this.poder = P_poder;
    }

    public int getPokemonid() {
        return pokemonid;
    }

    public Type getTipo() {
        return type;
    }

    public String getEspecie() {
        return especie;
    }

    public float getVida() {
        return vida;
    }

    public double getEnergia() {
        return energia;
    }

    public int getPoder() {
        return poder;
    }



    public void setEspecie(String p_especie) {
        this.especie = p_especie;
    }

    public void setVida(float p_vida) {
        this.vida = p_vida;
    }

    public void setEnergia(double p_energia) {
        this.energia = p_energia;
    }

    public void setPoder(int p_poder) {
        this.poder = p_poder;
    }



    public float atacar(Pokemon p_otroPokemon) {
        return type.atacar(this, p_otroPokemon);
    }

    public float autoDanio(Pokemon defensor) {
        return this.type.autoDanio(this, defensor);
    }

    public void restarVida(float cantidad) {
        this.vida = this.vida - cantidad;
    }

    public void aumentarVida(float cantidad) {
        this.vida += cantidad;
    }


    public float serAtacadoPorFuego(Pokemon p_atacante) {
        return this.type.serAtacadoPorTipoFuego(p_atacante, this);
    }

    public float serAtacadoPorPiedra(Pokemon p_atacante) {
        return this.type.serAtacadoPorTipoPiedra(p_atacante, this);
    }

    public float serAtacadoPorPlanta(Pokemon p_atacante) {
        return this.type.serAtacadoPorTipoPlanta(p_atacante, this);
    }

    public float serAtacadoPorAgua(Pokemon p_atacante) {
        return this.type.serAtacadoPorTipoAgua(p_atacante, this);
    }


    public float serAtacadoPorElectrico(Pokemon p_atacante) {
        return this.type.serAtacadoPorTipoElectrico(p_atacante, this);
    }

    public float recibirDanioPorAtacarAgua(Pokemon p_atacante) {
        return this.type.recibirDanioPorAtacarAgua(p_atacante);
    }

    public boolean isDead() {
        return this.getVida() <= 0;

    }

    public String toString() {
        return "Pokemon " + type.getNombre() + " : {Especie" + getEspecie() + " Poder: " + getPoder() + " Vida:" + getVida() + "}";
    }


}
