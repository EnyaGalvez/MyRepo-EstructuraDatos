package org.example;

import java.util.Scanner;

public class ProductFinder {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        String filePath = "src/main/resources/home_appliance_skus_lowes.csv";
        productService.readProducts(filePath);

        Scanner scanner = new Scanner(System.in);
        int option;
        
    }
}