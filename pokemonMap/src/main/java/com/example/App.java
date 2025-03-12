package com.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione la implementación de Map a usar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        int option = sc.nextInt();
        sc.nextLine(); // limpiar el buffer

        PokemonRepository repository = new PokemonRepository(option);
        repository.loadPokemons("pokemons.csv");
        Map<String, Pokemon> pokemonMap = repository.getPokemonMap();

        PokemonService service = new PokemonService(pokemonMap);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nOpciones:");
            System.out.println("1. Agregar Pokemon a la colección del usuario.");
            System.out.println("2. Mostrar datos de un Pokemon.");
            System.out.println("3. Mostrar la colección del usuario ordenada por type1.");
            System.out.println("4. Mostrar todos los Pokemon ordenados por type1.");
            System.out.println("5. Buscar Pokemon por habilidad.");
            System.out.println("6. Salir.");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokemon a agregar: ");
                    String nameToAdd = sc.nextLine();
                    System.out.println(service.addPokemonToUserCollection(nameToAdd));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokemon: ");
                    String name = sc.nextLine();
                    Pokemon p = service.getPokemon(name);
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("Pokemon no encontrado.");
                    }
                    break;
                case 3:
                    List<Pokemon> userPokemons = service.getUserCollectionSorted();
                    for (Pokemon pokemon : userPokemons) {
                        System.out.println("Nombre: " + pokemon.getName() + ", Tipo 1: " + pokemon.getType1() + ", Habilidad: " + pokemon.getAbility());
                    }
                    break;
                case 4:
                    List<Pokemon> allPokemons = service.getAllPokemonsSortedByType();
                    for (Pokemon pokemon : allPokemons) {
                        System.out.println("Nombre: " + pokemon.getName() + ", Tipo 1: " + pokemon.getType1() + ", Habilidad: " + pokemon.getAbility());
                    }
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad a buscar: ");
                    String ability = sc.nextLine();
                    List<Pokemon> pokemonsByAbility = service.searchPokemonByAbility(ability);
                    if (pokemonsByAbility.isEmpty()) {
                        System.out.println("No se encontraron Pokemon con esa habilidad.");
                    } else {
                        for (Pokemon pokemon : pokemonsByAbility) {
                            System.out.println("Nombre: " + pokemon.getName() + ", Tipo 1: " + pokemon.getType1() + ", Habilidad: " + pokemon.getAbility());
                        }
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}
