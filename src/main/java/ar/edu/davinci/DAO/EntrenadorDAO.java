package ar.edu.davinci.DAO;

import ar.edu.davinci.ENTRENADOR.Entrenador;

import java.util.List;

public interface EntrenadorDAO {


    public Entrenador saveEntrenador(Entrenador entrenador);
    public List<Entrenador> getAllEntrenador();
}
