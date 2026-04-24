package modelo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private List<Nodo> nodos = new ArrayList<>();
    private List<Arista> aristas = new ArrayList<>();

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void conectar(Nodo a, Nodo b, int peso) {
        Arista arista = new Arista(a, b, peso);
        aristas.add(arista);

        a.getAdyacentes().add(arista);
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }
}
