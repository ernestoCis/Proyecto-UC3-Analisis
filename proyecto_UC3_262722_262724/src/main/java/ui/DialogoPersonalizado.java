package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Ventana de diálogo personalizada y modal diseñada para mostrar información 
 * textual detallada (como secuencias de algoritmos o reportes) manteniendo 
 * la línea estática del sistema.
 * @author Paulina Guevara, Ernesto Cisneros
 */
public class DialogoPersonalizado extends JDialog {

    /**
     * Constructor que construye e inicializa el diálogo con un estilo azul marino.
     * @param padre     Ventana principal (Frame) sobre la cual se posicionará el diálogo.
     * @param titulo    Texto que aparecerá en el encabezado de la ventana.
     * @param contenido Texto descriptivo o secuencia que se mostrará en el área central.
     */
    public DialogoPersonalizado(Frame padre, String titulo, String contenido) {
        super(padre, titulo, true); // true para que sea modal

        Color AZUL_MARINO = new Color(23, 32, 42);
        Color BLANCO_TEXTO = new Color(240, 243, 244);

        setSize(500, 400);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(AZUL_MARINO);
        JLabel lblTitulo = new JLabel(titulo.toUpperCase());
        lblTitulo.setForeground(BLANCO_TEXTO);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelTitulo.add(lblTitulo);
        panelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextArea txtArea = new JTextArea(contenido);
        txtArea.setEditable(false);
        txtArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtArea.setBackground(new Color(245, 245, 245));
        txtArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JButton btnCerrar = new JButton("Entendido");

        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setBackground(AZUL_MARINO);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AZUL_MARINO, 1), 
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));

        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        panelBoton.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(panelTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }
}
