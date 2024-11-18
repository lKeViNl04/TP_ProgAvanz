package ar.edu.davinci.ENTRENADOR;

import ar.edu.davinci.POKEMON.Pokemon;
import ar.edu.davinci.POKEMON.TIPO.Fuego;
import ar.edu.davinci.POKEMON.TIPO.Planta;

import java.util.*;

public class Entrenador {

    //Estado

    private String nombre;
    private int edad;
    private String nacionalidad;
    private Genero genero;
    private List<Pokemon> pokemons;

    //CONSTRUCTOR
    public Entrenador(String p_nombre, int p_edad, String p_nacionalidad, Genero p_genero) {
        this.nombre = p_nombre;
        this.nacionalidad = p_nacionalidad;
        this.edad = p_edad;
        this.genero = p_genero;
        this.pokemons = new ArrayList<>();
    }

    //GETTER
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Genero getGenero() {
        return genero;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }


    //SETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    //COMPORTAMIENTO



    public void enfrentarseAOtro(Entrenador otroEntrenador) {

        while ((!this.pokemons.isEmpty()) && (!otroEntrenador.pokemons.isEmpty())) {

            Pokemon miPokemon = this.elegirPokemon();
            Pokemon rival = otroEntrenador.elegirPokemon();

            this.batalla1vs1( miPokemon, rival );

            if(miPokemon.getVida()>0){
                System.out.println("El ganador de la batalla es :"+miPokemon.toString());
                otroEntrenador.pokemons.remove(rival);
            }else{
                System.out.println("El ganador de la batalla es :"+rival.toString());
                this.pokemons.remove(miPokemon);
            }


            }

            if (!this.pokemons.isEmpty()){
            System.out.println("el ganador del enfrentamiento es "+ this.nombre);
            }else{
                System.out.println("el ganador del enfrentamiento es "+ otroEntrenador.nombre);
            }

    }

    public void batalla1vs1(Pokemon miPokemon ,Pokemon rival) {

        while(miPokemon.getVida() > 0 && rival.getVida() > 0){
            System.out.println("el pokemon "+ miPokemon.getEspecie() +" ataca al pokemon" + rival.getEspecie() );
            miPokemon.atacar(rival);
            System.out.println("vida de "+ rival.getEspecie() + " es de "+ rival.getVida());
            if (rival.getVida() > 0) {
                System.out.println("el pokemon "+ rival.getEspecie() +" ataca al pokemon" + miPokemon.getEspecie());
              rival.atacar(miPokemon);
                System.out.println("vida de "+ miPokemon.getEspecie() + " es de "+ miPokemon.getVida());
            }
        }


    }

    public Pokemon elegirPokemon() {
        Scanner sc = new Scanner(System.in);

        Pokemon pokemon = null;

        do{
        mostrarPokemones();

        System.out.println("Elegir pokemon");
        int elegido = sc.nextInt();

        if (elegido >= 0 && elegido < pokemons.size()) {
            pokemon = this.pokemons.get(elegido);
            System.out.println("Pokemon Elegido");
        }else{
            System.out.println("Pokemon no encontrado vuelva a poner pokemon");
        }
        }while( pokemon == null );

        return pokemon;

    }


    public void obtenerPokemonInicial(int elegir) {

        switch(elegir) {
            case 1:
                Pokemon charmander = new Pokemon(new Fuego(),"Charmader",55);
                this.pokemons.add(charmander);
                break;
            case 2:
                Pokemon bulbasur = new Pokemon(new Planta(),"Bulbasaur",45);
                this.pokemons.add(bulbasur);
                break;
            case 3:
                Pokemon squirtle = new Pokemon(new Planta(),"Squirtle",45);
                this.pokemons.add(squirtle);
                break;
            default:
                System.out.println("Opcion no valida");
                break;

        }
    }

    public void capturarPokemon(Pokemon p_pokemon) {
        if (pokemons.stream().count() < 5) {

          Pokemon miPokemon =  this.elegirPokemon();

          this.batalla1vs1( miPokemon, p_pokemon );

          if(p_pokemon.getVida()<=0){
              this.pokemons.add(p_pokemon);
          }
        } else {
            System.out.println("No podes tener más de 5 Pokémons.");
        }
    }

    public void mostrarPokemones(){
        System.out.println("Lista de pokemones de "+ this.getNombre() +" : "+ "\n");
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon);
        }
    }

    public void ganadorGuerra(Entrenador p_entrenador){
        if(p_entrenador.getPokemons() != null){
            System.out.println("Ganaste "+  p_entrenador.getNombre());
        }
    }

    public void agregarPokemons(Pokemon pokemon){
        if(this.getPokemons().size() <5){
            this.pokemons.add(pokemon);
        }
    }

}







