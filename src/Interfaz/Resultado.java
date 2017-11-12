package Interfaz;

import Logica.FilaResultado;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Vector;

public class Resultado extends JDialog {
    private JPanel contentPane;
    private JButton aceptarButton;
    private JTable resultadoTable;
    private JButton buttonCancel;
    public Vector<String> nomColumnas;
    public Vector<Vector> datosColumnnas;
    public Resultado(ArrayList<FilaResultado> listaResultado) {
        Map<String,FilaResultado> hs = new HashMap<>();
        for(FilaResultado fru: listaResultado){
            hs.put(fru.Nombre,fru);
        }
        listaResultado.clear();
        listaResultado.addAll(hs.values());
        for(FilaResultado fr : listaResultado){
            Vector fila = new Vector();
            fila.add(fr.getNombre());
            fila.add(fr.getPrecio());
            fila.add(fr.getTienda());
            datosColumnnas.addElement(fila);
        }
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(aceptarButton);
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        nomColumnas = new Vector<String>();
        datosColumnnas = new Vector<Vector>();
        nomColumnas.add("Nombre");
        nomColumnas.add("Precio");
        nomColumnas.add("Tienda");
        resultadoTable = new JTable(datosColumnnas,nomColumnas);
    }
}
