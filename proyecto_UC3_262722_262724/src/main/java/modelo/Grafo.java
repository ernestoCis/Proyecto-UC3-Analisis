package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la estructura de datos de un Grafo que contiene un conjunto de nodos y aristas.
 * Permite la gestión de localidades y sus conexiones para la ejecución de algoritmos de búsqueda.
 * * @author paulina Guevara, Ernesto Cisneros
 */
public class Grafo {

    /** Lista que almacena todos los nodos (localidades) pertenecientes al grafo. */
    private List<Nodo> nodos = new ArrayList<>();
    
    /** Lista que almacena todas las aristas (conexiones) que existen en el grafo. */
    private List<Arista> aristas = new ArrayList<>();

    /**
     * Agrega un nuevo nodo a la lista global de nodos del grafo.
     * * @param nodo El objeto Nodo que se desea integrar al grafo.
     */
    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    /**
     * Establece una conexión bidireccional entre dos nodos con un peso determinado.
     * Crea una arista de ida y una de vuelta, registrándolas en las listas de 
     * adyacencia de los nodos y en la lista global del grafo.
     * * @param a    Nodo inicial de la conexión.
     * @param b    Nodo final de la conexión.
     * @param peso Valor numérico que representa la distancia o costo entre los nodos.
     */
    public void conectar(Nodo a, Nodo b, int peso) {
        Arista ida = new Arista(a, b, peso);
        a.getAdyacentes().add(ida);
        aristas.add(ida);

        Arista vuelta = new Arista(b, a, peso);
        b.getAdyacentes().add(vuelta);
    }

    /**
     * Obtiene la lista completa de nodos que conforman el grafo.
     * * @return Una lista de objetos tipo Nodo.
     */
    public List<Nodo> getNodos() {
        return nodos;
    }

    /**
     * Obtiene la lista completa de todas las aristas registradas en el grafo.
     * * @return Una lista de objetos tipo Arista.
     */
    public List<Arista> getAristas() {
        return aristas;
    }
}