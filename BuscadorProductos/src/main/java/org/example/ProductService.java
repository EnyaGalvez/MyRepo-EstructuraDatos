package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Collections;

public class ProductService {
    private BinarySearchTree<Product> bst;

    public ProductService() {
        bst = new BinarySearchTree<>();
    }

    public void readProducts(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    if (line.toLowerCase().contains("sku")) {
                        firstLine = false;
                        continue;
                    }
                    firstLine = false;
                }
                String[] tokens = line.split(",");
                if (tokens.length < 5) continue;
                String sku = tokens[0].trim();
                double priceRetail = Double.parseDouble(tokens[1].trim());
                double priceCurrent = Double.parseDouble(tokens[2].trim());
                String productName = tokens[3].trim();
                String category = tokens[4].trim();
                Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                bst.insert(product);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public Product searchBySku(String sku) {
        return bst.search(sku);
    }

    public List<Product> listProductsAscending() {
        return bst.inOrderTraversal();
    }

    public List<Product> listProductsDescending() {
        List<Product> products = bst.inOrderTraversal();
        Collections.reverse(products);
        return products;
    }
}
