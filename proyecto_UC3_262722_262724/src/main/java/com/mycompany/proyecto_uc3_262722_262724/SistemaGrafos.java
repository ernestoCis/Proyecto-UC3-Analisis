import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * 
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class SistemaGrafos extends JFrame {

    private final Color AZUL_MARINO = new Color(23, 32, 42);
    private final Color AZUL_HOVER = new Color(33, 47, 61);
    private final Color BLANCO_TEXTO = new Color(240, 243, 244);
    private final Color LINEA_SEPARADORA = new Color(52, 73, 94);
    
    private JPanel panelDerecho;

    public SistemaGrafos() {
        setTitle("Grafo Oaxaca");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        
        JLabel lblBienvenida = new JLabel("Grafo", SwingConstants.CENTER);
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
            } else {
                actualizarPanelDerecho(texto);
            }
        });

        return btn;
    }

    private void actualizarPanelDerecho(String titulo) {
        panelDerecho.removeAll();
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        
        panelDerecho.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.add(new JLabel("Contenido de " + titulo + " irá aquí..."));
        panelDerecho.add(contenido, BorderLayout.CENTER);

        panelDerecho.revalidate();
        panelDerecho.repaint();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(SistemaGrafos::new);
    }
}