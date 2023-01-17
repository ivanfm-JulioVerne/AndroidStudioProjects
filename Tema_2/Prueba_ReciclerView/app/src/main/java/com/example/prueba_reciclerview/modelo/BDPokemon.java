package com.example.prueba_reciclerview.modelo;

import java.util.ArrayList;

public class BDPokemon {
    static ArrayList<Pokemon> pokemons=new ArrayList<>();

    public BDPokemon(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public BDPokemon() {
        pokemons.add(new Pokemon("Bulbasur","Planta",12));
        pokemons.add(new Pokemon("Charmander","Fuego",11));
        pokemons.add(new Pokemon("Squirtle","Agua",10));
        pokemons.add(new Pokemon("Pikachu","Electrico",13));
        pokemons.add(new Pokemon("Geodude","Tierra",9));
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void removeProfesor(int pos){
        try{
            pokemons.remove(pos);
        }catch (Exception e){

        }
    }

    public void addPokemons(Pokemon pokemon){
        pokemons.add(pokemon);
    }
}
