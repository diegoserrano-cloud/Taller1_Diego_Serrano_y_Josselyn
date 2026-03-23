/*
 * Nombres: Diego Serrano Fuentes, Josselyn Barraza Yáñez
 * Rut(s): 22.105.561-6 | 22.246.539-7
 * Carrera: ICCI
 * Taller: Taller N° 1 POO
 */

package logica;

import java.util.Scanner;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
public class Main {
	public static void main(String[] args) throws IOException  {
		File Tusuarios = new File("Taller_1/Usuarios.txt");
		File Tregistros = new File("Taller_1/Registros.txt");
		Scanner lector = new Scanner(Tusuarios);// lee las lineas
		String[] nombres= {"", "", ""};
		String[] contraseñas= {"", "", ""};
		int i = 0;
		while (lector.hasNextLine()) { // lee el archivo
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			String nombrer= partes[0];
			nombres[i] = nombrer;
			String contraseña = partes[1];
			contraseñas[i] = contraseña;
			if (i < 3) {
				i++;}
		}
		int a;
		for (a = 0; a< nombres.length; a++){ // esto solo es para guiarnos con las contraseñas despues se eliminara
			System.out.print(nombres[a]+ " ");
			System.out.print(contraseñas[a]+ " ");
			System.out.println();
		}

		Scanner opcion = new Scanner(System.in);
		
		int op;
		
		/*
		 * Control de error:
		 *	 primero realizamos nuestro menú de opciones
		 *	 en este caso usamos un do-while para que cada vez que el usuario ponga un valor distinto a 3  
		 * 	 el programa vuelva al inicio
		 */
		
		do {
			
			System.out.println("1) Menú de usuarios " //Preferencia personal para separar el menú de forma más legible
					+ "\n2) Menú de Analisis" 
					+ "\n3) Salir"
					
					);
			if (opcion.hasNextInt()) { //Comprueba si es entero
                op = opcion.nextInt(); 
            } else {
                System.out.println("Ingrese un número válido"); //si es carácter arrojamos el mensaje
                opcion.next(); // Reiniciamos la variable 
                op = 0; // Valor por defecto
            }	
			
			//Aquí vamos a hacer un Switch case (para los números del menú)
			switch(op) {
			case 1: 
            	opcion.nextLine();
            	System.out.print("Usuario: ");
            	String nombre = opcion.nextLine(); // segun el nombre que nos den usamos la contraseña
            	boolean condicion = false;

            	System.out.print("Contraseña: ");
            	String contraseña = opcion.nextLine();
            	
            	int indexUsuario = -1; //Guardamos la posición para cambiar registros en el futuro

            	for (int j = 0; j < nombres.length; j++) {
            	    if (nombre.equalsIgnoreCase(nombres[j]) && contraseña.equals(contraseñas[j])) {
            	        condicion = true;
            	        indexUsuario = j;
            	        break;
            	    }
            	}

            	if (!condicion) { 
            	    System.out.println("Usuario o contraseña incorrectos\n");
            	}
            	
            	if(condicion) {
					int opcion_usuario;
					System.out.println("Acceso correcto!");
            		do { // este do while nos sirve para que el usuario indique cuando quiere salir 
            		
            		System.out.println();
            		System.out.println("Bienvenido " + nombre + "!");
            		System.out.println();
            		System.out.println("Que deseas realizar?");
            		System.out.println();
            		System.out.println("1) Registrar actividad. " 
        					+ "\n2) Modificar actividad." 
        					+ "\n3) Eliminar actividad."
        					+ "\n4) Cambiar contraseña."
        					+ "\n5) Salir.");
            		System.out.println();
            		
            		opcion_usuario = opcion.nextInt();
            		
            		switch (opcion_usuario) {
            		
            		case 1:
            			registrarActividad(nombre, opcion);
            			break;
            		
            		case 2:
            			modificarActividad(nombre, opcion);
            		}
            			
            		
            		} while(opcion_usuario != 5);
				}
			//case 2:
				//Aquí va el menú de analisis
			}
		} while (op != 3);
			
		opcion.close();
		
	}
	
	public static void registrarActividad(String nombre, Scanner sc) { //Scanner sc es para reutilizarlo (porqué ya existe)
		try {
			sc.nextLine(); //Limpia
			
			System.out.print("Ingrese fecha (dd/mm/yyyy): ");
		    String fecha;
		    
		    do {
		    	System.out.print("Ingrese fecha (dd/mm/yyyy): ");
	            fecha = sc.nextLine();
		    	
	            /*
	             * En resumen la función ".matches()" compara los valores entre un rango y un formato, en este caso
	             * el "0[1-9]" toma el 0 y al lado de él acepta cualquier valor que esté entre 0 y 9, pero si es diferente el programa 
	             * lo reinicia y el "|" es parecido al 'and' del python (espero se haya entendido la explicación)
	             */
	            
	            if (!fecha.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}")) { //Aprobado por el ayudante
	                System.out.println("Fecha no válida. Use dd/mm/yyyy (Día 01-31, Mes 01-12)");
	            }
	            
	            } while(!fecha.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}"));

	            
		    
		    
		    System.out.print("Ingrese horas: ");
		    int horas = sc.nextInt();
		    sc.nextLine(); // limpiar buffer

		    System.out.print("Ingrese actividad: ");
		    String actividad = sc.nextLine();
		    
		    //Acá va el "BuffererWritter" para modificar el archivo
			BufferedWriter bw = new BufferedWriter(new FileWriter("Taller_1/Registros.txt", true));
		    bw.newLine();
		    bw.write(nombre+";"+fecha+";"+horas+";"+actividad);
		    bw.close();
		    
			
		} catch (IOException e) {
			System.out.println("Error al ingresar los datos");
		}
		
	}
	
	public static void modificarActividad(String nombre, Scanner sc) {
		
		//Arreglos de máximo 300 datos (read.me)
		String[] usuarios = new String[300]; 
        String[] fechas = new String[300];
        int[] horas = new int[300];
        String[] actividades = new String[300];

        int i = 0; //El objetivo es abrir el archivo de nuevo y hacer cambios ahí dentro
	}
}
