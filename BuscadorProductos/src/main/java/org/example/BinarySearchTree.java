package org.example;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(E element) {
        root = insertRec(root, element);
    }

    private Node<E> insertRec(Node<E> root, E element) {
        if (root == null) {
            return new Node<>(element);
        }
        if (element.compareTo(root.getData()) < 0) {
            root.setLeft(insertRec(root.getLeft(), element));
        } else if (element.compareTo(root.getData()) > 0) {
            root.setRight(insertRec(root.getRight(), element));
        }
        return root;
    }

    public E search(String sku) {
        return searchRec(root, sku);
    }

    private E searchRec(Node<E> root, String sku) {
        if (root == null) {
            return null;
        }
        Product prod = (Product) root.getData();
        int cmp = prod.getSku().compareTo(sku);
        if (cmp == 0) {
            return root.getData();
        } else if (cmp > 0) {
            return searchRec(root.getLeft(), sku);
        } else {
            return searchRec(root.getRight(), sku);
        }
    }

    public List<E> inOrderTraversal() {
        List<E> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(Node<E> root, List<E> list) {
        if (root != null) {
            inOrderRec(root.getLeft(), list);
            list.add(root.getData());
            inOrderRec(root.getRight(), list);
        }
    }

}
