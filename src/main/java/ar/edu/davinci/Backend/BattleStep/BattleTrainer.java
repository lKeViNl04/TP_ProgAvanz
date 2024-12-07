package ar.edu.davinci.Backend.BattleStep;


import ar.edu.davinci.Backend.DAO.PokemonDAO;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Trainer.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleTrainer {
    private Trainer trainer1;
    private Trainer trainer2;
    private PokemonDAO pokemonDAO;

    public BattleTrainer(Trainer trainer1, Trainer trainer2, PokemonDAO pokemonDAO) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.pokemonDAO = pokemonDAO;
    }

    public List<BattleStep> startBattle() {
        List<BattleStep> steps = new ArrayList<>();
        Random rnd = new Random();

        while (trainer1.alive() && trainer2.alive()) {
            Pokemon pokemon1 = getAlivePokemon(trainer1, rnd);
            Pokemon pokemon2 = getAlivePokemon(trainer2, rnd);

            if (pokemon1 != null && pokemon2 != null) {
                steps.addAll(battlePokemon(pokemon1, pokemon2));
            }
        }

        return steps;
    }

    private List<BattleStep> battlePokemon(Pokemon pokemon1, Pokemon pokemon2) {
        List<BattleStep> steps = new ArrayList<>();

        while (pokemon1.getVida() > 0 && pokemon2.getVida() > 0) {

            float damage1 = pokemon1.atacar(pokemon2);
            float selfDamage1 = pokemon1.autoDanio(pokemon2);
            updatePokemonVida(pokemon1);
            updatePokemonVida(pokemon2);

            steps.add(new BattleStep(
                    pokemon1.getEspecie(),
                    pokemon2.getEspecie(),
                    damage1,
                    selfDamage1,
                    pokemon1.getVida(),
                    pokemon2.getVida()
            ));

            if (pokemon2.getVida() > 0) {
                float damage2 = pokemon2.atacar(pokemon1);
                float selfDamage2 = pokemon2.autoDanio(pokemon1);
                updatePokemonVida(pokemon1);
                updatePokemonVida(pokemon2);

                steps.add(new BattleStep(
                        pokemon2.getEspecie(),
                        pokemon1.getEspecie(),
                        damage2,
                        selfDamage2,
                        pokemon2.getVida(),
                        pokemon1.getVida()
                ));
            }
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