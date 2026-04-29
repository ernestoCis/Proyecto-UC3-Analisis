/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import ui.SistemaGrafos;

/**
 * Clase principal que funge como punto de entrada (Entry Point) para la
 * aplicación. Se encarga de configurar el entorno gráfico y lanzar la interfaz
 * principal.
 *
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class Inicio {

    /**
     * Constructor privado para evitar la instanciación de la clase de inicio.
     * Se añade para cumplir con los estándares de documentación y evitar
     * advertencias de JavaDoc.
     */
    private Inicio() {
        throw new IllegalStateException("Clase de inicio");
    }

    /**
     * Método principal que inicia la ejecución del programa.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta
     * aplicación).
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        /**
         * Lanza la interfaz gráfica dentro del Event Dispatch Thread (EDT).
         * Esto es una buena práctica en Swing para asegurar que la
         * actualización de los componentes sea segura y evitar condiciones de
         * carrera.
         */
        SwingUtilities.invokeLater(SistemaGrafos::new);
    }

}
