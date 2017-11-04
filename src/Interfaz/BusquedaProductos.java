package Interfaz;

import javax.swing.*;

public class BusquedaProductos {
    private JPanel panelPrincipal;
    private JCheckBox amazonCheckBox;
    private JCheckBox elCorteInglesCheckBox;
    private JCheckBox fnacCheckBox;
    private JCheckBox mediaMarktCheckBox;
    private JButton aceptarButton;
    private JComboBox articuloComboBox;
    private JComboBox marcaComboBox;

    public static void main(String[] args){
        JFrame jFrame = new JFrame("Busqueda de productos");
        jFrame.setContentPane(new BusquedaProductos().panelPrincipal);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
