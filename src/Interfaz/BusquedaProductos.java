package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;

public class BusquedaProductos {
    public JPanel panelPrincipal;
    public JCheckBox amazonCheckBox;
    public JCheckBox elCorteInglesCheckBox;
    public JCheckBox fnacCheckBox;
    public JCheckBox mediaMarktCheckBox;
    public JButton aceptarButton;
    public JComboCheckBox articuloComboBox;
    public JComboCheckBox marcaComboBox;

    public BusquedaProductos() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Resultado dialog = new Resultado(getCafeterasDeTiendasSeleccionadas());
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    public void run() {
        JFrame jFrame = new JFrame("Busqueda de productos");
        jFrame.setContentPane(this.panelPrincipal);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static Vector getMarcasVector() {
        Vector v = new Vector();
        v.add("Marcas");
        v.add(new JCheckBox("Delonghi", false));
        v.add(new JCheckBox("Krups", false));
        v.add(new JCheckBox("Bosch", false));
        v.add(new JCheckBox("Saeco", false));
        v.add(new JCheckBox("Severin", false));
        v.add(new JCheckBox("FilterLogic", false));
        v.add(new JCheckBox("Philips", false));
        return v;
    }

    public static Vector getArcitulosVector() {
        Vector v = new Vector();
        v.add("Articulos");
        //Cafeteras automaticas, expreso manuales expreso automaticas
        v.add(new JCheckBox("Cafeteras expreso", false));
        //Cafeteras monodosis/ en capsula
        v.add(new JCheckBox("Cafeteras monodosis/capsula", false));
        //Cafeteras de goteo
        v.add(new JCheckBox("Cafeteras de goteo", false));
        return v;
    }

    private void createUIComponents() {
        articuloComboBox = new JComboCheckBox(getArcitulosVector());
        marcaComboBox = new JComboCheckBox(getMarcasVector());

    }

    public ArrayList<FilaResultado> getCafeterasDeTienda(Tienda t) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        ArrayList<String> marcaItems = marcaComboBox.getCheckedItems();
        ArrayList<String> articuloItems = articuloComboBox.getCheckedItems();
        if (articuloItems.contains("Cafeteras de goteo")) {
            res.addAll(t.getCafeterasGoteo(marcaItems));
        }
        if (articuloItems.contains("Cafeteras expreso")) {
            res.addAll(t.getCafeterasAutomaticas(marcaItems));
        }
        if (articuloItems.contains("Cafeteras monodosis/capsula")) {
            res.addAll(t.getCafeterasMonodosis(marcaItems));
        }
        t.driver.quit();
        return res;
    }

    public ArrayList<FilaResultado> getCafeterasDeTiendasSeleccionadas() {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        YouTube yt = new YouTube();
        yt.eliminarDuplicados();
        return res;
        /*ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();

        Thread fnacThread = new Thread() {
            public void run() {
                if (fnacCheckBox.isSelected()) {
                    res.addAll(getCafeterasDeTienda(new Fnac()));
                }
            }

        };
        fnacThread.start();
        Thread amazonThread = new Thread() {
            public void run() {
                if (amazonCheckBox.isSelected()) {
                    res.addAll(getCafeterasDeTienda(new Amazon()));

                }
            }

        };
        amazonThread.start();
        Thread corteInglesThread = new Thread() {
            public void run() {
                if (elCorteInglesCheckBox.isSelected()) {
                    res.addAll(getCafeterasDeTienda(new CorteIngles()));
                }
            }
        };
        corteInglesThread.start();
        Thread mediaMarktThread = new Thread() {
            public void run() {
                if (mediaMarktCheckBox.isSelected()) {
                    res.addAll(getCafeterasDeTienda(new MediaMarkt()));
                }
            }
        };
        mediaMarktThread.start();
        try {
            mediaMarktThread.join();
            fnacThread.join();
            corteInglesThread.join();
            amazonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }*/
    }
}