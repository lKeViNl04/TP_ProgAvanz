package ar.edu.davinci;

import ar.edu.davinci.Backend.DAO.EntrenadorImplMysql;
import ar.edu.davinci.Backend.DAO.PokemonImplMysql;
import ar.edu.davinci.Backend.Trainer.Entrenador;
import ar.edu.davinci.Backend.Trainer.Genero;
import ar.edu.davinci.Backend.Pokemon.Pokemon;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        PokemonImplMysql PokemonImpl = new PokemonImplMysql();

        EntrenadorImplMysql entrenadorImpl = new EntrenadorImplMysql();
        List<Entrenador> ENTRENADORES = entrenadorImpl.getAllEntrenador();
        List<Pokemon> POKEMONES = PokemonImpl.getAllPokemon();

        //Añadir de pokemones de cada entrenador de my DB
        for( Entrenador entrenador : ENTRENADORES ){
            for( Pokemon pokemon : POKEMONES ){
                entrenador.agregarPokemons(pokemon) ;
            }
        }

        System.out.println("Bienvenido a PokemonReword");

        System.out.println("Para comenzar crea tu Entrenador");
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese su Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese su edad ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese su nacionalidad:");
        String nacionalidad = sc.nextLine();

        Genero genero = null;
        int opcion ;
        do {
            System.out.println("""
                    Ingrese su Genero:\s
                    1-Masculino
                    2-Feminino
                    3-Agenero
                    """);

            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    genero = Genero.Masculino;
                    break;
                case 2:
                    genero = Genero.Femenino;
                    break;
                case 3:
                    genero = Genero.Agenero;
                    break;
            }
        }while(!(0<opcion && opcion < 4) );

        Entrenador PJ = new Entrenador(nombre, edad, nacionalidad, genero);

        System.out.println("Ahora "+PJ.getNombre()+ " eligue a tu pokemon inicial :");

        System.out.println( "1 - Charmander (TIPO = FUEGO ) \n" +
                            "2 - Bulbasaur (TIPO = PLANTA ) \n" +
                            "3 - Squirtle (TIPO = AGUA)");
        int elegido;
        do {
            System.out.println("Ingrese 1 de las opciones ");
            elegido = sc.nextInt();
            PJ.obtenerPokemonInicial(elegido);
        }while( !(0<elegido && elegido<4));

        String res = "";
        do {
            System.out.println("Menu");
            System.out.println("1 - Enfrentarse con otro entrenador");
            System.out.println("2 - Capturar un pokemon salvaje");
            System.out.println("3 - Mostrar pokemon que tengo");
            System.out.println("4 - Salir del menu");

            do {
                System.out.println("eligi una opcion");
                 opcion = sc.nextInt();
            } while (!(0<opcion && opcion<4));

            switch (opcion) {
                case 1:
                    Random rnd = new Random();
                    int oponenteentrenador = rnd.nextInt(0,ENTRENADORES.size());
                    PJ.enfrentarseAOtro(ENTRENADORES.get(oponenteentrenador));

                case 2:
                    Random rnd2 = new Random();
                    int oponentepokemon = rnd2.nextInt(0,POKEMONES.size());
                    PJ.capturarPokemon(POKEMONES.get(oponentepokemon));

                case 3:
                    PJ.mostrarPokemones();

                case 4:
                    res = "si";


            }
        }while(res.equalsIgnoreCase("si"));


        sc.close();

    }
}