package ar.edu.davinci.Backend.Excepion;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException() {
        super("Pokemon no encontrado");
    }
}
