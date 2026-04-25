package modelo;

/**
 * Representa una conexión (carretera) entre dos localidades en el grafo.
 * Implementa Comparable para permitir el ordenamiento basado en el peso de la
 * arista, lo cual es fundamental para algoritmos como Kruskal.
 *
 * * @author Paulina Guevara, Ernesto Cisneros
 */
public class Arista implements Comparable<Arista> {

    /**
     * Nodo de inicio de la conexión.
     */
    Nodo origen;

    /**
     * Nodo final de la conexión.
     */
    Nodo destino;

    /**
     * Distancia o costo de la conexión en kilómetros.
     */
    double peso;

    /**
     * Bandera para determinar si la arista debe resaltarse en la interfaz
     * gráfica (BFS, DFS, MST).
     */
    boolean esParteDeResultado;

    /**
     * Constructor para crear una nueva Arista.
     *
     * @param origen El nodo de partida.
     * @param destino El nodo de llegada.
     * @param peso El valor numérico de la distancia entre ambos nodos.
     */
    public Arista(Nodo origen, Nodo destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.esParteDeResultado = false;
    }

    /**
     * Obtiene el nodo de origen de la arista.
     *
     * @return El nodo inicial.
     */
    public Nodo getOrigen() {
        return origen;
    }

    /**
     * Establece el nodo de origen de la arista.
     *
     * @param origen El nuevo nodo inicial.
     */
    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    /**
     * Obtiene el nodo de destino de la arista.
     *
     * @return El nodo final.
     */
    public Nodo getDestino() {
        return destino;
    }

    /**
     * Establece el nodo de destino de la arista.
     *
     * @param destino El nuevo nodo final.
     */
    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    /**
     * Obtiene el peso o distancia de la arista.
     *
     * @return Valor del peso en tipo double.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso o distancia de la arista.
     *
     * @param peso El nuevo valor de la distancia.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Indica si la arista está marcada como parte de la solución de un
     * algoritmo.
     *
     * @return true si es parte del resultado, false en caso contrario.
     */
    public boolean isEsParteDeResultado() {
        return esParteDeResultado;
    }

    /**
     * Cambia el estado de resaltado de la arista.
     *
     * @param esParteDeResultado true para resaltar la arista en el gráfico,
     * false para normal.
     */
    public void setEsParteDeResultado(boolean esParteDeResultado) {
        this.esParteDeResultado = esParteDeResultado;
    }

    /**
     * Compara esta arista con otra basándose exclusivamente en su peso.
     * Esencial para el funcionamiento de algoritmos de optimización.
     *
     * @param otra La arista con la que se desea comparar.
     * @return Un entero negativo, cero o positivo si el peso es menor, igual o
     * mayor respectivamente.
     */
    @Override
    public int compareTo(Arista otra) {
        return Double.compare(this.peso, otra.peso);
    }
}
