package icc.pap.pl1.patrones.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.servicio.ServicioPedidos;

public class VentanaPedidos extends JFrame {
    private final ServicioPedidos servicio;

    private JComboBox<String> comboProductos;
    private JComboBox<String> comboIngredientes;
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    
    private JLabel lblIdDetalle;
    private JLabel lblProductoDetalle;
    private JLabel lblIngredientesDetalle;
    private JLabel lblTotalDetalle;

    private Pedido pedidoSeleccionado;

    public VentanaPedidos() {
        this.servicio = new ServicioPedidos();
        configurarEstiloGlobal();
        configurarVentana();
        inicializarComponentes();
        actualizarTabla();
    }

    private void configurarEstiloGlobal() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
    }

    private void configurarVentana() {
        setTitle("PedidoLab - Prototype + Decorator");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));
        getRootPane().setBorder(new EmptyBorder(12, 12, 12, 12));
        getContentPane().setBackground(new Color(245, 247, 250));
    }

    private void inicializarComponentes() {
        // ----------------------------------------------------
        // PANEL IZQUIERDO: Contiene controles de seleccion y edicion juntos
        // ----------------------------------------------------
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setOpaque(false);
        panelIzquierdo.setPreferredSize(new Dimension(320, 0));

        // Subpanel 1: Seleccion de producto base
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(Color.WHITE);
        panel1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230)),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTitulo1 = new JLabel("1. Seleccionar producto base");
        lblTitulo1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo1.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        // Uso de guion normal para evitar caracteres extraños de codificacion
        comboProductos = new JComboBox<>(new String[]{"Cafe - $2.20", "Ensalada sencilla - $3.00", "Pizza sencilla - $5.00"});
        comboProductos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        comboProductos.setAlignmentX(JComboBox.LEFT_ALIGNMENT);

        JButton btnClonar = new JButton("Clonar producto");
        btnClonar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnClonar.setBackground(new Color(13, 110, 253));
        btnClonar.setForeground(Color.WHITE);
        btnClonar.setFocusPainted(false);
        btnClonar.setAlignmentX(JButton.LEFT_ALIGNMENT);
        btnClonar.addActionListener(e -> clonarProductoAccion());

        panel1.add(lblTitulo1);
        panel1.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
        panel1.add(comboProductos);
        panel1.add(javax.swing.Box.createRigidArea(new Dimension(0, 12)));
        panel1.add(btnClonar);

        // Subpanel 2: Agregar ingredientes
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(Color.WHITE);
        panel2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230)),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTitulo2 = new JLabel("2. Agregar ingredientes");
        lblTitulo2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo2.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        comboIngredientes = new JComboBox<>(new String[]{"Azucar", "Crema", "Tomate", "Pollo", "Queso", "Pepperoni"});
        comboIngredientes.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        comboIngredientes.setAlignmentX(JComboBox.LEFT_ALIGNMENT);

        JButton btnAgregar = new JButton("Agregar ingrediente");
        btnAgregar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnAgregar.setBackground(new Color(25, 135, 84));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setAlignmentX(JButton.LEFT_ALIGNMENT);
        btnAgregar.addActionListener(e -> agregarIngredienteAccion());

        JButton btnReiniciar = new JButton("Reiniciar pedidos");
        btnReiniciar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.setAlignmentX(JButton.LEFT_ALIGNMENT);
        btnReiniciar.addActionListener(e -> reiniciarAccion());

        panel2.add(lblTitulo2);
        panel2.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
        panel2.add(comboIngredientes);
        panel2.add(javax.swing.Box.createRigidArea(new Dimension(0, 12)));
        panel2.add(btnAgregar);
        panel2.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
        panel2.add(btnReiniciar);

        panelIzquierdo.add(panel1);
        panelIzquierdo.add(javax.swing.Box.createRigidArea(new Dimension(0, 12)));
        panelIzquierdo.add(panel2);

        add(panelIzquierdo, BorderLayout.WEST);

        // ----------------------------------------------------
        // PANEL DERECHO: Tabla y Detalle apilados verticalmente
        // ----------------------------------------------------
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1, 0, 12));
        panelDerecho.setOpaque(false);

        // Panel 3: Tabla de pedidos creados
        JPanel panel3 = new JPanel(new BorderLayout(5, 5));
        panel3.setBackground(Color.WHITE);
        panel3.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230)),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTitulo3 = new JLabel("3. Pedidos creados");
        lblTitulo3.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel3.add(lblTitulo3, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Producto", "Composicion", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaPedidos = new JTable(modeloTabla);
        tablaPedidos.setRowHeight(25);
        tablaPedidos.getSelectionModel().addListSelectionListener(e -> seleccionarPedidoAccion());
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaPedidos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        panel3.add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);
        panelDerecho.add(panel3);

        // Panel 4: Detalle del pedido seleccionado
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.setBackground(Color.WHITE);
        panel4.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230)),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTitulo4 = new JLabel("4. Detalle del pedido seleccionado");
        lblTitulo4.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo4.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        lblIdDetalle = new JLabel("ID de pedido: N/A");
        lblIdDetalle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        lblProductoDetalle = new JLabel("Producto: N/A");
        lblProductoDetalle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        lblIngredientesDetalle = new JLabel("Ingredientes: N/A");
        lblIngredientesDetalle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        lblTotalDetalle = new JLabel("Total del pedido: $0.00");
        lblTotalDetalle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotalDetalle.setForeground(new Color(13, 110, 253));
        lblTotalDetalle.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        panel4.add(lblTitulo4);
        panel4.add(javax.swing.Box.createRigidArea(new Dimension(0, 12)));
        panel4.add(lblIdDetalle);
        panel4.add(javax.swing.Box.createRigidArea(new Dimension(0, 6)));
        panel4.add(lblProductoDetalle);
        panel4.add(javax.swing.Box.createRigidArea(new Dimension(0, 6)));
        panel4.add(lblIngredientesDetalle);
        panel4.add(javax.swing.Box.createRigidArea(new Dimension(0, 12)));
        panel4.add(lblTotalDetalle);

        panelDerecho.add(panel4);
        add(panelDerecho, BorderLayout.CENTER);
    }

    private void clonarProductoAccion() {
        int index = comboProductos.getSelectedIndex();
        String clave = index == 0 ? "cafe" : (index == 1 ? "ensalada" : "pizza");
        try {
            servicio.clonarProducto(clave);
            actualizarTabla();
            seleccionarUltimoPedido();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarIngredienteAccion() {
        if (pedidoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido de la tabla primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String ingrediente = (String) comboIngredientes.getSelectedItem();
        try {
            Pedido actualizado = servicio.agregarIngrediente(pedidoSeleccionado, ingrediente);
            pedidoSeleccionado = actualizado;
            actualizarTabla();
            actualizarDetalle();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Operacion rechazada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reiniciarAccion() {
        servicio.reiniciarPedidos();
        pedidoSeleccionado = null;
        actualizarTabla();
        actualizarDetalle();
    }

    private void seleccionarPedidoAccion() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila >= 0) {
            List<Pedido> lista = servicio.getPedidosCreados();
            if (fila < lista.size()) {
                pedidoSeleccionado = lista.get(fila);
                actualizarDetalle();
            }
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<Pedido> lista = servicio.getPedidosCreados();
        for (Pedido p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getId(),
                p.getTipo().getNombre(),
                p.getDescripcion(),
                String.format("$%.2f", p.getPrecio())
            });
        }
    }

    private void seleccionarUltimoPedido() {
        int rowCount = tablaPedidos.getRowCount();
        if (rowCount > 0) {
            tablaPedidos.setRowSelectionInterval(rowCount - 1, rowCount - 1);
        }
    }

    private void actualizarDetalle() {
        if (pedidoSeleccionado != null) {
            lblIdDetalle.setText("ID de pedido: " + pedidoSeleccionado.getId());
            lblProductoDetalle.setText("Producto: " + pedidoSeleccionado.getTipo().getNombre());
            lblIngredientesDetalle.setText("Ingredientes: " + pedidoSeleccionado.getDescripcion());
            lblTotalDetalle.setText(String.format("Total del pedido: $%.2f", pedidoSeleccionado.getPrecio()));
        } else {
            lblIdDetalle.setText("ID de pedido: N/A");
            lblProductoDetalle.setText("Producto: N/A");
            lblIngredientesDetalle.setText("Ingredientes: N/A");
            lblTotalDetalle.setText("Total del pedido: $0.00");
        }
    }
}