package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class PokemonRepository {
    private Map<String, Pokemon> pokemonMap;

    public PokemonRepository(int mapOption) {
        pokemonMap = MapFactory.getMap(mapOption);
    }

    public void loadPokemons(String fileName) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                String[] tokens = line.split(",");
                String name = tokens[0];
                String type1 = tokens[2];
                String ability = tokens[7].replaceAll("\"", "");
                Pokemon pokemon = new Pokemon(name, type1, ability);
                pokemonMap.put(name, pokemon);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public Map<String, Pokemon> getPokemonMap() {
        return pokemonMap;
    }
}
