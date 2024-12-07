package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.BattleStep.BattleStep;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Trainer.Trainer;

import java.util.List;

public interface TrainerDAO {

    public Trainer setTrainerToUser(Trainer trainer,int userId);
    public List<Trainer> getAllTrainers();
    public List<Trainer> getTrainersByUserId(int userId);
    public int getTrainerCountByUserId(int userId);
    public void deleteTrainer(int trainerid);
    public List<BattleStep> battleTrainerPokemon(Trainer mytrainer, Trainer rival,PokemonDAO pokemonDAO);
    public List<BattleStep> battleWildPokemon(Trainer mytrainer,Pokemon pokemonWild, PokemonDAO pokemonDAO) ;
}

