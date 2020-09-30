package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.Model;
import co.edu.unbosque.model.persistence.Persistence;
import co.edu.unbosque.view.View;

public class Controller implements ActionListener {

	private View view;
	private Model model;
	private ReadPropierties prop;
	private File archivo;  //Archivo separado por comas
	
	public Controller(View view, Model model, ReadPropierties prop) {
		this.view = view;
		this.view.cmdArchivo.addActionListener(this);  //Boton del formulario recibe comandos
		this.model = model;
		this.prop = prop;
	}

	public void iniciar() {
		
		view.setTitle(prop.Titulo_de_ventana);  //Titulo del archivo de propiedades
		view.txtArchivo.setText("");
		view.setLocationRelativeTo(null);     // Centrar la ventana
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {  //Genera un cuadro de dialogo de selección de archivo
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());  //Cuadro de dialogo nativo
                } catch(Exception e) {
                    e.printStackTrace();
                }
                JFileChooser jfc = new JFileChooser();  //Lanza cuadro para elegir archivo
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo de nomina sin procesar de estudiantes separado por comas (*.csv)", "csv"));
                jfc.showOpenDialog(null);
                archivo = jfc.getSelectedFile();
                if (archivo != null) {
	                System.out.println(archivo.getAbsolutePath());  //Ruta del archivo
	                view.txtArchivo.setText(archivo.getAbsolutePath());
	                Persistence per = new Persistence(archivo, model, prop);
	                DefaultTableModel dtm = (DefaultTableModel) view.tblArchivo.getModel();
	                dtm.setRowCount(0);  //Borra los datos de la tabla
	                for (int i=0; i<model.n; i++) {
	                	dtm.addRow(new Object[]{model.Cedulas[i], model.Nombres[i], model.Salarios[i]});
	                }
	                per.GenerarNomina(archivo, model, prop);
                }else {
                	System.out.println("Ningún archivo de nomina de estudiantes seleccionado");  //Ruta del archivo
	                view.txtArchivo.setText("");

	                DefaultTableModel dtm = (DefaultTableModel) view.tblArchivo.getModel();
	                dtm.setRowCount(0);  //Borra los datos de la tabla
                }
            }
        });
	}
}
