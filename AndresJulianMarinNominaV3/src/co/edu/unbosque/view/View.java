package co.edu.unbosque.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {   // Se crea el formulario
	private static final long serialVersionUID = 1L;  //Asignar serial a la clase
	public JLabel lblArchivo;
	public JTextField txtArchivo;   //Nombre del archivo seleccionado
	public JButton cmdArchivo;    //Botón para elegir archivo
	public JTable tblArchivo;    //Tabla para mostrar los datos
	public DefaultTableModel tmodel;
	
	JPanel panel;      //Panel para organizar los objetos
	
	public View () {
		lblArchivo = new JLabel("Archivo:");
		lblArchivo.setBounds(20, 20, 60, 20);  //Ubicacion
		
		txtArchivo = new JTextField();  // Numero de referencia uno
		txtArchivo.setBounds(80, 20, 200, 20);  //Ubicacion
		txtArchivo.setEditable(false);
		
		cmdArchivo = new JButton("Cargar...");
		cmdArchivo.setBounds(300, 20, 20, 20);
		
        String[] columnas = { "Documento", "Nombre", "Salario" }; //Titulos columna
        String[][] datos = { 
        		  { "3012300100", "Andres Julian Marin", "1010000" },
                  { "10303002", "Andres Julian Marin", "2002000" },
                  { "1030123003", "Andres Julian Marin", "3040000" },
                  { "10323004", "Andres Julian Marin", "4000500" },
                  { "1030123005", "Andres Julian Marin", "5050000" },
                  { "10303006", "Andres Julian Marin", "6000500" },
                  { "1036623007", "Andres Julian Marin", "7050000" },
                  { "10123008", "Andres Julian Marin", "8000300" },
                  { "1030155009", "Andres Julian Marin", "9040000" },
                  { "10301010", "Andres Julian Marin", "1000400" },
                  { "1038823011", "Andres Julian Marin", "1140000" },
                  { "10323012", "Andres Julian Marin", "1200500" },
                  { "1038823013", "Andres Julian Marin", "1380000" },
                  { "10123014", "Andres Julian Marin", "1400800" },
                  { "1030743015", "Andres Julian Marin", "1570000" } 
        }; 
        tmodel = new DefaultTableModel(datos, columnas);  //Carga el modelo con datos de prueba
  		tblArchivo = new JTable(tmodel);
  		tblArchivo.getColumnModel().getColumn(1).setPreferredWidth(200);
  		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
  		centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
  		tblArchivo.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
  		
		
		panel = new JPanel();   //"Modelo - Vista - Controlador"
		panel.setLayout(null);   //Centra la ventana 
		
		panel.add(lblArchivo);   //Agrega los elementos al panel
		panel.add(txtArchivo);
		panel.add(cmdArchivo);
		// JScrollPane 
        JScrollPane sp = new JScrollPane(tblArchivo);
        sp.setBounds(20, 60, 400, 200);
		panel.add(sp);
		add(panel);  //Inserta el panel en el formulario
		setSize(450, 320);  //Tamaño del formulario

	}

}
