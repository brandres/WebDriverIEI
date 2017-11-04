package Interfaz;

import javax.swing.*;

public class BusquedaProductos {
    private JCheckBox checkBox1;
    private JPanel panel1;
    public static void main(String[] args){
        JFrame jFrame = new JFrame("Busqueda de productos");
        jFrame.setContentPane(new BusquedaProductos().panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
