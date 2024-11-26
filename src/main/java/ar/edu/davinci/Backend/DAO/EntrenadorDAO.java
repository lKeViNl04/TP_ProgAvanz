package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.Trainer.Entrenador;

import java.util.List;

public interface EntrenadorDAO {


    public Entrenador saveEntrenador(Entrenador entrenador);
    public List<Entrenador> getAllEntrenador();
}
