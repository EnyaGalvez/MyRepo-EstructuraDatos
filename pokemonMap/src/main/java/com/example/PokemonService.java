package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PokemonService {
    private Map<String, Pokemon> pokemonMap;
    private Set<Pokemon> userCollection;

    public PokemonService(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
        // Comparador que ordena por type1 y, en caso de empate, por nombre
        this.userCollection = new TreeSet<>(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon p1, Pokemon p2) {
                int comp = p1.getType1().compareTo(p2.getType1());
                if (comp == 0) {
                    return p1.getName().compareTo(p2.getName());
                }
                return comp;
            }
        });
    }

    public String addPokemonToUserCollection(String name) {
        if (!pokemonMap.containsKey(name)) {
            return "El pokemon no existe en los datos.";
        }
        Pokemon pokemon = pokemonMap.get(name);
        if (userCollection.contains(pokemon)) {
            return "El pokemon ya se encuentra en la colección.";
        }
        userCollection.add(pokemon);
        return "Pokemon agregado a la colección.";
    }

    public Pokemon getPokemon(String name) {
        return pokemonMap.get(name);
    }

    public List<Pokemon> getUserCollectionSorted() {
        return new ArrayList<>(userCollection);
    }

    public List<Pokemon> getAllPokemonsSortedByType() {
        List<Pokemon> list = new ArrayList<>(pokemonMap.values());
        list.sort(Comparator.comparing(Pokemon::getType1).thenComparing(Pokemon::getName));
        return list;
    }

    public List<Pokemon> searchPokemonByAbility(String ability) {
        List<Pokemon> result = new ArrayList<>();
        for (Pokemon p : pokemonMap.values()) {
            if (p.getAbility().toLowerCase().contains(ability.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
