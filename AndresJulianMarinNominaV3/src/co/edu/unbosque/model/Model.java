package co.edu.unbosque.model;

public class Model {

	public String Cedulas[];
	public String Nombres[];
	public double Salarios[];
	public int n;  //Cantidad de elementos
	
	public Model() {
		// TODO Auto-generated constructor stub
		n = 0;  //Tamaño de los arreglos
		Cedulas = new String[1];  //Arreglos inicialmente con 1 posición
		Nombres = new String[1];
		Salarios = new double[1];
	}
	
	public void AgregarFila( String cedula, String nombre, double salario ) {
		String Cedulas2[] = new String[this.n + 1];  //Crea arreglos con 1 posición adicional
		String Nombres2[] = new String[this.n + 1];
		double Salarios2[] = new double[this.n + 1];
		
		int i;
		for (i=0; i<n; i++) {
			Cedulas2[i] = Cedulas[i];  //Copia el contenido del arreglo inicial
			Nombres2[i] = Nombres[i];
			Salarios2[i] = Salarios[i];
		}
		Cedulas2[i] = cedula;  //Inserta el nuevo valor en el arreglo redimensionado
		Nombres2[i] = nombre;
		Salarios2[i] = salario;
		Cedulas = Cedulas2;  //Los arreglos originales toman los nuevos valores
		Nombres = Nombres2;
		Salarios = Salarios2;
		n = n+1;  //Aumenta el tamaño del arreglo
		
	}
	
	public double ProcesarNomina(double salario) {
		//Calcula el valor de la retención según el salario recibido
		if (salario <= 2000) return 0.1;  //Devuelve 10%
		if (salario > 5000) return 0.2;   //Devuelve 20%
		return 0.15;  //En otros casos devuelve 15%
	}
}
