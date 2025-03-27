package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BinarySearchTreeTest {

    @Test
    public void testInsert() {
        BinarySearchTree<Product> bst = new BinarySearchTree<>();

        Product p1 = new Product("SKU001", 100.0, 90.0, "Producto A", "Categoría 1");
        Product p2 = new Product("SKU002", 200.0, 180.0, "Producto B", "Categoría 2");
        Product p3 = new Product("SKU003", 150.0, 140.0, "Producto C", "Categoría 1");

        bst.insert(p2);
        bst.insert(p1);
        bst.insert(p3);

        List<Product> products = bst.inOrderTraversal();

        assertEquals(3, products.size(), "El árbol debe contener 3 elementos");
        assertEquals("SKU001", products.get(0).getSku(), "El primer SKU debe ser SKU001");
        assertEquals("SKU002", products.get(1).getSku(), "El segundo SKU debe ser SKU002");
        assertEquals("SKU003", products.get(2).getSku(), "El tercer SKU debe ser SKU003");
    }

    @Test
    public void testSearchBySku() {
        BinarySearchTree<Product> bst = new BinarySearchTree<>();

        Product p1 = new Product("SKU001", 100.0, 90.0, "Producto A", "Categoría 1");
        Product p2 = new Product("SKU002", 200.0, 180.0, "Producto B", "Categoría 2");
        Product p3 = new Product("SKU003", 150.0, 140.0, "Producto C", "Categoría 1");

        bst.insert(p2);
        bst.insert(p1);
        bst.insert(p3);

        Product found = bst.search("SKU002");
        assertNotNull(found, "El producto con SKU002 debe existir");
        assertEquals("SKU002", found.getSku(), "El SKU del producto encontrado debe ser SKU002");
        assertEquals("Producto B", found.getProductName(), "El nombre del producto debe ser 'Producto B'");

        Product notFound = bst.search("SKU999");
        assertNull(notFound, "La búsqueda de un SKU no existente debe retornar null");
    }
}
