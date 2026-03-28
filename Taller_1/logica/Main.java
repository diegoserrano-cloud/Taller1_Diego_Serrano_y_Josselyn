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
import java.io.FileNotFoundException;
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
            			break;
            		
					case 3:
            			eliminarActividad(nombre, opcion);
            			break;
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
	            
	            if (!fecha.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/20[0-2][0-9]")) { //Aprobado por el ayudante (solo funciona en los años 2000 hasta 2029)
	                System.out.println("Fecha no válida. Use dd/mm/yyyy (Día 01-31, Mes 01-12)");
	            }
	            
	            } while(!fecha.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/20[0-2][0-9]"));

	            
		    
		    
		    System.out.print("Ingrese horas: ");
		    int horas = sc.nextInt();
		    sc.nextLine(); // limpiar buffer
		    
		    //Falta control de error por acá pero lo hago despuésD

		    System.out.print("Ingrese actividad: ");
		    String actividad = sc.nextLine();
		    
		    //Acá va el "BuffererWritter" para modificar el archivo
			BufferedWriter bw = new BufferedWriter(new FileWriter("Taller_1/Registros.txt", true)); //true mantiene el archivo completo
		    bw.newLine();
		    bw.write(nombre+";"+fecha+";"+horas+";"+actividad);
		    bw.close();
		    
			
		} catch (IOException e) {
			System.out.println("Error al ingresar los datos");
		}
		
	}
	
	public static void modificarActividad(String nombre, Scanner sc) {
		
		//Arreglos de máximo 300 datos (read.me)
	
        //Dado que el archivo puede no existir usamos try and catch
        try {
        	File Tregistros = new File("Taller_1/Registros.txt");
            Scanner lector = new Scanner(Tregistros);

            String[] usuarios = new String[300];
            String[] fechas = new String[300];
            String[] horas = new String[300];
            String[] actividades = new String[300];

            int i = 0;

            //Lectura archivo
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");

                usuarios[i] = partes[0];
                fechas[i] = partes[1];
                horas[i] = partes[2];
                actividades[i] = partes[3];

                i++;
            }
            
            lector.close();
	
            int[] indices = new int[300];
            int contador = 1;

            System.out.println("\n¿Cual actividad deseas modificar?");
            System.out.println("0) Regresar");
			
			for (int j = 0; j < i; j++) { 
			    if (usuarios[j].equalsIgnoreCase(nombre)) {
			        System.out.println(contador + ") " +
			                usuarios[j] + ";" + fechas[j] + ";" + horas[j] + ";" + actividades[j]);
	
			        indices[contador] = j;
			        contador++;
			    }
			}
			
			int op = sc.nextInt(); //op = linea a modificar (usaremos el indice)
	        sc.nextLine(); //Limpiamos
	        if (op == 0) {
	        	return;
	        }

			System.out.println("Que deseas modificar?");
			System.out.println("0) Regresar."
					+ "\n1) Fecha"
					+ "\n2) Duracion"
					+ "\n3) Tipo de actividad");
			
			int op_2= sc.nextInt(); //op_2 lo que quiere modificar (usaremos el indice)
	        sc.nextLine(); //Limpiamos

			BufferedWriter bw = new BufferedWriter(new FileWriter("registros"));//volvemos abrir el archivo para hacer los cambios
			
			System.out.println("0) Regresar.");
	        switch(op_2){
	        	case 0: 
	        		return;
	        	case 1:
	        		String fecha_Nueva;    
	    		    do {
	    		    	System.out.print("Ingrese fecha (dd/mm/yyyy): ");
	    	            fecha_Nueva= sc.nextLine();
	    	            
	    	            if (!fecha_Nueva.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}")) { //Aprobado por el ayudante
	    	                System.out.println("Fecha no válida. Use dd/mm/yyyy (Día 01-31, Mes 01-12)");
	    	            }
	    	            
	    	            } while(!fecha_Nueva.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}"));
	    		    int indice = indices[op];//usamos los indices guardados previamente
	    		    fechas[indice]= fecha_Nueva;//cambiamos las fechas en el lugar que corresponde
	    		    
	    		    int x;
	    		    for (x = 0; x<i ; x++) { //agregamos la informacion en el archivo
		    		    bw.write(usuarios[x] + ";" + fechas[x] + ";" + horas[x] + ";" + actividades[x]);
		    		    bw.newLine();
	    		    }
	    		    
	    		    System.out.println("Fecha modificada con exito!");
	    		    bw.close();
	    		    break;
	    		    
	        	case 2:
	        		System.out.print("Ingrese duracion: ");
	        		String duracion_Nueva= sc.nextLine();
	        		
	    		    int indice2 = indices[op];//usamos los indices guardados previamente
	    		    horas[indice2]= duracion_Nueva;//cambiamos las horas en el lugar que corresponde
	    		    
	    		    int k;
	    		    for (k= 0; k<i ; k++) { //agregamos la informacion en el archivo
		    		    bw.write(usuarios[k] + ";" + fechas[k] + ";" + horas[k] + ";" + actividades[k]);
		    		    bw.newLine();
	    		    }
	        		System.out.println("Duración modificada con exito!");
	        		bw.close();
	        		break;
	        		
	        	case 3:
	        		System.out.print("Ingrese nuevo tipo de actividad: ");
	        		String actividad_Nueva= sc.nextLine();
	        		
	    		    int indice3 = indices[op];//usamos los indices guardados previamente
	    		    actividades[indice3]= actividad_Nueva;//cambiamos las horas en el lugar que corresponde
	    		    
	    		    int v;
	    		    for (v= 0; v<i ; v++) { //agregamos la informacion en el archivo
		    		    bw.write(usuarios[v] + ";" + fechas[v] + ";" + horas[v] + ";" + actividades[v]);
		    		    bw.newLine();
	    		    }
	        		System.out.println("Actividad modificada con exito!");
	        		bw.close();
	        		break;
	        }
	        int l;
	        int cont = 1;
			for ( l = 0; l < i; l++) {
			        System.out.println(cont + ") " +
			                usuarios[l] + ";" + fechas[l] + ";" + horas[l] + ";" + actividades[l]); //es para ver si se modifico correctamente
			        cont++;
			    
			}
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
        }
    }
	/*
	public static void eliminarActividad(String nombre, Scanner sc) {
        try {
        	File Tregistros = new File("registros");
            Scanner lector = new Scanner(Tregistros);

            String[] usuarios = new String[300];
            String[] fechas = new String[300];
            String[] horas = new String[300];
            String[] actividades = new String[300];

            int i = 0;

            //Lectura archivo
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");

                usuarios[i] = partes[0];
                fechas[i] = partes[1];
                horas[i] = partes[2];
                actividades[i] = partes[3];

                i++;
            }
            
            lector.close();
          
            int[] indices = new int[300];
            int contador = 1;

            System.out.println("\n¿Cual actividad deseas eliminar?");
            System.out.println("0) Regresar");
			
			for (int j = 0; j < i; j++) { 
			    if (usuarios[j].equalsIgnoreCase(nombre)) {
			        System.out.println(contador + ") " +
			                usuarios[j] + ";" + fechas[j] + ";" + horas[j] + ";" + actividades[j]);
	
			        indices[contador] = j;
			        contador++;
			    }
			}
			
			int op = sc.nextInt(); //op = linea a modificar (usaremos el indice), nos falta control de error
	        sc.nextLine();
    	}catch (FileNotFoundException e) {
	            System.out.println("No se encontró el archivo");
        } catch (IOException e) {
	            System.out.println("Error al escribir en el archivo");
	       }
	       */
		private static void eliminarActividad(String nombre, Scanner sc) {
	        try {
	        	File Tregistros = new File("registros");
	            Scanner lector = new Scanner(Tregistros);
	
	            String[] usuarios = new String[300];
	            String[] fechas = new String[300];
	            String[] horas = new String[300];
	            String[] actividades = new String[300];
	
	            int i = 0;
	
	            //Lectura archivo
	            while (lector.hasNextLine()) {
	                String linea = lector.nextLine();
	                String[] partes = linea.split(";");
	
	                usuarios[i] = partes[0];
	                fechas[i] = partes[1];
	                horas[i] = partes[2];
	                actividades[i] = partes[3];
	
	                i++;
	            }
	            
	            lector.close();
	          
	            int[] indices = new int[300];
	            int contador = 1;
	
	            System.out.println("\n¿Cual actividad deseas eliminar?");
	            System.out.println("0) Regresar");
				
				for (int j = 0; j < i; j++) { 
				    if (usuarios[j].equalsIgnoreCase(nombre)) {
				        System.out.println(contador + ") " +
				                usuarios[j] + ";" + fechas[j] + ";" + horas[j] + ";" + actividades[j]);
		
				        indices[contador] = j;
				        contador++;
				    }
				}
				
				int op = sc.nextInt(); //op = linea a modificar (usaremos el indice), nos falta control de error
		        sc.nextLine();
	    	}catch (FileNotFoundException e) {
		            System.out.println("No se encontró el archivo");
	        } catch (IOException e) {
		            System.out.println("Error al escribir en el archivo");
		       }
		
        
	}	
}
