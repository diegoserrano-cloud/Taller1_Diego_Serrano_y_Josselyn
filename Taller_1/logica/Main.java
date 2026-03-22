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
		File Tusuarios = new File("C:\\Users\\Alumno\\eclipse-workspace\\Taller_1\\usuarios");
		
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
				++i;}
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
			if (opcion.hasNextInt()) {
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
            	String nombre = opcion.nextLine();// segun el nombre que nos den usamos la contraseña
            	Boolean condicion = false;
            	if (nombre.equalsIgnoreCase("Martin")) {
            		System.out.print("Contraseña: ");
            		String contraseña_Martin = opcion.nextLine();
					if (contraseña_Martin.equals(contraseñas[0])) {
            			condicion = true;
            			System.out.println();
					}
            	}else if (nombre.equalsIgnoreCase("Catalina")) {
            		System.out.print("Contraseña: ");
            		String contraseña_Catalina = opcion.nextLine(); 
					if (contraseña_Catalina.equals(contraseñas[1])) {
            			condicion = true;
            			System.out.println();
					}
            	}else if (nombre.equalsIgnoreCase("Estefania")) {
            		System.out.print("Contraseña: ");
            		String contraseña_Estefania = opcion.nextLine(); 
					if (contraseña_Estefania.equals(contraseñas[2])) {
            			condicion = true;
            			System.out.println();
					}	
            	}else {
            		System.out.println("Usuario invalido");
            		System.out.println();
            	}if(condicion) {
					int opcion_usuario;
            		do {
					opcion.nextLine();
            		System.out.println("Acceso correcto!");
            		System.out.println();
            		System.out.println("Bienvenido"+ " " + nombre + "!");
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
            		}while(opcion_usuario != 5);
				}
			}
		} while (op != 3);
			
		opcion.close();
		
	}
}
