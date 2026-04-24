package modelo;

import modelo.Arista;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class Nodo {
    String nombre;
    int x, y;
    List<Arista> adyacentes;
    
    // Atributos auxiliares para algoritmos (BFS, DFS, Dijkstra)
    boolean visitado;
    double distancia; // Para Dijkstra
    Nodo predecesor;  // Para reconstruir rutas cortas

    public Nodo(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.adyacentes = new ArrayList<>();
        this.visitado = false;
        this.distancia = Double.MAX_VALUE; // Representa el infinito
        this.predecesor = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Arista> getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(List<Arista> adyacentes) {
        this.adyacentes = adyacentes;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public Nodo getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(Nodo predecesor) {
        this.predecesor = predecesor;
    }

    public void agregarArista(Nodo destino, double peso) {
        this.adyacentes.add(new Arista(this, destino, peso));
    }

    @Override
    public String toString() {
        return nombre;
    }
}