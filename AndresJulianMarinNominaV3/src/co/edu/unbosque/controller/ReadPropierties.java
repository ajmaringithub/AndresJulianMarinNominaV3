package co.edu.unbosque.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ReadPropierties {

	String result = "";   //Cadena que se va llenando
	InputStream inputStream;  //Lectura de archivos
	public String Proyecto, Numero_archivos_de_salida, Máximo_número_de_registros, 
		Archivo_inferior, Archivo_medio, Archivo_superior, Archivo_por_defecto, 
		Caracter_de_separación, Titulo_de_ventana;   //Cadenas de propiedades esperadas
	
	public ReadPropierties() {
		// TODO Auto-generated constructor stub
	}

	public String getPropValues() throws IOException {
 
		try {
			Properties prop = new Properties();  //Crea objeto de propiedades
			String propFileName = "config.properties";  //Nombre del archivo de propiedades
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);  //Carga el archivo
 
			if (inputStream != null) {
				prop.load(inputStream);  //mientras el archivo tenga datos los carga 
			} else {
				//Archivo no encontrado
				throw new FileNotFoundException("Archivo de configuracion '" + propFileName + "' no encontrado en el proyecto valide ruta por favor");
			}
 
			Date time = new Date(System.currentTimeMillis());  //Toma la fecha y hora del sistema
 
			// carga el valor de la propiedad del archivo config
			Proyecto = prop.getProperty("Proyecto");
			Numero_archivos_de_salida = prop.getProperty("Numero_archivos_de_salida");
			Máximo_número_de_registros = prop.getProperty("Máximo_número_de_registros");
			Archivo_inferior = prop.getProperty("Archivo_inferior");
			Archivo_medio = prop.getProperty("Archivo_medio");
			Archivo_superior = prop.getProperty("Archivo_superior");
			Archivo_por_defecto = prop.getProperty("Archivo_por_defecto");
			Caracter_de_separación = prop.getProperty("Caracter_de_separación");
			Titulo_de_ventana = prop.getProperty("Titulo_de_ventana");
			//Salida de prueba
			System.out.println(Proyecto + "\nPrograma ejecutado el " + time + " para salidas =" + Numero_archivos_de_salida);
		} catch (Exception e) {
			System.out.println("Exception: " + e);  //Devuelve algún error
		} finally {
			inputStream.close();  //Cierra el archivo de propiedades
		}
		return result;  //devuelve el resultado
	}
}