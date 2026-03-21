/*
 * Nombres: Diego Serrano Fuentes, Josselyn Barraza Yáñez
 * Rut(s): 22.105.561-6 | 22.246.539-7
 * Carrera: ICCI
 * Taller: Taller N° 1 POO
 */

package logica;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		
		
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
            	
            	if (nombre.equalsIgnoreCase("Martin")) {
            		System.out.print("Contraseña: ");
            		String contraseña_Martin = opcion.nextLine(); 
            		
            	}else if (nombre.equalsIgnoreCase("Catalina")) {
            		System.out.print("Contraseña: ");
            		String contraseña_Catalina = opcion.nextLine(); 
            		
            	}else if (nombre.equalsIgnoreCase("Estefania")) {
            		System.out.print("Contraseña: ");
            		String contraseña_Estefania = opcion.nextLine(); 
            	}else {
            		System.out.println("Usuario invalido");
            		System.out.println();
            	}	
			}
		} while (op != 3);
			
		opcion.close();
		
	}
}
