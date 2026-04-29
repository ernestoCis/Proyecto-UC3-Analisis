package modelo;

import java.awt.Color;
import modelo.Arista;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un vértice o localidad dentro del grafo. Contiene información
 * sobre su ubicación espacial, conexiones adyacentes y atributos auxiliares
 * para la ejecución de algoritmos de búsqueda y optimización.
 *
 * * @author Paulina Guevara, Ernesto Cisneros
 */
public class Nodo {

    /**
     * Nombre identificador de la localidad.
     */
    private String nombre;
    /**
     * Coordenada X para la representación gráfica.
     */
    private int x;
    /**
     * Coordenada Y para la representación gráfica.
     */
    private int y;
    /**
     * Lista de aristas que conectan a este nodo con sus vecinos.
     */
    private List<Arista> adyacentes;
    /**
     * Estado del nodo representado por un color Blanco:No visitado, Gris:En
     * proceso, Negro:Finalizado
     */
    private Color estado;

    /**
     * Estado de exploración del nodo (usado en BFS y DFS).
     */
    private boolean visitado;
    /**
     * Almacena el costo acumulado (Dijkstra) o tiempo de descubrimiento (DFS).
     */
    private double distancia;
    /**
     * Tiempo de finalización específico para el algoritmo DFS.
     */
    private Integer f;
    /**
     * Referencia al nodo anterior en la ruta óptima encontrada.
     */
    private Nodo predecesor;

    /**
     * Constructor para inicializar un nodo con su nombre y coordenadas.
     *
     * @param nombre Nombre de la localidad.
     * @param x Posición en el eje horizontal.
     * @param y Posición en el eje vertical.
     */
    public Nodo(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.adyacentes = new ArrayList<>();
        this.visitado = false;
        this.distancia = Double.MAX_VALUE; // Representa el infinito
        this.predecesor = null;
        this.estado = Color.BLUE;
    }

    /**
     * Obtiene el nombre del nodo.
     *
     * @return String con el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del nodo.
     *
     * @param nombre Nuevo nombre de la localidad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la coordenada X.
     *
     * @return Entero con la posición X.
     */
    public int getX() {
        return x;
    }

    /**
     * Establece la coordenada X.
     *
     * @param x Nueva posición X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada Y.
     *
     * @return Entero con la posición Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Establece la coordenada Y.
     *
     * @param y Nueva posición Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obtiene la lista de aristas adyacentes.
     *
     * @return List con las conexiones del nodo.
     */
    public List<Arista> getAdyacentes() {
        return adyacentes;
    }

    /**
     * Establece la lista de adyacencia.
     *
     * @param adyacentes Nueva lista de aristas.
     */
    public void setAdyacentes(List<Arista> adyacentes) {
        this.adyacentes = adyacentes;
    }

    /**
     * Verifica si el nodo ha sido visitado por un algoritmo.
     *
     * @return true si fue visitado, false en caso contrario.
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * Modifica el estado de visita del nodo.
     *
     * @param visitado Nuevo estado de visita.
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     * Obtiene el valor de distancia o tiempo de descubrimiento asignado.
     *
     * @return Valor double de la distancia/tiempo.
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * Establece el valor de distancia o tiempo de descubrimiento.
     *
     * @param distancia Nuevo valor de distancia/tiempo.
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    /**
     * Obtiene el nodo predecesor en un camino.
     *
     * @return Objeto Nodo predecesor.
     */
    public Nodo getPredecesor() {
        return predecesor;
    }

    /**
     * Establece el nodo predecesor para reconstrucción de rutas.
     *
     * @param predecesor Nodo anterior en la ruta.
     */
    public void setPredecesor(Nodo predecesor) {
        this.predecesor = predecesor;
    }

    /**
     * Crea y agrega una nueva arista de salida desde este nodo.
     *
     * @param destino Nodo al que apunta la conexión.
     * @param peso Costo o distancia de la arista.
     */
    public void agregarArista(Nodo destino, double peso) {
        this.adyacentes.add(new Arista(this, destino, peso));
    }

    /**
     * Obtiene el tiempo de finalización (específico para DFS).
     *
     * @return Integer con el tiempo f.
     */
    public Integer getF() {
        return f;
    }

    /**
     * Establece el tiempo de finalización (específico para DFS).
     *
     * @param f Nuevo tiempo de finalización.
     */
    public void setF(Integer f) {
        this.f = f;
    }

    /**
     * Regresa el estado del nodo representado por un color
     *
     * @return
     */
    public Color getEstado() {
        return estado;
    }

    /**
     * Establece el estado del nodo representado por un color
     *
     * @param estado
     */
    public void setEstado(Color estado) {
        this.estado = estado;
    }

    /**
     * Representación en cadena del nodo.
     *
     * @return El nombre de la localidad.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
