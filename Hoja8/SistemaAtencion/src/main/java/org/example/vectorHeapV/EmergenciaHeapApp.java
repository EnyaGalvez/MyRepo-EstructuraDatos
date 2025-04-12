package org.example.vectorHeapV;

import org.example.Paciente;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class EmergenciaHeapApp {
    public static void main(String[] args) {
        VectorHeap<Paciente> cola = new VectorHeap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(linea, ",");
                String nombre = st.nextToken().trim();
                String sintoma = st.nextToken().trim();
                String codigo = st.nextToken().trim();
                cola.add(new Paciente(nombre, sintoma, codigo));
            }
        } catch (Exception e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
            return;
        }

        while (!cola.isEmpty()) {
            System.out.println("Siguiente paciente: " + cola.remove());
        }
    }
}
