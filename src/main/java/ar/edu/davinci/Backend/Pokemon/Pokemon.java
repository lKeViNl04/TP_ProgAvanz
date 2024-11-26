package ar.edu.davinci.Backend.Pokemon;

import ar.edu.davinci.Backend.Pokemon.TIPO.Tipo;

public class Pokemon {
    //ESTADO
    private Tipo tipo;
    private String especie;
    private float vida;
    private double energia;
    private int poder;

    //CONSTRUCTOR
    public Pokemon( Tipo p_tipo , String p_especie , int p_poder){
        this.tipo = p_tipo;
        this.especie = p_especie;
        this.vida = 100;
        this.energia = 100;
        this.poder = p_poder;
    }

    //GETTER
    public Tipo getTipo(){
        return tipo;
    }
    public String getEspecie(){
        return especie;
    }
    public float getVida(){
        return vida;
    }
    public double getEnergia(){
        return energia;
    }
    public int getPoder(){
        return poder;
    }


    //SETTER
    public void setEspecie(String p_especie){
        this.especie = p_especie;
    }
    public void setVida(float p_vida){
        this.vida = p_vida;
    }
    public void setEnergia(double p_energia){
        this.energia = p_energia;
    }
    public void setPoder(int p_poder){
        this.poder = p_poder;
    }

    //COMPORTAMIENTO

    public void atacar(Pokemon p_otroPokemon){
            tipo.atacar(this , p_otroPokemon);
    }

    public void restarVida(float cantidad){
        this.vida = this.vida - cantidad;

    }

    public void aumentarVida(float cantidad){
        this.vida += cantidad;
    }


    public void serAtacadoPorFuego(Pokemon p_atacante){
        this.tipo.serAtacadoPorTipoFuego(p_atacante,this);
    }

    public void serAtacadoPorPiedra(Pokemon p_atacante){
        this.tipo.serAtacadoPorTipoPiedra(p_atacante,this);
    }

   public void serAtacadoPorPlanta(Pokemon p_atacante) {
       this.tipo.serAtacadoPorTipoPlanta(p_atacante, this);
   }

    public void serAtacadoPorAgua(Pokemon p_atacante){
            this.tipo.serAtacadoPorTipoAgua(p_atacante,this);
    }


    public void serAtacadoPorElectrico(Pokemon p_atacante){
        this.tipo.serAtacadoPorTipoElectrico(p_atacante,this);
    }

    public String toString(){
        return"Pokemon " +tipo.getNombre()+ " : {Especie"+getEspecie()+" Poder: "+getPoder()+" Vida:"+ getVida()+"}";
    }



}
