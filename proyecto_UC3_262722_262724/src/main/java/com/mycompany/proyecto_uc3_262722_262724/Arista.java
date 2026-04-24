/**
 * 
 * @author Paulina Guevara, Ernesto Cisneros;
 */
public class Arista implements Comparable<Arista> {
    Nodo origen;
    Nodo destino;
    double peso; // Distancia en kilómetros
    boolean esParteDeResultado; // Para resaltar en el gráfico (MST o Ruta Corta)

    public Arista(Nodo origen, Nodo destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.esParteDeResultado = false;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isEsParteDeResultado() {
        return esParteDeResultado;
    }

    public void setEsParteDeResultado(boolean esParteDeResultado) {
        this.esParteDeResultado = esParteDeResultado;
    }

    // Método necesario para algoritmos como Kruskal (ordenar por peso)
    @Override
    public int compareTo(Arista otra) {
        return Double.compare(this.peso, otra.peso);
    }
}