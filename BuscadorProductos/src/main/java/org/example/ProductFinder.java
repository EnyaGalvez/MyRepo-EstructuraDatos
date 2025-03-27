package org.example;

import java.util.Scanner;
import java.util.List;

public class ProductFinder {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        String filePath = "src/main/resources/HomeAppliance.csv";
        productService.readProducts(filePath);

        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar producto por SKU");
            System.out.println("2. Listar productos en orden ascendente (por SKU)");
            System.out.println("3. Listar productos en orden descendente (por SKU)");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, intente de nuevo.");
                continue;
            }
            switch (option) {
                case 1:
                    System.out.print("Ingrese SKU: ");
                    String sku = scanner.nextLine();
                    Product found = productService.searchBySku(sku);
                    if (found != null) {
                        System.out.println("Producto encontrado:");
                        System.out.println(found);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 2:
                    List<Product> ascProducts = productService.listProductsAscending();
                    System.out.println("Productos ordenados ascendentemente:");
                    for (Product p : ascProducts) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    List<Product> descProducts = productService.listProductsDescending();
                    System.out.println("Productos ordenados descendentemente:");
                    for (Product p : descProducts) {
                        System.out.println(p);
                    }
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}