package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.logging.Logger;

public class ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());
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
                    firstLine = false;
                    continue;
                }
                String[] tokens = line.split(",");
                if (tokens.length < 19) continue;

                String category = tokens[0].trim();
                String sku = tokens[6].trim();
                String priceRetailStr = tokens[9].trim();
                String priceCurrentStr = tokens[10].trim();
                String productName = tokens[18].trim();

                if (priceRetailStr.equals("") || priceCurrentStr.equals("")) {
                    continue;
                }

                double priceRetail = Double.parseDouble(priceRetailStr);
                double priceCurrent = Double.parseDouble(priceCurrentStr);

                Product product = new Product(sku, priceRetail, priceCurrent, productName, category);
                bst.insert(product);
            }
        } catch (IOException e) {
            LOGGER.severe("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
        LOGGER.severe("Error al convertir un número: " + e.getMessage());
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
