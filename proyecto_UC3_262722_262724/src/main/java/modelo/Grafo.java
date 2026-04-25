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
        Arista ida = new Arista(a, b, peso);
        a.getAdyacentes().add(ida);
        aristas.add(ida);

        Arista vuelta = new Arista(b, a, peso);
        b.getAdyacentes().add(vuelta);
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }
}
