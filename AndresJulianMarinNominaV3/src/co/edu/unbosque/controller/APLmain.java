package co.edu.unbosque.controller;

import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.Model;
import co.edu.unbosque.model.persistence.Persistence;
import co.edu.unbosque.view.View;

public class APLmain {



	public APLmain() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View view = new View();   //Interfaz con el usuario formulario
		Model model = new Model();
		ReadPropierties properties = new ReadPropierties();  //Constructor de propiedades
				
		try {
			properties.getPropValues();  //Carga las propiedades del archivo
			System.out.println(properties.Archivo_por_defecto);  //Muestra la ruta leída
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Controller ctrl = new Controller(view, model, properties);  //Comunicacion entre vista y modelo
		ctrl.iniciar();  //Lanza el formulario controlado
		view.setVisible(true);  //Muestra el formulario
		
		//Leer archivo de la ruta /data/datosprincipales.csv
		try {
			File archivo = new File(properties.Archivo_por_defecto); // "/data/datosprincipales.csv");
			if (archivo.exists()) {
				view.txtArchivo.setText(archivo.getPath());
				Persistence per = new Persistence(archivo, model, properties);
				DefaultTableModel dtm = (DefaultTableModel) view.tblArchivo.getModel();
				dtm.setRowCount(0);  //Borra los datos de la tabla
				int m = Integer.parseInt(properties.Máximo_número_de_registros);
				if (m > model.n) m = model.n;  //Si hay menos registros que el permitido
				for (int i=0; i<m; i++) {
					dtm.addRow(new Object[]{model.Cedulas[i], model.Nombres[i], model.Salarios[i]});
				}
				per.GenerarNomina(archivo, model, properties);
			} else {
				view.txtArchivo.setText(archivo.getPath());
				System.out.print("Archivo no encontrado!");
				DefaultTableModel dtm = (DefaultTableModel) view.tblArchivo.getModel();
				dtm.setRowCount(0);  //Borra los datos de la tabla
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
