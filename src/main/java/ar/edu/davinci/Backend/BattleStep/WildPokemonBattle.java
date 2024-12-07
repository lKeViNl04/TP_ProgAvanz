package ar.edu.davinci.Backend.BattleStep;

import ar.edu.davinci.Backend.DAO.PokemonDAO;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Trainer.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WildPokemonBattle {
    private Trainer trainer;
    private PokemonDAO pokemonDAO;

    public WildPokemonBattle(Trainer trainer, PokemonDAO pokemonDAO) {
        this.trainer = trainer;
        this.pokemonDAO = pokemonDAO;
    }

    public List<BattleStep> battleWithWildPokemon(Pokemon wildPokemon) {
        List<BattleStep> steps = new ArrayList<>();
        Random rnd = new Random();

        Pokemon trainerPokemon = getAlivePokemon(trainer, rnd);

        if (trainerPokemon == null) {
            throw new IllegalStateException("El entrenador no tiene Pokémon vivos para luchar.");
        }

        while (trainerPokemon.getVida() > 0 && wildPokemon.getVida() > 0) {
            float damage = trainerPokemon.atacar(wildPokemon);
            float selfDamage = trainerPokemon.autoDanio(wildPokemon);
            steps.add(new BattleStep(
                    trainerPokemon.getEspecie(),
                    wildPokemon.getEspecie(),
                    damage,
                    selfDamage,
                    trainerPokemon.getVida(),
                    wildPokemon.getVida()
            ));

            if (wildPokemon.getVida() > 0) {

                float wildDamage = wildPokemon.atacar(trainerPokemon);
                float wildSelfDamage = wildPokemon.autoDanio(trainerPokemon);
                steps.add(new BattleStep(
                        wildPokemon.getEspecie(),
                        trainerPokemon.getEspecie(),
                        wildDamage,
                        wildSelfDamage,
                        wildPokemon.getVida(),
                        trainerPokemon.getVida()
                ));
            }

            updatePokemonVida(trainerPokemon);
        }

        return steps;
    }

    private void updatePokemonVida(Pokemon pokemon) {

        pokemonDAO.updatePokemonVida(pokemon);
    }

    private Pokemon getAlivePokemon(Trainer trainer, Random rnd) {

        List<Pokemon> vivos = trainer.getPokemons().stream()
                .filter(p -> p.getVida() > 0)
                .toList();

        if (vivos.isEmpty()) {
            return null;
        }

        return vivos.get(rnd.nextInt(vivos.size()));
    }
}