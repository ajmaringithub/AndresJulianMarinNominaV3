package co.edu.unbosque.model.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import co.edu.unbosque.controller.ReadPropierties;
import co.edu.unbosque.model.Model;

public class Persistence {

	public Persistence(File archivo, Model model, ReadPropierties prop) {  //Metodos para leer el archivo
		// TODO Auto-generated constructor stub
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(archivo));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);  //Lee linea por linea y las muestra
				String[] parts = line.split("\\" + prop.Caracter_de_separación);   //;"); // Separar por ";"
				if (parts.length==3) {
					try {
						double s = Double.parseDouble(parts[2]);  //Intenta convertir en decimal
						model.AgregarFila(parts[0], parts[1], s);  //Crea una fila en los arreglos
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
						System.out.println("Salario no es número");  //No entendió los datos
					}
				} else {
					System.out.println("Línea no se pudo separar");  //No tenía 3 datos
				}
				line = reader.readLine();
			}
			reader.close();  //Cierra el archivo
		} catch (IOException e) {
			e.printStackTrace();  //No pudo leer el archivo
		} catch (NumberFormatException e) {
			e.printStackTrace();  //No es un número
		}
		
	}

	public void GenerarNomina(File archivo, Model model, ReadPropierties prop) {  //Metodo para generar los archivos

		
		try {
			//Abre el archivo con agrego FALSE, es decir borra lo anterior 
			FileWriter nom1 = new FileWriter(new File(archivo.getParent() + "//" + prop.Archivo_inferior), false);// Nomina1.txt"), false);
			FileWriter nom2 = new FileWriter(new File(archivo.getParent() + "//" + prop.Archivo_medio), false);// Nomina2.txt"), false);
			FileWriter nom3 = new FileWriter(new File(archivo.getParent() + "//" + prop.Archivo_superior), false);// Nomina3.txt"), false);
			nom1.write("DOCUMENTO\tSUELDO NETO\tSALARIO\tRETENCION (10%)");
			nom1.write(System.getProperty("line.separator"));  //Salto de linea
			nom2.write("DOCUMENTO\tSUELDO NETO\tSALARIO\tRETENCION (15%)");
			nom2.write(System.getProperty("line.separator"));  //Salto de linea
			nom3.write("DOCUMENTO\tSUELDO NETO\tSALARIO\tRETENCION (20%)");
			nom3.write(System.getProperty("line.separator"));  //Salto de linea
			
			//Recorre las filas generando las retenciones correspondientes
			for (int i=0; i<model.n; i++) {
				int d = (int) (model.ProcesarNomina(model.Salarios[i])*100);  //Descuento
				switch (d) {
				case 10:  //Descuento 10%
					nom1.write(model.Cedulas[i] + "\t" + Double.toString(model.Salarios[i]*0.9) + "\t" + "\t" + Double.toString(model.Salarios[i]) + "\t" + Double.toString(model.Salarios[i]*0.1));
					nom1.write(System.getProperty("line.separator"));  //Salto de linea
					break;
				case 15:
					nom2.write(model.Cedulas[i] + "\t" + Double.toString(model.Salarios[i]*0.85) + "\t" + "\t" + Double.toString(model.Salarios[i]) + "\t" + Double.toString(model.Salarios[i]*0.15) + "\n");
					nom2.write(System.getProperty("line.separator"));  //Salto de linea
					break;
				case 20:
					nom3.write(model.Cedulas[i] + "\t" + Double.toString(model.Salarios[i]*0.8) + "\t" + "\t" + Double.toString(model.Salarios[i]) + "\t" + Double.toString(model.Salarios[i]*0.2) + "\n");
					nom3.write(System.getProperty("line.separator"));  //Salto de linea
					break;
				}
			}
			nom1.close();  //Cierra los archivos de texto
			nom2.close();
			nom3.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
