package ui;

import controlador.Controlador;
import controlador.Controlador.PasoAlgoritmo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;

/**
 * Ventana principal del sistema "Grafo Oaxaca". Proporciona la interfaz de
 * usuario para la visualización del mapa, tablas de datos, ejecución de
 * recorridos (BFS/DFS) y reportes de complejidad.
 *
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class SistemaGrafos extends JFrame {

    private Timer animacionActual;

    /**
     * Instancia del controlador para gestionar la lógica del grafo.
     */
    private Controlador controlador;

    /**
     * Constante de color para la identidad visual
     */
    private final Color AZUL_MARINO = new Color(23, 32, 42);
    /**
     * Constante de color para la identidad visual
     */
    private final Color AZUL_HOVER = new Color(33, 47, 61);
    /**
     * Constante de color para la identidad visual
     */
    private final Color BLANCO_TEXTO = new Color(240, 243, 244);
    /**
     * Constante de color para la identidad visual
     */
    private final Color LINEA_SEPARADORA = new Color(52, 73, 94);

    /**
     * Panel contenedor de la derecha donde se renderiza el contenido dinámico.
     */
    private JPanel panelDerecho;

    /**
     * Constructor que inicializa la ventana principal, el menú lateral y el
     * panel de bienvenida.
     */
    public SistemaGrafos() {
        controlador = new Controlador();

        setTitle("Grafo Oaxaca");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(AZUL_MARINO);
        menuLateral.setPreferredSize(new Dimension(280, 0));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));

        menuLateral.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel lblTitulo = new JLabel("MENÚ DEL SISTEMA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(lblTitulo);

        menuLateral.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] opciones = {
            "Visualización de grafo",
            "Recorridos",
            "Árbol de Expansión Mínima",
            "Rutas más cortas",
            "Reportes de complejidad",
            "Salir"
        };

        for (String opcion : opciones) {
            JButton btn = crearBotonMenu(opcion);
            menuLateral.add(btn);
        }

        panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(245, 245, 245));
        panelDerecho.setLayout(new BorderLayout());

        JLabel lblBienvenida = new JLabel("Grafo de Oaxaca", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
        lblBienvenida.setForeground(Color.DARK_GRAY);
        panelDerecho.add(lblBienvenida, BorderLayout.CENTER);

        add(menuLateral, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Crea un botón estilizado para el menú lateral con efectos hover y
     * eventos.
     *
     * @param texto El nombre de la opción.
     * @return Un objeto JButton configurado.
     */
    private JButton crearBotonMenu(String texto) {
        JButton btn = new JButton(texto);

        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        btn.setPreferredSize(new Dimension(280, 55));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBackground(AZUL_MARINO);
        btn.setForeground(BLANCO_TEXTO);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 1, 0, LINEA_SEPARADORA),
                BorderFactory.createEmptyBorder(0, 25, 0, 0)
        ));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(AZUL_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(AZUL_MARINO);
            }
        });

        btn.addActionListener(e -> {
            detenerAnimacion();
            if (texto.equals("Salir")) {
                System.exit(0);
            } else if (texto.equals("Visualización de grafo")) {
                JPopupMenu popup = crearSubmenuVisualizacion();
                popup.show(btn, btn.getWidth(), 0); // aparece a la derecha
            } else if (texto.equals("Recorridos")) {
                JPopupMenu popup = crearSubmenuRecorridos();
                popup.show(btn, btn.getWidth(), 0);
            } else if (texto.equals("Árbol de Expansión Mínima")) {
                JPopupMenu popup = crearSubmenuMST();
                popup.show(btn, btn.getWidth(), 0);
            } else if (texto.equals("Rutas más cortas")) {
                JPopupMenu popup = crearSubmenuDijkstra();
                popup.show(btn, btn.getWidth(), 0);
            } else if (texto.equals("Reportes de complejidad")) {
                JPopupMenu popup = crearSubmenuReportes();
                popup.show(btn, btn.getWidth(), 0);
            } else {
                actualizarPanelDerecho(texto);
            }
        }
        );

        return btn;
    }

    /**
     * Crea un menú emergente con las opciones para visualizar reportes de
     * complejidad.
     *
     * * @return JPopupMenu con las opciones de reportes.
     */
    private JPopupMenu crearSubmenuReportes() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem recorridos = new JMenuItem("Recorridos");
        recorridos.addActionListener(e -> mostrarReporteComplejidadRecorridos());

        JMenuItem mst = new JMenuItem("Árbol de Expansión Mínima");
        mst.addActionListener(e -> mostrarReporteComplejidadMST());

        JMenuItem rmc = new JMenuItem("Ruta más corta");
        rmc.addActionListener(e -> mostrarReporteComplejidadRuta());

        popup.add(recorridos);
        popup.add(mst);
        popup.add(rmc);

        return popup;
    }

    /**
     * Reemplaza el contenido del panel derecho por el lienzo de dibujo del
     * grafo.
     *
     * @param titulo El título de la acción ejecutada.
     */
    private void actualizarPanelDerecho(String titulo) {
        panelDerecho.setLayout(new BorderLayout());

        GrafoPanel lienzo = new GrafoPanel(controlador.getGrafo());

        panelDerecho.removeAll();
        panelDerecho.add(lienzo, BorderLayout.CENTER);

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    /**
     * Crea un menú emergente con las opciones de visualización (Tabla o
     * Gráfico).
     *
     * @return JPopupMenu configurado.
     */
    private JPopupMenu crearSubmenuVisualizacion() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem tabla = new JMenuItem("Mostrar tabla de nodos y aristas");
        tabla.addActionListener(e -> mostrarTabla());

        JMenuItem grafico = new JMenuItem("Mostrar representación gráfica");
        grafico.addActionListener(e -> mostrarGrafo());

        popup.add(tabla);
        popup.add(grafico);

        return popup;
    }

    /**
     * Genera y muestra las tablas de datos de nodos y aristas en el panel
     * derecho.
     */
    private void mostrarTabla() {
        panelDerecho.removeAll();
        panelDerecho.setLayout(new BorderLayout());
        panelDerecho.setBackground(Color.WHITE);

        JPanel contenedorTablas = new JPanel();
        contenedorTablas.setLayout(new BoxLayout(contenedorTablas, BoxLayout.Y_AXIS));
        contenedorTablas.setBackground(Color.WHITE);
        contenedorTablas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Nodos
        JLabel lblNodos = new JLabel("TABLA DE NODOS (LOCALIDADES)");
        lblNodos.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNodos.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedorTablas.add(lblNodos);
        contenedorTablas.add(Box.createRigidArea(new Dimension(0, 10)));

        JTable tablaNodos = new JTable(controlador.getTablaNodos());
        estilizarTabla(tablaNodos);
        JScrollPane scrollNodos = new JScrollPane(tablaNodos);
        scrollNodos.setPreferredSize(new Dimension(0, 200));
        scrollNodos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        contenedorTablas.add(scrollNodos);

        contenedorTablas.add(Box.createRigidArea(new Dimension(0, 30)));

        // Aristas
        JLabel lblAristas = new JLabel("TABLA DE ARISTAS (CARRETERAS)");
        lblAristas.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblAristas.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedorTablas.add(lblAristas);
        contenedorTablas.add(Box.createRigidArea(new Dimension(0, 10)));

        JTable tablaAristas = new JTable(controlador.getTablaAristas());
        estilizarTabla(tablaAristas);
        JScrollPane scrollAristas = new JScrollPane(tablaAristas);
        scrollAristas.setPreferredSize(new Dimension(0, 200));
        scrollAristas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        contenedorTablas.add(scrollAristas);

        panelDerecho.add(new JScrollPane(contenedorTablas), BorderLayout.CENTER);

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    /**
     * Limpia resultados previos y muestra el panel de representación gráfica.
     */
    private void mostrarGrafo() {
        controlador.limpiarResultadoAlgoritmos();

        panelDerecho.removeAll();

        Grafo grafo = controlador.getGrafo();
        GrafoPanel panel = new GrafoPanel(grafo);

        panelDerecho.add(panel, BorderLayout.CENTER);

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    /**
     * Solicita una semilla y ejecuta el recorrido en anchura (BFS). Muestra la
     * secuencia resultante en un diálogo.
     */
    private void mostrarBFS() {
        mostrarGrafo();

        String entrada = JOptionPane.showInputDialog(this, "Ingrese localidad semilla para BFS:");

        if (entrada != null && !entrada.trim().isEmpty()) {
            Nodo semilla = controlador.buscarNodoPorNombre(entrada);

            if (semilla != null) {
                controlador.limpiarResultadoAlgoritmos();
                List<PasoAlgoritmo> pasos = controlador.ejecutarBFS(semilla);

                animacionActual = new Timer(100, null);
                Timer timer = animacionActual;

                final int[] indice = {0};

                timer.addActionListener(e -> {
                    if (indice[0] < pasos.size()) {
                        PasoAlgoritmo p = pasos.get(indice[0]);

                        p.nodo.setEstado(p.colorNuevo);
                        if (p.arista != null) {
                            p.arista.setEsParteDeResultado(true);
                        }

                        panelDerecho.repaint();
                        indice[0]++;
                    } else {
                        timer.stop();
                        mostrarReporteFinalBFS(semilla, pasos);
                    }
                });

                timer.start();

            } else {
                JOptionPane.showMessageDialog(this, "La localidad no existe en el registro.");
            }
        }
    }

    /**
     * Método auxiliar para mostrar el diálogo con los resultados una vez
     * acabada la animación.
     */
    private void mostrarReporteFinalBFS(Nodo semilla, List<PasoAlgoritmo> pasos) {
        StringBuilder sb = new StringBuilder();
        sb.append("Recorrido BFS finalizado desde: ").append(semilla.getNombre()).append("\n");
        sb.append("-------------------------------------------\n\n");

        int contador = 1;
        for (PasoAlgoritmo p : pasos) {
            if (p.colorNuevo.equals(Color.BLACK)) {
                sb.append(String.format(" [%02d] %-20s (Nivel %d)\n",
                        contador++, p.nodo.getNombre(), (int) p.nodo.getDistancia()));
            }
        }

        DialogoPersonalizado dp = new DialogoPersonalizado(this, "Secuencia de Recorrido BFS", sb.toString());
        dp.setVisible(true);
        dp.toFront();
    }

    /**
     * Solicita una semilla y ejecuta el recorrido en profundidad (DFS). Muestra
     * el tiempo de descubrimiento en un diálogo.
     */
    private void mostrarDFS() {
        mostrarGrafo();

        String entrada = JOptionPane.showInputDialog(this, "Ingrese localidad semilla para DFS:");

        if (entrada != null && !entrada.trim().isEmpty()) {
            Nodo semilla = controlador.buscarNodoPorNombre(entrada);

            if (semilla != null) {
                controlador.limpiarResultadoAlgoritmos();
                List<PasoAlgoritmo> pasos = controlador.ejecutarDFS(semilla);

                animacionActual = new Timer(100, null);
                Timer timer = animacionActual;
                final int[] indice = {0};

                timer.addActionListener(e -> {
                    if (indice[0] < pasos.size()) {
                        PasoAlgoritmo p = pasos.get(indice[0]);

                        p.nodo.setEstado(p.colorNuevo);
                        if (p.arista != null) {
                            p.arista.setEsParteDeResultado(true);
                        }

                        panelDerecho.repaint();
                        indice[0]++;
                    } else {
                        timer.stop();
                        mostrarReporteFinalDFS(semilla, pasos);
                    }
                });

                timer.start();

            } else {
                JOptionPane.showMessageDialog(this, "La localidad no existe en el registro.");
            }
        }
    }

    /**
     * Muestra el reporte final del recorrido DFS incluyendo tiempos de
     * descubrimiento y finalización.
     *
     * * @param semilla El nodo inicial del recorrido.
     * @param pasos Lista de pasos de ejecución para extraer los datos de los
     * nodos.
     */
    private void mostrarReporteFinalDFS(Nodo semilla, List<PasoAlgoritmo> pasos) {
        StringBuilder sb = new StringBuilder();
        sb.append("RECORRIDO DFS FINALIZADO\n");
        sb.append("Origen: ").append(semilla.getNombre()).append("\n");
        sb.append("-----------------------------------------------------------------\n");

        sb.append(String.format("%-25s | %-15s | %-12s\n",
                "LOCALIDAD", "DESCUBRIMIENTO", "FINALIZACIÓN"));
        sb.append("-----------------------------------------------------------------\n\n");

        Set<Nodo> agregados = new HashSet<>();

        for (PasoAlgoritmo p : pasos) {
            Nodo n = p.nodo;
            if (!agregados.contains(n)) {
                sb.append(String.format("%-25s | d = %-10d | f = %-8d\n",
                        n.getNombre(),
                        (int) n.getDistancia(),
                        (int) n.getF()));
                agregados.add(n);
            }
        }
        sb.append("----------------------------------------------------------------------\n");

        DialogoPersonalizado dp = new DialogoPersonalizado(this, "Secuencia de Recorrido DFS", sb.toString());
        dp.setVisible(true);
    }

    /**
     * Aplica un diseño uniforme y colores del sistema a las tablas JTable.
     *
     * @param tabla La tabla a la que se aplicará el estilo.
     */
    private void estilizarTabla(JTable tabla) {
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setGridColor(new Color(210, 210, 210));
        tabla.setShowVerticalLines(false);
        tabla.setSelectionBackground(new Color(44, 62, 80));
        tabla.setSelectionForeground(Color.WHITE);

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setOpaque(false);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setBackground(AZUL_MARINO);
                setForeground(BLANCO_TEXTO);
                setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(52, 73, 94)));

                return this;
            }
        });

        tabla.setBorder(null);
    }

    /**
     * Crea un menú emergente para seleccionar entre BFS y DFS.
     *
     * @return JPopupMenu configurado.
     */
    private JPopupMenu crearSubmenuRecorridos() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem bfs = new JMenuItem("Recorrido BFS (Anchura)");
        bfs.addActionListener(e -> mostrarBFS());

        JMenuItem dfs = new JMenuItem("Recorrido DFS (Profundidad)");
        dfs.addActionListener(e -> {
            mostrarDFS();
        });

        popup.add(bfs);
        popup.add(dfs);

        return popup;
    }

    /**
     * Genera un reporte de complejidad temporal en formato HTML y lo muestra en
     * el panel derecho.
     */
    private void mostrarReporteComplejidadRecorridos() {
        panelDerecho.removeAll();
        panelDerecho.setLayout(new BorderLayout());

        JTextPane textoReporte = new JTextPane();
        textoReporte.setContentType("text/html");
        textoReporte.setEditable(false);
        textoReporte.setBackground(new Color(245, 245, 245));

        // CSS y Contenido
        String html = "<html><body style='font-family: Segoe UI, sans-serif; margin: 25px; color: #2C3E50;'>"
                + "<h1 style='color: #17202A; border-bottom: 2px solid #17202A; padding-bottom: 10px;'>Análisis de Complejidad</h1>"
                + "<div style='margin-bottom: 20px;'>"
                + "  <h2 style='color: #2E86C1;'>Breadth-First Search (BFS)</h2>"
                + "  <p>Explora el grafo nivel por nivel usando una <b>Cola (FIFO)</b>.</p>"
                + "  <ul style='list-style-type: square;'>"
                + "    <li><b>Tiempo:</b> O(V + E)</li>"
                + "    <li><b>Espacio:</b> O(V) - Almacena los nodos en la cola.</li>"
                + "  </ul>"
                + "</div>"
                + "<div style='margin-bottom: 20px;'>"
                + "  <h2 style='color: #2E86C1;'>Depth-First Search (DFS)</h2>"
                + "  <p>Explora cada rama hasta el fondo usando <b>Recursividad (Stack)</b>.</p>"
                + "  <ul style='list-style-type: square;'>"
                + "    <li><b>Tiempo:</b> O(V + E)</li>"
                + "    <li><b>Espacio:</b> O(V) - Debido a la pila de llamadas recursivas.</li>"
                + "  </ul>"
                + "</div>"
                + "<div style='background-color: #D6EAF8; padding: 15px; border-left: 5px solid #17202A;'>"
                + "  <b>Nota:</b> En ambos casos, <i>V</i> representa el número de ciudades de Oaxaca "
                + "  y <i>E</i> el número de carreteras que las conectan."
                + "</div>"
                + "</body></html>";

        textoReporte.setText(html);

        JScrollPane scroll = new JScrollPane(textoReporte);
        scroll.setBorder(null);

        panelDerecho.add(scroll, BorderLayout.CENTER);

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    /**
     * Muestra un reporte en formato HTML sobre la complejidad del algoritmo de
     * Kruskal (MST).
     */
    private void mostrarReporteComplejidadMST() {
        panelDerecho.removeAll();
        panelDerecho.setLayout(new BorderLayout());

        JTextPane textoReporte = new JTextPane();
        textoReporte.setContentType("text/html");
        textoReporte.setEditable(false);
        textoReporte.setBackground(new Color(245, 245, 245));

        String html = "<html><body style='font-family: Segoe UI, sans-serif; margin: 25px; color: #2C3E50;'>"
                + "<h1 style='color: #17202A; border-bottom: 2px solid #17202A;'>Árbol de Expansión Mínima</h1>"
                + "<div style='margin-top:20px;'>"
                + "<h2 style='color:#2E86C1;'>Algoritmo de Kruskal</h2>"
                + "<p>Construye un árbol mínimo seleccionando aristas de menor peso sin formar ciclos.</p>"
                + "<ul style='list-style-type:square;'>"
                + "<li><b>Tiempo:</b> O(E log E)</li>"
                + "<li><b>Espacio:</b> O(V)</li>"
                + "</ul>"
                + "<p><b>¿Por qué?</b></p>"
                + "<ul>"
                + "<li>Se ordenan las aristas → O(E log E)</li>"
                + "<li>Se usa Union-Find para evitar ciclos</li>"
                + "</ul>"
                + "</div>"
                + "<div style='background-color:#D6EAF8; padding:15px; border-left:5px solid #17202A;'>"
                + "<b>Nota:</b> E = carreteras, V = ciudades."
                + "</div>"
                + "</body></html>";

        textoReporte.setText(html);

        JScrollPane scroll = new JScrollPane(textoReporte);
        scroll.setBorder(null);

        panelDerecho.add(scroll, BorderLayout.CENTER);
        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    /**
     * Muestra un reporte en formato HTML sobre la complejidad del algoritmo de
     * Dijkstra.
     */
    private void mostrarReporteComplejidadRuta() {
        panelDerecho.removeAll();
        panelDerecho.setLayout(new BorderLayout());

        JTextPane textoReporte = new JTextPane();
        textoReporte.setContentType("text/html");
        textoReporte.setEditable(false);
        textoReporte.setBackground(new Color(245, 245, 245));

        String html = "<html><body style='font-family: Segoe UI, sans-serif; margin: 25px; color: #2C3E50;'>"
                + "<h1 style='color: #17202A; border-bottom: 2px solid #17202A;'>Rutas Más Cortas</h1>"
                + "<div style='margin-top:20px;'>"
                + "<h2 style='color:#2E86C1;'>Algoritmo de Dijkstra</h2>"
                + "<p>Encuentra la ruta más corta desde un nodo origen a todos los demás.</p>"
                + "<ul style='list-style-type:square;'>"
                + "<li><b>Tiempo:</b> O((V + E) log V)</li>"
                + "<li><b>Espacio:</b> O(V)</li>"
                + "</ul>"
                + "<p><b>¿Por qué?</b></p>"
                + "<ul>"
                + "<li>Se usa una cola de prioridad (heap)</li>"
                + "<li>Cada actualización cuesta log V</li>"
                + "</ul>"
                + "</div>"
                + "<div style='background-color:#D6EAF8; padding:15px; border-left:5px solid #17202A;'>"
                + "<b>Nota:</b> Ideal para grafos con pesos positivos (como distancias en km)."
                + "</div>"
                + "</body></html>";

        textoReporte.setText(html);

        JScrollPane scroll = new JScrollPane(textoReporte);
        scroll.setBorder(null);

        panelDerecho.add(scroll, BorderLayout.CENTER);
        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    /**
     * Crea un menú emergente para ejecutar el algoritmo de Kruskal.
     *
     * * @return JPopupMenu configurado.
     */
    private JPopupMenu crearSubmenuMST() {
        /**
         * Crea un menú emergente para ejecutar el algoritmo de Kruskal.
         *
         * * @return JPopupMenu configurado.
         */
        JPopupMenu popup = new JPopupMenu();

        JMenuItem kruskal = new JMenuItem("Kruskal");
        kruskal.addActionListener(e -> mostrarMST());

        popup.add(kruskal);

        return popup;
    }

    /**
     * Ejecuta y anima visualmente el algoritmo de Kruskal para obtener el Árbol
     * de Expansión Mínima.
     */
    private void mostrarMST() {
        controlador.limpiarResultadoAlgoritmos();
        Controlador.ResultadoMST res = controlador.ejecutarKruskal();

        mostrarGrafo();

        animacionActual = new Timer(500, null);
        Timer timer = animacionActual;

        final int[] indice = {0};
        final boolean[] faseExploracion = {true};

        timer.addActionListener(e -> {
            if (indice[0] < res.aristas.size()) {

                Arista a = res.aristas.get(indice[0]);

                if (faseExploracion[0]) {
                    // FASE 1: EXPLORAR
                    a.getOrigen().setEstado(Color.GRAY);
                    a.getDestino().setEstado(Color.GRAY);

                    faseExploracion[0] = false;

                } else {
                    // FASE 2: CONFIRMAR
                    a.setEsParteDeResultado(true);

                    a.getOrigen().setEstado(Color.BLACK);
                    a.getDestino().setEstado(Color.BLACK);

                    faseExploracion[0] = true;
                    indice[0]++;
                }

                panelDerecho.repaint();

            } else {
                timer.stop();

                StringBuilder sb = new StringBuilder();
                sb.append("ÁRBOL DE EXPANSIÓN MÍNIMA (KRUSKAL)\n\n");

                for (Arista a : res.aristas) {
                    sb.append(a.getOrigen().getNombre())
                            .append(" -> ")
                            .append(a.getDestino().getNombre())
                            .append(" (")
                            .append((int) a.getPeso())
                            .append(" km)\n");
                }

                sb.append("\nPeso total: ")
                        .append((int) res.pesoTotal)
                        .append(" km");

                new DialogoPersonalizado(this, "Kruskal", sb.toString()).setVisible(true);
            }
        });

        timer.start();
    }

    // DIJKSTRA
    /**
     * Crea un menú emergente para ejecutar el algoritmo de Dijkstra.
     *
     * * @return JPopupMenu configurado.
     */
    private JPopupMenu crearSubmenuDijkstra() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem dijkstra = new JMenuItem("Dijkstra");
        dijkstra.addActionListener(e -> mostrarDijkstra());

        popup.add(dijkstra);

        return popup;
    }

    /**
     * Solicita origen y destino, ejecuta el algoritmo de Dijkstra y anima el
     * proceso de búsqueda.
     */
    private void mostrarDijkstra() {
        mostrarGrafo();

        String origenStr = JOptionPane.showInputDialog(this, "Ingrese ciudad ORIGEN:");
        String destinoStr = JOptionPane.showInputDialog(this, "Ingrese ciudad DESTINO:");

        if (origenStr == null || destinoStr == null) {
            return;
        }

        Nodo origen = controlador.buscarNodoPorNombre(origenStr);
        Nodo destino = controlador.buscarNodoPorNombre(destinoStr);

        if (origen == null || destino == null) {
            JOptionPane.showMessageDialog(this, "Ciudad no encontrada.");
            return;
        }

        List<Controlador.PasoDijkstra> pasos = controlador.ejecutarDijkstra(origen, destino);

        animacionActual = new Timer(120, null);
        Timer timer = animacionActual;

        final int[] i = {0};

        timer.addActionListener(e -> {
            if (i[0] < pasos.size()) {
                Controlador.PasoDijkstra p = pasos.get(i[0]);

                p.nodo.setEstado(p.color);

                if (p.arista != null) {
                    p.arista.setEsParteDeResultado(true);
                }

                panelDerecho.repaint();
                i[0]++;
            } else {
                timer.stop();

                // reconstruir camino final
                List<Nodo> camino = new ArrayList<>();
                Nodo actual = destino;

                while (actual != null) {
                    camino.add(0, actual);
                    actual = actual.getPredecesor();
                }

                for (int j = 0; j < camino.size() - 1; j++) {
                    Nodo a = camino.get(j);
                    Nodo b = camino.get(j + 1);

                    for (Arista ar : a.getAdyacentes()) {
                        if (ar.getDestino() == b) {
                            ar.setEsParteDeResultado(true);
                        }
                    }

                    // NEGRO = ruta final
                    a.setEstado(Color.BLACK);
                    b.setEstado(Color.BLACK);
                }

                panelDerecho.repaint();

                mostrarResultadoDijkstra(camino, destino);
            }
        });

        timer.start();
    }

    /**
     * Muestra una ventana con el resumen del camino más corto y una tabla con
     * los estados finales de los nodos.
     *
     * * @param camino Lista ordenada de nodos que conforman la ruta óptima.
     * @param destino Nodo destino para extraer la distancia total acumulada.
     */
    private void mostrarResultadoDijkstra(List<Nodo> camino, Nodo destino) {

        JPanel panel = new JPanel(new BorderLayout());

        // Texto
        JTextArea area = new JTextArea();
        area.setEditable(false);

        StringBuilder sb = new StringBuilder();
        sb.append("RUTA MÁS CORTA (DIJKSTRA)\n\n");

        for (Nodo n : camino) {
            sb.append(n.getNombre()).append(" → ");
        }

        sb.append("\n\nDistancia total: ")
                .append((int) destino.getDistancia())
                .append(" km");

        area.setText(sb.toString());

        //  Tabla
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Nodo", "Distancia", "Predecesor"}, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        for (Nodo n : controlador.getGrafo().getNodos()) {
            modelo.addRow(new Object[]{
                n.getNombre(),
                n.getDistancia() == Double.MAX_VALUE ? "∞" : (int) n.getDistancia() + " km",
                n.getPredecesor() == null ? "-" : n.getPredecesor().getNombre()
            });
        }

        JTable tabla = new JTable(modelo);
        estilizarTabla(tabla);

        panel.add(new JScrollPane(area), BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, panel, "Dijkstra", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Detiene la ejecución de cualquier animación (Timer) que se encuentre
     * activa en ese momento.
     */
    private void detenerAnimacion() {
        if (animacionActual != null && animacionActual.isRunning()) {
            animacionActual.stop();
        }
    }
}
