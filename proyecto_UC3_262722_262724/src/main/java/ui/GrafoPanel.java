/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;

/**
 * Panel personalizado para la representación gráfica del grafo sobre un mapa.
 * Se encarga de renderizar los nodos como puntos, las aristas como líneas y de
 * resaltar los resultados de los algoritmos de búsqueda (BFS, DFS).
 *
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class GrafoPanel extends JPanel {

    /**
     * Instancia del grafo que contiene los datos a dibujar.
     */
    private Grafo grafo;
    /**
     * Imagen de fondo (Mapa de Oaxaca) sobre la cual se proyecta el grafo.
     */
    private Image fondo;

    /**
     * Constructor que inicializa el panel con un grafo específico y carga la
     * imagen de fondo.
     *
     * @param grafo El objeto Grafo que se desea visualizar.
     */
    public GrafoPanel(Grafo grafo) {
        this.grafo = grafo;
        setBackground(Color.WHITE);
        try {
            fondo = new ImageIcon(getClass().getResource("/imagenes/oaxaca.png")).getImage();
        } catch (Exception e) {
            System.out.println("Error cargando imagen");
        }
    }

    /**
     * Sobrescribe el método de dibujo para renderizar los componentes del
     * grafo. Realiza el dibujado en orden: fondo (imagen), aristas
     * (normales/resaltadas) y nodos.
     *
     * @param g El contexto gráfico utilizado para pintar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // FONDO
        if (fondo != null) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }

        // DIBUJAR ARISTAS
        for (Nodo n : grafo.getNodos()) {
            for (Arista a : n.getAdyacentes()) {
                if (a.isEsParteDeResultado()) {
                    g2.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(4));
                } else {
                    g2.setColor(Color.GRAY); // Color normal
                    g2.setStroke(new BasicStroke(1));
                }
                Nodo destino = a.getDestino();

                g.drawLine(n.getX(), n.getY(), destino.getX(), destino.getY());

                // peso
                int midX = (n.getX() + destino.getX()) / 2;
                int midY = (n.getY() + destino.getY()) / 2;
                g.setColor(Color.RED);
                g.drawString((int) a.getPeso() + " km", midX, midY);
            }
        }

        // DIBUJAR NODOS
        for (Nodo n : grafo.getNodos()) {
            g.setColor(n.getEstado());
            g.fillOval(n.getX() - 10, n.getY() - 10, 20, 20);

            g.setColor(Color.BLACK);
            g.drawString(n.getNombre(), n.getX() - 20, n.getY() - 15);
        }
    }
}
