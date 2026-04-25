/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;

/**
 *
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class Controlador {

    private Grafo grafo;

    public Controlador() {
        grafo = crearGrafoOaxaca();

    }

    public Grafo getGrafo() {
        return grafo;
    }

    private Grafo crearGrafoOaxaca() {

        Grafo grafo = new Grafo();

        // NODOS (coordenadas aproximadas)
        Nodo oaxaca = new Nodo("Oaxaca", 340, 310);
        Nodo xoxo = new Nodo("Xoxocotlán", 280, 310);
        Nodo santaLucia = new Nodo("Santa Lucía", 340, 270);
        Nodo atzompa = new Nodo("Atzompa", 310, 250);
        Nodo amilpas = new Nodo("San Jacinto Amilpas", 400, 290);
        Nodo amilpasSC = new Nodo("Santa Cruz Amilpas", 430, 310);

        Nodo zaachila = new Nodo("Zaachila", 270, 390);
        Nodo cuilapam = new Nodo("Cuilápam", 330, 350);
        Nodo ocotlan = new Nodo("Ocotlán", 340, 420);
        Nodo zimatlan = new Nodo("Zimatlán", 300, 410);

        Nodo miahuatlan = new Nodo("Miahuatlán", 320, 450);
        Nodo puerto = new Nodo("Puerto Escondido", 260, 510);
        Nodo rioGrande = new Nodo("Río Grande", 200, 500);
        Nodo pochutla = new Nodo("Pochutla", 370, 530);
        Nodo crucecita = new Nodo("Crucecita", 430, 520);

        Nodo huajuapan = new Nodo("Huajuapan", 150, 220);
        Nodo sanAntonio = new Nodo("San Antonio de la Cal", 140, 260);
        Nodo tlaxiaco = new Nodo("Tlaxiaco", 200, 320);
        Nodo nochixtlan = new Nodo("Nochixtlán", 280, 230);
        Nodo vicenteGuerrero = new Nodo("Vicente Guerrero", 140, 360);

        Nodo tlacolula = new Nodo("Tlacolula", 420, 400);

        Nodo tuxtepec = new Nodo("Tuxtepec", 380, 150);
        Nodo lomaBonita = new Nodo("Loma Bonita", 440, 150);

        Nodo matiasRomero = new Nodo("Matías Romero", 680, 310);
        Nodo ixtepec = new Nodo("Ixtepec", 650, 330);
        Nodo tehuantepec = new Nodo("Tehuantepec", 590, 390);
        Nodo salinaCruz = new Nodo("Salina Cruz", 600, 430);
        Nodo juchitan = new Nodo("Juchitán", 680, 370);
        Nodo unionHidalgo = new Nodo("Unión Hidalgo", 730, 330);

        Nodo pinotepa = new Nodo("Pinotepa", 140, 450);

        // AGREGAR NODOS
        Nodo[] nodos = {
            oaxaca, xoxo, santaLucia, atzompa, amilpas, amilpasSC,
            zaachila, cuilapam, ocotlan, zimatlan,
            miahuatlan, puerto, rioGrande, pochutla, crucecita,
            huajuapan, sanAntonio, tlaxiaco, nochixtlan, vicenteGuerrero,
            tlacolula, tuxtepec, lomaBonita,
            matiasRomero, ixtepec, tehuantepec, salinaCruz,
            juchitan, unionHidalgo, pinotepa
        };

        for (Nodo n : nodos) {
            grafo.agregarNodo(n);
        }

        // ARISTAS (distancias aproximadas en km)
        // ZONA CENTRO 
        grafo.conectar(oaxaca, xoxo, 5);
        grafo.conectar(oaxaca, santaLucia, 4);
        grafo.conectar(oaxaca, atzompa, 6);
        grafo.conectar(oaxaca, amilpas, 7);
        grafo.conectar(oaxaca, amilpasSC, 6);

        grafo.conectar(xoxo, sanAntonio, 4);
        grafo.conectar(sanAntonio, vicenteGuerrero, 8);

        grafo.conectar(oaxaca, zaachila, 15);
        grafo.conectar(zaachila, cuilapam, 10);
        grafo.conectar(cuilapam, ocotlan, 25);
        grafo.conectar(ocotlan, zimatlan, 10);

        // MIXTECA 
        grafo.conectar(oaxaca, nochixtlan, 90);
        grafo.conectar(nochixtlan, huajuapan, 100);
        grafo.conectar(huajuapan, tlaxiaco, 90);
        grafo.conectar(tlaxiaco, vicenteGuerrero, 70);

        // SIERRA SUR → COSTA 
        grafo.conectar(oaxaca, miahuatlan, 100);
        grafo.conectar(miahuatlan, puerto, 140);
        grafo.conectar(puerto, rioGrande, 40);
        grafo.conectar(rioGrande, pinotepa, 90);

        grafo.conectar(puerto, pochutla, 60);
        grafo.conectar(pochutla, crucecita, 30);

        // ISTMO 
        grafo.conectar(oaxaca, tlacolula, 35);
        grafo.conectar(tlacolula, tehuantepec, 200);

        grafo.conectar(tehuantepec, salinaCruz, 30);
        grafo.conectar(salinaCruz, juchitan, 25);
        grafo.conectar(juchitan, unionHidalgo, 15);
        grafo.conectar(juchitan, ixtepec, 20);

        grafo.conectar(ixtepec, matiasRomero, 80);

        // NORTE 
        grafo.conectar(oaxaca, tuxtepec, 215);
        grafo.conectar(tuxtepec, lomaBonita, 50);

        return grafo;
    }

    public DefaultTableModel getTablaNodos() {
        String[] columnas = {"#", "Nombre de Localidad", "Coordenada X", "Coordenada Y"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Nodo> listaNodos = this.grafo.getNodos();
        int contador = 1;
        for (Nodo nodo : listaNodos) {
            Object[] fila = {
                contador++,
                nodo.getNombre(),
                nodo.getX(),
                nodo.getY()
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    public DefaultTableModel getTablaAristas() {
        String[] columnas = {"#", "Origen", "Destino", "Distancia (KM)"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Arista> listaAristas = this.grafo.getAristas();
        int contador = 1;
        for (Arista arista : listaAristas) {
            Object[] fila = {
                contador++,
                arista.getOrigen().getNombre(),
                arista.getDestino().getNombre(),
                arista.getPeso() + " km"
            };
            modelo.addRow(fila);
        }
        return modelo;
    }

    public void limpiarResultadoAlgoritmos() {
        for (Nodo n : grafo.getNodos()) {
            n.setVisitado(false);
            n.setPredecesor(null);
            n.setDistancia(Double.MAX_VALUE);
            n.setF(null);
        }
        for (Arista a : grafo.getAristas()) {
            a.setEsParteDeResultado(false);
        }

        for (Nodo n : grafo.getNodos()) {
            for (Arista ady : n.getAdyacentes()) {
                ady.setEsParteDeResultado(false);
            }
        }
    }

    // Recorridos
    public List<Nodo> ejecutarBFS(Nodo nodoSemilla) {

        limpiarResultadoAlgoritmos();

        List<Nodo> nodos = this.grafo.getNodos();

        // Limpiar estados previos
        for (Nodo n : nodos) {
            n.setVisitado(false);
            n.setPredecesor(null);
            n.setDistancia(Double.MAX_VALUE); // Inicializamos como "infinito"
        }

        List<Nodo> secuenciaVisita = new ArrayList<>();
        Queue<Nodo> cola = new LinkedList<>();

        nodoSemilla.setVisitado(true);
        nodoSemilla.setDistancia(0);
        cola.add(nodoSemilla);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            secuenciaVisita.add(actual);

            for (Arista arista : actual.getAdyacentes()) {
                Nodo vecino = arista.getDestino();

                if (!vecino.isVisitado()) {
                    vecino.setVisitado(true);
                    vecino.setPredecesor(actual);

                    vecino.setDistancia(actual.getDistancia() + 1);

                    arista.setEsParteDeResultado(true);
                    cola.add(vecino);
                }
            }
        }
        return secuenciaVisita;
    }

    private int tiempo;

    public List<Nodo> ejecutarDFS(Nodo inicio) {
        List<Nodo> visita = new ArrayList<>();

        limpiarResultadoAlgoritmos();

        tiempo = 0;

        if (!inicio.isVisitado()) {
            dfsVisit(inicio, visita);
        }
        
        return visita;
    }
    
    private void dfsVisit(Nodo u, List<Nodo> visita) {
        tiempo++;
        u.setDistancia(tiempo); // u.d = time

        u.setVisitado(true);
        visita.add(u);

        for (Arista arista : u.getAdyacentes()) {
            Nodo v = arista.getDestino();
            if (!v.isVisitado()) {
                v.setPredecesor(u);
                arista.setEsParteDeResultado(true);
                marcarAristaEspejo(v, u);
                dfsVisit(v, visita);
            }
        }

        tiempo++;            // time = time + 1
        u.setF(tiempo);      // u.f = time
    }
    
    private void marcarAristaEspejo(Nodo origen, Nodo destino) {
        for (Arista a : origen.getAdyacentes()) {
            if (a.getDestino().equals(destino)) {
                a.setEsParteDeResultado(true);
                break;
            }
        }
    }

    public Nodo buscarNodoPorNombre(String nombreBusqueda) {
        if (nombreBusqueda == null || nombreBusqueda.trim().isEmpty()) {
            return null;
        }

        String nombreLimpio = nombreBusqueda.trim();

        for (Nodo n : grafo.getNodos()) {
            if (n.getNombre().equalsIgnoreCase(nombreLimpio)) {
                return n;
            }
        }

        return null;
    }
}
