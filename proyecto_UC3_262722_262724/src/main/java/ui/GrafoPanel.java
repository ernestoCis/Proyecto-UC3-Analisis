/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;

/**
 *
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class GrafoPanel extends JPanel {

    private Grafo grafo;
    private Image fondo;

    public GrafoPanel(Grafo grafo) {
        this.grafo = grafo;
        setBackground(Color.WHITE);
        try {
            fondo = new ImageIcon(getClass().getResource("/imagenes/oaxaca.png")).getImage();
        } catch (Exception e) {
            System.out.println("Error cargando imagen");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // FONDO
        if (fondo != null) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }

        // DIBUJAR ARISTAS
        for (Nodo n : grafo.getNodos()) {
            for (Arista a : n.getAdyacentes()) {
                Nodo destino = a.getDestino();

                g.setColor(Color.GRAY);
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
            g.setColor(Color.BLUE);
            g.fillOval(n.getX() - 10, n.getY() - 10, 20, 20);

            g.setColor(Color.BLACK);
            g.drawString(n.getNombre(), n.getX() - 20, n.getY() - 15);
        }
    }
}
