package ar.edu.davinci.Backend.Trainer;

import ar.edu.davinci.Backend.BattleStep.BattleStep;
import ar.edu.davinci.Backend.Pokemon.Pokemon;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Trainer {


    private int TrainerId;
    private String name;
    private Date fecha_nacimiento;
    private String nationality;
    private Gender gender;
    private List<Pokemon> pokemons;


    public Trainer(String P_name, Date P_fecha_nacimiento, String P_nationality, Gender P_gender) {
        this.name = P_name;
        this.nationality = P_nationality;
        this.fecha_nacimiento = P_fecha_nacimiento;
        this.gender = P_gender;
        this.pokemons = new ArrayList<>();
    }

    public Trainer(int P_TrainerId, String P_name, Date P_fecha_nacimiento, String P_nationality, Gender P_gender) {
        this.TrainerId = P_TrainerId;
        this.name = P_name;
        this.nationality = P_nationality;
        this.fecha_nacimiento = P_fecha_nacimiento;
        this.gender = P_gender;
        this.pokemons = new ArrayList<>();
    }



    public int getTrainerId() {
        return this.TrainerId;
    }

    public String getName() {
        return name;
    }

    public int getFechaNacimientoInt() {
        LocalDate fechaNacimientoLocal = new java.sql.Date(fecha_nacimiento.getTime()).toLocalDate();

        LocalDate fechaActual = LocalDate.now();

        Period periodo = Period.between(fechaNacimientoLocal, fechaActual);

        return periodo.getYears();
    }

    public Date getFechaNacimientoDate() {
        return fecha_nacimiento;
    }

    public String getNationality() {
        return nationality;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }


    public void setname(String name) {
        this.name = name;
    }

    public void setFechaNacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setnationality(String nationality) {
        this.nationality = nationality;
    }

    public void setGenero(Gender gender) {
        this.gender = gender;
    }

    public void setTrainerId(int TrainerId) {
        this.TrainerId = TrainerId;
    }



    public boolean alive() {
        return this.pokemons.stream().anyMatch(pokemon
                -> (pokemon.getVida() > 0));
    }


    public boolean capturePokemon(Pokemon p_pokemon) {
        if (pokemons.size() < 5) {
            this.pokemons.add(p_pokemon);
            return true;
        }
        return false;
    }


    public void addPokemon(List<Pokemon> Pokemones) {
        if (Pokemones != null) {
            this.pokemons.addAll(Pokemones);
        }
    }

}







