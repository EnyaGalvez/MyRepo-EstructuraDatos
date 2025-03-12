package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for PokemonService class.
 */
public class PokemonnServiceTest 
{
    private PokemonService service;

    @BeforeEach
    public void setup() {
        Map<String, Pokemon> map = new HashMap<>();
        map.put("Pikachu", new Pokemon("Pikachu", "Electric", "Static"));
        map.put("Charmander", new Pokemon("Charmander", "Fire", "Blaze"));
        service = new PokemonService(map);
    }

    @Test
    public void testAddPokemonToUserCollection() {
        String result = service.addPokemonToUserCollection("Pikachu");
        assertEquals("Pokemon agregado a la colección.", result);

        // Intento de agregar el mismo Pokemon
        result = service.addPokemonToUserCollection("Pikachu");
        assertEquals("El pokemon ya se encuentra en la colección.", result);
    }

    @Test
    public void testSearchPokemonByAbility() {
        List<Pokemon> result = service.searchPokemonByAbility("Blaze");
        assertFalse(result.isEmpty());
        assertEquals("Charmander", result.get(0).getName());

        result = service.searchPokemonByAbility("NonExistingAbility");
        assertTrue(result.isEmpty());
    }
}
