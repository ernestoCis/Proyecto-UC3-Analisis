/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.table.DefaultTableModel;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;

/**
 * Clase controladora que gestiona la lógica de negocio del sistema. Se encarga
 * de la creación del grafo, la manipulación de datos para la interfaz y la
 * ejecución de algoritmos de búsqueda y recorrido.
 *
 * * @author Paulina Guevara, Ernesto Cisneros
 */
public class Controlador {

    /**
     * Instancia del grafo principal que contiene la red de carreteras de
     * Oaxaca.
     */
    private Grafo grafo;

    /**
     * Constructor que inicializa el controlador y genera el grafo de Oaxaca.
     */
    public Controlador() {
        grafo = crearGrafoOaxaca();

    }

    /**
     * Obtiene la instancia del grafo gestionado.
     *
     * @return El objeto Grafo.
     */
    public Grafo getGrafo() {
        return grafo;
    }

    /**
     * Define y construye la estructura del grafo representando las localidades
     * principales del estado de Oaxaca y sus conexiones viales.
     *
     * @return Un objeto Grafo poblado con nodos y aristas.
     */
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

    /**
     * Genera un modelo de tabla con los datos de todas las localidades
     * registradas.
     *
     * @return DefaultTableModel para ser usado en un JTable.
     */
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

    /**
     * Genera un modelo de tabla con los datos de todas las carreteras y sus
     * distancias.
     *
     * @return DefaultTableModel para ser usado en un JTable.
     */
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

    /**
     * Reinicia los estados de todos los nodos y aristas para permitir una nueva
     * ejecución de algoritmos sin datos residuales.
     */
    public void limpiarResultadoAlgoritmos() {
        for (Nodo n : grafo.getNodos()) {
            n.setVisitado(false);
            n.setPredecesor(null);
            n.setDistancia(Double.MAX_VALUE);
            n.setF(null);
            n.setEstado(Color.BLUE);
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
    /**
     * Ejecuta el recorrido en anchura (BFS) a partir de una localidad semilla.
     * Calcula los niveles de distancia mínima en términos de saltos.
     *
     * @param nodoSemilla Punto de inicio del recorrido.
     * @return Lista de nodos en el orden en que fueron descubiertos.
     */
    public List<PasoAlgoritmo> ejecutarBFS(Nodo nodoSemilla) {

        limpiarResultadoAlgoritmos();

        List<PasoAlgoritmo> secuenciaPasos = new ArrayList<>(); // Interfaz

        List<Nodo> nodos = this.grafo.getNodos();

        // Pasos del 1 al 4
        for (Nodo n : nodos) {
            n.setVisitado(false);
            n.setPredecesor(null);
            n.setDistancia(Double.MAX_VALUE); // Inicializamos como "infinito"
        }

        // Pasos del 5 al 7
        nodoSemilla.setVisitado(true);
        nodoSemilla.setDistancia(0);
        nodoSemilla.setPredecesor(null);

        // Paso 8
        Queue<Nodo> cola = new LinkedList<>();

        //Paso 9
        cola.add(nodoSemilla);

        secuenciaPasos.add(new PasoAlgoritmo(nodoSemilla, null, Color.GRAY)); // Interfaz

        while (!cola.isEmpty()) { // Paso 10
            Nodo actual = cola.poll(); // Paso 11

            for (Arista arista : actual.getAdyacentes()) { // Paso 12
                Nodo vecino = arista.getDestino(); // v

                if (!vecino.isVisitado()) { // Paso 13
                    vecino.setVisitado(true); // Paso 14
                    vecino.setDistancia(actual.getDistancia() + 1); // Paso 15
                    vecino.setPredecesor(actual); // Paso 16
                    cola.add(vecino); // Paso 17

                    secuenciaPasos.add(new PasoAlgoritmo(vecino, arista, Color.GRAY)); // Interfaz
                }
            }
            secuenciaPasos.add(new PasoAlgoritmo(actual, null, Color.BLACK)); // Interfaz / Paso 18
        }
        for (Nodo n : grafo.getNodos()) {
            n.setEstado(Color.BLUE); // Interfaz
        }
        return secuenciaPasos;
    }

    /**
     * Atributo auxiliar para llevar el conteo de tiempo en DFS.
     */
    private int tiempo;

    /**
     * Ejecuta el recorrido en profundidad (DFS) a partir de un nodo inicial.
     *
     * @param inicio Nodo raíz para comenzar la exploración.
     * @return Lista de nodos visitados en orden de descubrimiento.
     */
    public List<PasoAlgoritmo> ejecutarDFS(Nodo inicio) {
        List<PasoAlgoritmo> pasos = new ArrayList<>();

        limpiarResultadoAlgoritmos();

        // Pasos de 1 a 3
        for (Nodo n : grafo.getNodos()) {
            n.setVisitado(false);
            n.setPredecesor(null);
            n.setEstado(Color.BLUE); // Interfaz
        }

        // Interfaz
        for (Arista a : grafo.getAristas()) {
            a.setEsParteDeResultado(false);
        }

        // Paso 4
        tiempo = 0;

        // Pasos de 5 a 7
        if (!inicio.isVisitado()) {
            dfsVisit(inicio, pasos);
        }

        return pasos;
    }

    /**
     * Método recursivo para la exploración en profundidad.
     *
     * @param u Nodo actual que se está visitando.
     * @param visita Lista que acumula el orden de visita.
     */
    private void dfsVisit(Nodo u, List<PasoAlgoritmo> pasos) {
        tiempo++; // Paso 1
        u.setDistancia(tiempo); // Paso 2      u.d = time
        u.setVisitado(true); // Paso 3

        pasos.add(new PasoAlgoritmo(u, null, Color.GRAY)); // Interfaz

        for (Arista arista : u.getAdyacentes()) { // Paso 4
            Nodo v = arista.getDestino(); // v
            if (!v.isVisitado()) { // Paso 5
                v.setPredecesor(u); // Paso 6

                pasos.add(new PasoAlgoritmo(v, arista, Color.GRAY)); // Interfaz

                dfsVisit(v, pasos); // Paso 7
            }
        }

        pasos.add(new PasoAlgoritmo(u, null, Color.BLACK)); // Paso 8 / Interfaz

        tiempo++;   // Paso 9         // time = time + 1
        u.setF(tiempo); // Paso 10     // u.f = time
    }

    /**
     * Busca un objeto Nodo dentro del grafo comparando por nombre.
     *
     * @param nombreBusqueda Texto con el nombre de la localidad.
     * @return El objeto Nodo encontrado o null si no existe.
     */
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

    /**
     * Clase para ayudar a la animacion del recorrido
     */
    public class PasoAlgoritmo {

        /**
         * nodo al que se le cambiara el estado
         */
        public Nodo nodo;
        /**
         * arista a la que se le cambiara el color si es parte del resultado
         */
        public Arista arista;
        /**
         * color que se va a usar
         */
        public Color colorNuevo;

        /**
         * Constructor del paso
         *
         * @param nodo nodo a modificar
         * @param arista arista a modificar
         * @param colorNuevo nuevo color que se usara
         */
        public PasoAlgoritmo(Nodo nodo, Arista arista, Color colorNuevo) {
            this.nodo = nodo;
            this.arista = arista;
            this.colorNuevo = colorNuevo;
        }
    }

    // KRUSKAL - MST
    /**
     * Clase contenedora para los resultados de un Árbol de Expansión Mínima
     * (MST).
     */
    public class ResultadoMST {

        public List<Arista> aristas;
        public double pesoTotal;

        public ResultadoMST(List<Arista> aristas, double pesoTotal) {
            this.aristas = aristas;
            this.pesoTotal = pesoTotal;
        }
    }

    /**
     * Ejecuta el algoritmo de Kruskal para hallar el Árbol de Expansión Mínima.
     * Utiliza la técnica de Union-Find con compresión de caminos.
     *
     * @return Objeto ResultadoMST con las aristas seleccionadas y el costo
     * total.
     */
    public ResultadoMST ejecutarKruskal() {
        limpiarResultadoAlgoritmos();

        List<Arista> resultado = new ArrayList<>();
        double pesoTotal = 0;

        List<Arista> aristas = new ArrayList<>(grafo.getAristas());
        aristas.sort(null); // Ordenar por peso

        List<Nodo> nodos = grafo.getNodos();

        int n = nodos.size();
        int[] padre = new int[n];
        int[] rango = new int[n];

        // Inicializar Union-Find
        for (int i = 0; i < n; i++) {
            padre[i] = i;
            rango[i] = 0;
        }

        for (Arista a : aristas) {
            int i = nodos.indexOf(a.getOrigen());
            int j = nodos.indexOf(a.getDestino());

            int raizI = find(padre, i);
            int raizJ = find(padre, j);

            if (raizI != raizJ) {
                union(padre, rango, raizI, raizJ);

                resultado.add(a);
                pesoTotal += a.getPeso();

                // Marcar como parte del MST (para UI)
                a.setEsParteDeResultado(true);

                // OPTIMIZACIÓN: parar cuando ya tiene n-1 aristas
                if (resultado.size() == n - 1) {
                    break;
                }
            }
        }

        return new ResultadoMST(resultado, pesoTotal);
    }

    /**
     * Operación Find de Union-Find con compresión de caminos.
     *
     * @param padre Arreglo de parentesco.
     * @param i Índice del nodo a buscar.
     * @return Raíz del conjunto al que pertenece el nodo i.
     */
    private int find(int[] padre, int i) {
        if (padre[i] != i) {
            padre[i] = find(padre, padre[i]); // compresión
        }
        return padre[i];
    }

    /**
     * Operación Union de Union-Find utilizando unión por rango.
     *
     * @param padre Arreglo de parentesco.
     * @param rango Arreglo de rangos (profundidad del árbol).
     * @param x Raíz del primer conjunto.
     * @param y Raíz del segundo conjunto.
     */
    private void union(int[] padre, int[] rango, int x, int y) {
        if (rango[x] < rango[y]) {
            padre[x] = y;
        } else if (rango[x] > rango[y]) {
            padre[y] = x;
        } else {
            padre[y] = x;
            rango[x]++;
        }
    }

    // DIJKSTRA
    /**
     * Clase interna para representar un paso intermedio en la ejecución de
     * Dijkstra.
     */
    public class PasoDijkstra {

        public Nodo nodo;
        public Arista arista;
        public Color color;

        public PasoDijkstra(Nodo nodo, Arista arista, Color color) {
            this.nodo = nodo;
            this.arista = arista;
            this.color = color;
        }
    }

    /**
     * Clase que encapsula el resultado final del algoritmo de Dijkstra.
     */
    public class ResultadoDijkstra {

        public List<Nodo> camino;
        public double distanciaTotal;
        public List<Nodo> todosLosNodos; // para tabla

        public ResultadoDijkstra(List<Nodo> camino, double distanciaTotal, List<Nodo> todosLosNodos) {
            this.camino = camino;
            this.distanciaTotal = distanciaTotal;
            this.todosLosNodos = todosLosNodos;
        }
    }

    /**
     * Ejecuta el algoritmo de Dijkstra para encontrar el camino más corto entre
     * dos localidades.
     *
     * @param origen Nodo de partida.
     * @param destino Nodo de llegada.
     * @return Lista de pasos de la ejecución para su animación en la UI.
     */
    public List<PasoDijkstra> ejecutarDijkstra(Nodo origen, Nodo destino) {
        limpiarResultadoAlgoritmos();

        List<PasoDijkstra> pasos = new ArrayList<>();
        List<Nodo> nodos = grafo.getNodos();

        for (Nodo n : nodos) {
            n.setDistancia(Double.MAX_VALUE);
            n.setPredecesor(null);
        }

        origen.setDistancia(0);

        List<Nodo> noVisitados = new ArrayList<>(nodos);

        while (!noVisitados.isEmpty()) {

            Nodo actual = noVisitados.stream()
                    .min((a, b) -> Double.compare(a.getDistancia(), b.getDistancia()))
                    .orElse(null);

            if (actual == null) {
                break;
            }

            noVisitados.remove(actual);

            // GRIS = descubierto / explorando
            pasos.add(new PasoDijkstra(actual, null, Color.GRAY));

            if (actual == destino) {
                break;
            }

            for (Arista arista : actual.getAdyacentes()) {
                Nodo vecino = arista.getDestino();

                double nuevaDist = actual.getDistancia() + arista.getPeso();

                if (nuevaDist < vecino.getDistancia()) {
                    vecino.setDistancia(nuevaDist);
                    vecino.setPredecesor(actual);

                    // vecino descubierto → gris
                    pasos.add(new PasoDijkstra(vecino, arista, Color.GRAY));
                }
            }
        }

        return pasos;
    }

    /**
     * Genera un modelo de tabla con los resultados finales de distancias tras
     * Dijkstra.
     *
     * @param nodos Lista de nodos a incluir en la tabla.
     * @return DefaultTableModel con las columnas: Nodo, Distancia y Predecesor.
     */
    public DefaultTableModel getTablaDijkstra(List<Nodo> nodos) {
        String[] columnas = {"Nodo", "Distancia", "Predecesor"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Nodo n : nodos) {
            modelo.addRow(new Object[]{
                n.getNombre(),
                n.getDistancia() == Double.MAX_VALUE ? "∞" : (int) n.getDistancia() + " km",
                n.getPredecesor() == null ? "-" : n.getPredecesor().getNombre()
            });
        }

        return modelo;
    }
}
