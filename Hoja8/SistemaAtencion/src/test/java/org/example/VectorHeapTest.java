package org.example;

import org.example.Paciente;
import org.example.vectorHeapV.VectorHeap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorHeapTest {
    private VectorHeap<Paciente> heap;

    @BeforeEach
    void setUp() {
        heap = new VectorHeap<>();
        heap.add(new Paciente("Paciente C", "Dolor", "C"));
        heap.add(new Paciente("Paciente A", "Fiebre", "A"));
        heap.add(new Paciente("Paciente B", "Herida", "B"));
    }

    @Test
    void testOrderOfRemoval() {
        Paciente primero = heap.remove();
        Paciente segundo = heap.remove();
        Paciente tercero = heap.remove();

        assertEquals("Paciente A", primero.getNombre());
        assertEquals("Paciente B", segundo.getNombre());
        assertEquals("Paciente C", tercero.getNombre());
    }

    @Test
    void testEmptyAfterRemovals() {
        heap.remove();
        heap.remove();
        heap.remove();
        assertTrue(heap.isEmpty(), "El heap debería estar vacío después de remover todos los elementos.");
    }

    @Test
    void testNotEmptyInitially() {
        assertFalse(heap.isEmpty(), "El heap no debería estar vacío después de agregar elementos.");
    }
}
