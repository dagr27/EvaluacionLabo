/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JFrame;
import dao.filtroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.filtro;
/**
 *
 * @author LN710Q
 */
public class consulta extends JFrame {
    public JLabel lblCodigo, lblDispo, lblPrecio, lblCantidad, lblTipo, lblNombre;

    public JTextField codigo, precio,nombre,cantidad;
    public JComboBox tipo;

    ButtonGroup dispo = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public consulta() {
        super("Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblPrecio);
        container.add(lblNombre);
        container.add(lblCantidad);
        container.add(lblTipo);
        container.add(lblDispo);
        
        
        container.add(codigo);
        container.add(precio);
        container.add(nombre);
        container.add(cantidad);
        container.add(tipo);
        
        
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(1000, 800);
        eventos();

    }

    private void agregarLabels() {
        lblCodigo = new JLabel("Codigo");
        lblPrecio = new JLabel("Precio");
        lblNombre = new JLabel("Nombre");
        lblCantidad = new JLabel("Cantidad");
        lblTipo = new JLabel("Tipo");        
        lblDispo = new JLabel("Disponibilidad");
        
        
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblPrecio.setBounds(10, 60, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 100, ANCHOC, ALTOC);
        lblCantidad.setBounds(10, 140, ANCHOC, ALTOC);
        lblTipo.setBounds(10, 190, ANCHOC, ALTOC);
        lblDispo.setBounds(10, 240, ANCHOC, ALTOC);
        
        
 
    }

    private void formulario() {
        codigo = new JTextField();
        precio = new JTextField();
        nombre = new JTextField();
        cantidad = new JTextField();
        tipo = new JComboBox();
        
        
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        tipo.addItem("Frutas");
        tipo.addItem("Verduras");
        tipo.addItem("Bebida");
        tipo.addItem("Dulce");

        dispo = new ButtonGroup();
        dispo.add(si);
        dispo.add(no);
        //-------------------------------------------
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        precio.setBounds(140, 60, ANCHOC, ALTOC);
        nombre.setBounds(140, 100, ANCHOC, ALTOC);
        cantidad.setBounds(140, 140, ANCHOC, ALTOC);
        
        tipo.setBounds(140, 190, ANCHOC, ALTOC);
        
        si.setBounds(140, 240, 50, ALTOC);
        no.setBounds(210, 240, 50, ALTOC);

        buscar.setBounds(300, 10, 100, ALTOC);
        insertar.setBounds(450, 210, 100, ALTOC);
        actualizar.setBounds(550, 210, 100, ALTOC);
        eliminar.setBounds(650, 210, 100, ALTOC);
        limpiar.setBounds(750, 210, 100, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 300, 500, 200);
        table.add(new JScrollPane(resultados));

    }

    private void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return int.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Boolean.class;
                    case 5:
                        return double.class;
                        
                    default:
                        return int.class;
                }
            }
        };
        tm.addColumn("id");
        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Tipo");        
        tm.addColumn("Disponibilidad");
        tm.addColumn("Precio");
        tm.addColumn("Cantidad");
        filtroDao fd = new filtroDao();
        ArrayList<filtro> filtros = fd.readAll();
        //
        for (filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getId(),fi.getCodigo(), fi.getNombre(),  fi.getTipo(),fi.getDispo(),fi.getPrecio(), fi.getCantidad()});
        }

        resultados.setModel(tm);

    }
    

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtroDao fd = new filtroDao();
                //String, String,String,int, double, boolea
                filtro f = new filtro(0,nombre.getText(), codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Double.parseDouble(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de este filtro.");
                }
            }
        });
        

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtroDao fd = new filtroDao();
                filtro f = new filtro(0,nombre.getText(), codigo.getText(), tipo.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()), Double.parseDouble(precio.getText()), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de creación de este filtro.");
                }
            }
        });
        

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtroDao fd = new filtroDao();
                System.out.println(codigo.getText());
                
                if (fd.delete(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar este filtro.");
                }
            }
        });
        

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtroDao fd = new filtroDao();
                filtro f = fd.read(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El Filtro buscado no ha sido encontrado");
                } else {

                    codigo.setText(f.getCodigo());
                    precio.setText(String.valueOf(f.getPrecio()));
                    nombre.setText(f.getNombre());
                    cantidad.setText(String.valueOf(f.getCantidad()));
                    tipo.setSelectedItem(f.getTipo());

                    if (f.getDispo()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        codigo.setText("");
        nombre.setText("");
        precio.setText("");
        cantidad.setText("");
        tipo.setSelectedItem("Frutas");
        

    }

}
