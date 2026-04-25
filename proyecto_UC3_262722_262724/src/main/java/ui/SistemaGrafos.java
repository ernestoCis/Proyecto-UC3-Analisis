package ui;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import modelo.Grafo;
import modelo.Nodo;

/**
 *
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class SistemaGrafos extends JFrame {

    private Controlador controlador;

    private final Color AZUL_MARINO = new Color(23, 32, 42);
    private final Color AZUL_HOVER = new Color(33, 47, 61);
    private final Color BLANCO_TEXTO = new Color(240, 243, 244);
    private final Color LINEA_SEPARADORA = new Color(52, 73, 94);

    private JPanel panelDerecho;

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
            if (texto.equals("Salir")) {
                System.exit(0);
            } else if (texto.equals("Visualización de grafo")) {
                JPopupMenu popup = crearSubmenuVisualizacion();
                popup.show(btn, btn.getWidth(), 0); // aparece a la derecha
            } else if (texto.equals("Recorridos")) {
                JPopupMenu popup = crearSubmenuRecorridos();
                popup.show(btn, btn.getWidth(), 0);
            } else {
                actualizarPanelDerecho(texto);
            }
        });

        return btn;
    }

    private void actualizarPanelDerecho(String titulo) {
        panelDerecho.setLayout(new BorderLayout());

        GrafoPanel lienzo = new GrafoPanel(controlador.getGrafo());

        panelDerecho.removeAll();
        panelDerecho.add(lienzo, BorderLayout.CENTER);

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

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

    private void mostrarGrafo() {
        controlador.limpiarResultadoAlgoritmos();

        panelDerecho.removeAll();

        Grafo grafo = controlador.getGrafo();
        GrafoPanel panel = new GrafoPanel(grafo);

        panelDerecho.add(panel, BorderLayout.CENTER);

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    private void mostrarBFS() {
        mostrarGrafo();
        
        String entrada = JOptionPane.showInputDialog(this, "Ingrese localidad semilla:");

        if (entrada != null && !entrada.trim().isEmpty()) {
            Nodo semilla = controlador.buscarNodoPorNombre(entrada);

            if (semilla != null) {
                controlador.limpiarResultadoAlgoritmos();
                List<Nodo> visita = controlador.ejecutarBFS(semilla);

                panelDerecho.repaint();

                StringBuilder sb = new StringBuilder();
                sb.append("Orden de visita desde: ").append(semilla.getNombre()).append("\n");
                sb.append("-------------------------------------------\n\n");

                for (int i = 0; i < visita.size(); i++) {
                    Nodo n = visita.get(i);
                    sb.append(String.format(" [%02d] %-20s (Nivel %d)\n",
                            (i + 1), n.getNombre(), (int) n.getDistancia()));
                }

                DialogoPersonalizado dp = new DialogoPersonalizado(this, "Secuencia de Recorrido BFS", sb.toString());
                dp.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "La localidad no existe en el registro.");
            }
        }
    }

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
    
    private JPopupMenu crearSubmenuRecorridos() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem bfs = new JMenuItem("Recorrido BFS (Anchura)");
        bfs.addActionListener(e -> mostrarBFS());

        JMenuItem dfs = new JMenuItem("Recorrido DFS (Profundidad)");
        dfs.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Próximamente: Recorrido por Profundidad");
        });

        popup.add(bfs);
        popup.add(dfs);

        return popup;
    }
}
