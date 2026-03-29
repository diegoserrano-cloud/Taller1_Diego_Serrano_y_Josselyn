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

import java.io.*; //Importa todo o algo así (consejo de la ayudantía) 

public class Main {
	public static void main(String[] args) throws IOException {
	    File Tusuarios = new File("Taller_1/Usuarios.txt");
	    File Tregistros = new File("Taller_1/Registros.txt");
	    Scanner lector = new Scanner(Tusuarios); // lee las lineas
	    
	    String[] nombres = {"", "", ""};
	    String[] contraseñas = {"", "", ""};
	    int i = 0;

	    while (lector.hasNextLine()) { // lee el archivo de Usuarios.txt
	        String linea = lector.nextLine();
	        String[] partes = linea.split(";");
	        nombres[i] = partes[0];
	        contraseñas[i] = partes[1];
	        if (i < nombres.length) { // Ajustado a 2 para no desbordar el array de tamaño 3 (osea el tamaño de los nombres)
	            i++;
	        }
	    }

	    int a;
	    for (a = 0; a < nombres.length; a++) { // esto solo es para guiarnos con las contraseñas despues se eliminara
	        System.out.print(nombres[a] + " ");
	        System.out.print(contraseñas[a] + " ");
	        System.out.println();
	    }

	    Scanner opcion = new Scanner(System.in);
	    int op = 0;

	    /*
	    * Control de error:
	    * primero realizamos nuestro menú de opciones
	    * en este caso usaremos un do-while para que cada vez que el usuario ponga un valor distinto a 3
	    * el programa vuelva al inicio
	    */
	    
	    do {
	        System.out.println("1) Menú de usuarios " 
	                + "\n2) Menú de Analisis" 
	                + "\n3) Salir\n");

	        if (opcion.hasNextInt()) {
	            op = opcion.nextInt();
	        } else {
	            System.out.println("Ingrese un número válido");
	            opcion.next(); 
	            op = 0; 
	        }

	        switch (op) {
	            case 1:
	                opcion.nextLine(); // Limpiar buffer
	                System.out.print("Usuario: ");
	                String nombre = opcion.nextLine();
	                
	                System.out.print("Contraseña: ");
	                String contraseña = opcion.nextLine();
	                
	                boolean condicion = false;
	                int indexUsuario = -1; 

	                for (int j = 0; j < nombres.length; j++) {
	                    if (nombre.equalsIgnoreCase(nombres[j]) && contraseña.equals(contraseñas[j])) {
	                        condicion = true;
	                        indexUsuario = j;
	                        break;
	                    }
	                }

	                if (!condicion) {
	                    System.out.println("Usuario o contraseña incorrectos\n");
	                } else {
	                    System.out.println("\nAcceso correcto!");
	                    int opcion_usuario;
	                    do {
	                        System.out.println("\nBienvenido " + nombre + "!");
	                        System.out.println("Que deseas realizar?\n");
	                        System.out.println("1) Registrar actividad." 
	                                + "\n2) Modificar actividad." 
	                                + "\n3) Eliminar actividad."
	                                + "\n4) Cambiar contraseña."
	                                + "\n5) Salir.");
	                        
	                        opcion_usuario = opcion.nextInt();

	                        switch (opcion_usuario) {
	                            case 1: registrarActividad(nombre, opcion); 
	                            	break;
	                            case 2: modificarActividad(nombre, opcion);
	                            	break;
	                            case 3: eliminarActividad(nombre, opcion); 
	                            	break;
	                            case 4: cambiarContraseña(nombre, opcion, nombres, contraseñas, indexUsuario, i); 
	                            	break;
	                        }
	                    } while (opcion_usuario != 5);
	                }
	                break; // Cierre case 1

	            case 2:
	            	//Definimos dimensión
	                String[] usuarios = new String[300];
	                String[] fechas = new String[300];
	                String[] horas = new String[300];
	                String[] actividades = new String[300];
	                int c = 0;
	                
	                //Lectura de archivo
	                Scanner lector2 = new Scanner(Tregistros);
	                while (lector2.hasNextLine()) {
	                    String linea = lector2.nextLine(); 
	                    String[] partes2 = linea.split(";");
	                    usuarios[c] = partes2[0];
	                    fechas[c] = partes2[1];
	                    horas[c] = partes2[2];
	                    actividades[c] = partes2[3];
	                    c++;
	                }
	                lector2.close(); //Cierre del lector

	                
	                int opcionMenu; //Entrar al ciclo
	                do {
	                	System.out.println("Bienvenido al menu de analisis!\n"
	                			+ "\nQué deseas realizar?\n");
	                	System.out.println("1) Actividad más realizada"
	                			+ "\n2) Actividad más realizada por cada usuario"
	                			+ "\n3) Usuario con mayor procastinacion"
	                			+ "\n4) Ver todas las actividades"
	                			+ "\n5) Salir");
	                	opcionMenu = opcion.nextInt();
	                	
		                switch (opcionMenu) {
		                    case 1: 
		                    	//En proceso
		                    	
		                    	/*
		                    	 * Comentario: creo que está logica no funciona para todos los casos
		                    	 * así que después voy a crear una parecida pero con un arreglo de 300 datos
		                    	 * cosa de que cada vez que lo encuentré se crea una lista paralela que guarda las horas y al final comparo
		                    	 */
		                        String actividadMayor = "";
		                        int horasMayor = 0;
		                        for (int k = 0; k < i; k++) {
		                            int suma = 0;

		                            for (int b = 0; b < i; b++) {
		                                if (actividades[k].equalsIgnoreCase(actividades[b])) {
		                                    suma += Integer.parseInt(horas[b]);
		                                }
		                            }

		                            if (suma > horasMayor) {
		                                horasMayor = suma;
		                                actividadMayor = actividades[a];
		                            }
		                        }

		                        System.out.println("Actividad más realizada: " + actividadMayor + " con " + horasMayor + " horas");
		                        break;
		                    case 2:
		                    	
		                    	System.out.println("Actividades mas realizadas por cada usuario: ");
		                    	System.out.println();
								//Las listas que crearemos serán para acumular cada actividad sin repetir en su respectivo usuarios
		                    	String[] martin_Actividades= new String[300];
		                    	int[] horas_Martin = new int[300];//acumula las horas sin repetir
		                    	int cont_M = 0;// nos serviran para que cada vez que se agrege una actividad nueva se vaya contando
		                    	
		                    	String[] catalina_Actividad= new String[300];
		                    	int[] horas_Catalina = new int[300];	
		                    	int cont_C = 0;
		                    	
		                    	String[] estefania_Actividad= new String[300];
		                    	int[] horas_Estefania = new int[300];
		                    	int cont_E = 0;
		                    	
		                    	int z;
		                    	for(z= 0; z < c ; z++) { //recorre las listas totales
		                    		if(usuarios[z].equalsIgnoreCase("Martin")){
		                    			int idx_M = -1;
		                    			int m;
		                    			for(m=0; m<cont_M; m++) {//recorre las listas de cada usuarios 
		                    				if (martin_Actividades[m].equalsIgnoreCase(actividades[z])) {
		                    					idx_M = m;
		                    	                break;
		                    	            }
		                    			}if (idx_M != -1) {
		                    				horas_Martin[idx_M] += Integer.parseInt(horas[z]);//si ya existe la actividad acumulamos las horas
		                    			}else {
		                    				horas_Martin[cont_M] = Integer.parseInt(horas[z]);
		                    				martin_Actividades[cont_M] = actividades[z];//si no existe la agregamos
		                    				cont_M ++;
		                    			}
		                    			
		                    			
		                    			
		                    		//Se hace lo mismo para cada usuario	
		                    		}else if (usuarios[z].equalsIgnoreCase("Catalina")){
		                    			int idx_C = -1;
		                    			int y;
		                    			
		                    			for(y=0; y<cont_C; y++) {
		                    				if (catalina_Actividad[y].equalsIgnoreCase(actividades[z])) {
		                    					idx_C = y;
		                    	                break;
		                    	            }
		                    			}if (idx_C != -1) {
		                    				horas_Catalina[idx_C] += Integer.parseInt(horas[z]);
		                    			}else {
		                    				horas_Catalina[cont_C] = Integer.parseInt(horas[z]);
		                    				catalina_Actividad[cont_C] = actividades[z];
		                    				cont_C++;
		                    			}	
		                    			
		                    				
		                    		}else if(usuarios[z].equalsIgnoreCase("Estefania")){
		                    			int idx_E = -1;
		                    			int s;
		                    			for(s=0; s<cont_E; s++) {
		                    				if (estefania_Actividad[s].equalsIgnoreCase(actividades[z])) {
		                    					idx_E = s;
		                    	                break;
		                    	            }
		                    			}if (idx_E != -1) {
		                    				horas_Estefania[idx_E] += Integer.parseInt(horas[z]);
		                    			}else {
		                    				horas_Estefania[cont_E] = Integer.parseInt(horas[z]);
		                    				estefania_Actividad[cont_E] = actividades[z];
		                    				cont_E++;
		                    			}
		                    		}
		                    	}
		                    		
		                    		//Como ya tenemos todas las actividades y horas acumuladas
		                    		//bucaremos el mayor de las horas y el nombre de las actividades
		                    		int horas_M = 0;
		                    		String ac_M = "";
		                    		
		                    		int horas_C = 0;
		                    		String ac_C = "";
		                    		
		                    		int horas_E = 0;
		                    		String ac_E = "";
		                    		
		                    		int q;
		                    		for (q = 0; q < cont_M ; q++) {
		                    			if(horas_Martin[q] > horas_M) {
		                    				horas_M = horas_Martin[q];
		                    				ac_M = martin_Actividades[q];
		                    			}
		                    		}
		                    		int j;
		                    		for(j=0; j < cont_E; j++) {
		                    			if(horas_Estefania[j] > horas_E) {
		                    				horas_E = horas_Estefania[j];
		                    				ac_E = estefania_Actividad[j];
		                    			}
		                    		}
		                    		int u;
		                    		for(u= 0; u< cont_C ; u++) {
		                    			if(horas_Catalina[u] > horas_C) {
		                    				horas_C = horas_Catalina[u];
		                    				ac_C = catalina_Actividad[u];
		                    			}
		                    		}
		                    		
		                    	System.out.println("* Martin"+  " -> " + ac_M +" -> "+"con " + horas_M + " horas registradas");
		                    	System.out.println("* Estefania"+ " -> "  + ac_E +" -> "+ "con "+ horas_E+ " horas registradas");
		                    	System.out.println("* Catalina"+" -> "  + ac_C +" -> "+ "con "+ horas_C+ " horas registradas");
		                    	System.out.println();
		                    	break;
								
		                    case 3:
		                    	//Pendiente
		                    	break;
		                    case 4:
		                    	//Pendiente
		                    	break;
		                }
		                
	                } while (opcionMenu != 5);
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
		    
		    int horas;
		    while (true) {
    		    

    		    if (sc.hasNextInt()) {
    		        horas = sc.nextInt();
    		        sc.nextLine(); // limpiar buffer (Pd: tengo malas experiencias ya con esta cosa)

    		        if (horas > 0 && horas <= 24) { //Control de error simple, no más de 24 horas (un día)
    		            break; 
    		        } else {
    		            System.out.println("Ingrese un valor entre 1 y 24 horas");
    		        }

    		    } else {
    		        System.out.println("Debe ingresar un número válido");
    		        sc.next(); // limpiar entrada inválida 
    		    }
    		}
		    //Falta control de error por acá pero lo hago después

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
			
			int op;

			while (true) {
			    System.out.print("Seleccione una opción: ");

			    if (sc.hasNextInt()) {
			        op = sc.nextInt();
			        sc.nextLine(); // limpiar

			        if (op == 0) {
			            return;
			        }

			        if (op >= 1 && op < contador) {
			            break; // evitar indices fuera de rango
			        } else {
			            System.out.println("Opción fuera de rango");
			        }

			    } else {
			        System.out.println("Debe ingresar un número");
			        sc.next(); // limpiar
			    }
			}

			System.out.println("Que deseas modificar?");
			System.out.println("0) Regresar."
					+ "\n1) Fecha"
					+ "\n2) Duracion"
					+ "\n3) Tipo de actividad");
			
			int op_2;

			while (true) {
			    

			    if (sc.hasNextInt()) {
			        op_2 = sc.nextInt();
			        sc.nextLine();

			        if (op_2 == 0) {
			            return;
			        }

			        if (op_2 >= 1 && op_2 <= 3) {
			            break;
			        } else {
			            System.out.println("Opción inválida");
			        }

			    } else {
			        System.out.println("Debe ingresar un número");
			        sc.next();
			    }
			}

			
			
	        switch(op_2){
	        	
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
	    		    
	    		    BufferedWriter bbbw = new BufferedWriter(new FileWriter("Taller_1/Registros.txt"));//volvemos abrir el archivo para hacer los cambios
	    		    int x;
	    		    for (x = 0; x<i ; x++) { //agregamos la informacion en el archivo
		    		    bbbw.write(usuarios[x] + ";" + fechas[x] + ";" + horas[x] + ";" + actividades[x]);
						if(x< i-1) {
		    		   		 bbbw.newLine();
						}
	    		    }
	    		    
	    		    System.out.println("Fecha modificada con exito!");
	    		    bbbw.close();
	    		    break;
	    		    
	        	case 2:
	        		int horas_Nueva = 0;

	        		while (true) {
	        		    System.out.print("Ingrese duración (horas): ");

	        		    if (sc.hasNextInt()) {
	        		        horas_Nueva = sc.nextInt();
	        		        sc.nextLine(); // limpiar buffer (Pd: tengo malas experiencias ya con esta cosa)

	        		        if (horas_Nueva > 0 && horas_Nueva <= 24) { //Control de error simple, no más de 24 horas (un día)
	        		            break; 
	        		        } else {
	        		            System.out.println("Ingrese un valor entre 1 y 24 horas");
	        		        }

	        		    } else {
	        		        System.out.println("Debe ingresar un número válido");
	        		        sc.next(); // limpiar entrada inválida 
	        		    }
	        		}
	        		
	    		    int indice2 = indices[op];//usamos los indices guardados previamente
	    		    horas[indice2]= String.valueOf(horas_Nueva);//cambiamos las horas en el lugar que corresponde
	    		    
	    		    BufferedWriter bw = new BufferedWriter(new FileWriter("Taller_1/Registros.txt"));//volvemos abrir el archivo para hacer los cambios
	    		    int k;
	    		    for (k= 0; k<i ; k++) { //agregamos la informacion en el archivo
		    		    bw.write(usuarios[k] + ";" + fechas[k] + ";" + horas[k] + ";" + actividades[k]);
						if(k< i-1) {
		    		    	bw.newLine();
						}
	    		    }
	        		System.out.println("Duración modificada con exito!");
	        		bw.close();
	        		break;
	        		
	        	case 3:
	        		System.out.println("0) Regresar.");
	        		System.out.print("Ingrese nuevo tipo de actividad: ");
	        		String actividad_Nueva= sc.nextLine();
	        		
	        		if (actividad_Nueva.equals("0")) {
	        			System.out.println("No se modifico ningúna actividad.");
	        			return;
	        		}
	    		    int indice3 = indices[op];//usamos los indices guardados previamente
	    		    actividades[indice3]= actividad_Nueva;//cambiamos las horas en el lugar que corresponde
	    		    
	    		    BufferedWriter bW = new BufferedWriter(new FileWriter("Taller_1/Registros.txt"));//volvemos abrir el archivo para hacer los cambios
	    		    int v;
	    		    for (v= 0; v<i ; v++) { //agregamos la informacion en el archivo
		    		    bW.write(usuarios[v] + ";" + fechas[v] + ";" + horas[v] + ";" + actividades[v]);
						if(v< i-1) {
		    		    	bW.newLine();
						}
	    		    }
	        		System.out.println("Actividad modificada con exito!");
	        		bW.close();
	        		break;
	        	default:
	        		
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
	
		public static void eliminarActividad(String nombre, Scanner sc) {
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
		
				if (op == 0) {
				    return;
				}

				if (op >= contador && op <= 0) {
				    System.out.println("Opción inválida.");
				    return;
				}
				
				int eliminar = indices[op];
				usuarios[eliminar] = null;
				fechas[eliminar]= null;
				horas[eliminar]=null;
				actividades[eliminar]=null;
				
				int e;
				int r;
				for(e= 0; e<i ; e++) {
					if(usuarios[e] == null) {
						for(r = e+1; r< i; r++, e++ ) {
							String aux = usuarios[e];
							usuarios[e] = usuarios[r];
							usuarios[r]= aux;
														
							String x = fechas[e];
							fechas[e] = fechas[r];
							fechas[r]= x;
							
							String mm = actividades[e];
							actividades[e] = actividades[r];
							actividades[r]= mm;
							
							String temp = horas[e];
							horas[e] = horas[r];
							horas[r]= temp;
						}
					}
				}
				i--;
				
				int contador2 = 1;
				
				for (int j = 0; j < i; j++) { //para ver si se elimino la actividad
				     {
				        System.out.println(contador2 + ") " +
				                usuarios[j] + ";" + fechas[j] + ";" + horas[j] + ";" + actividades[j]);
		
				        indices[contador2] = j;
				        contador2++;
				    }
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter("Taller_1/Registros.txt"));
				
    		    int h;
    		    for (h= 0; h<i ; h++) { //agregamos la informacion en el archivo
	    		    bw.write(usuarios[h] + ";" + fechas[h] + ";" + horas[h] + ";" + actividades[h]);
					if(h< i-1) {
	    		    	bw.newLine();
					}
    		    }
    		    bw.close();

				System.out.println("\nActividad eliminada correctamente.");
		        sc.nextLine();
	    	}catch (FileNotFoundException e) {
		            System.out.println("No se encontró el archivo");
	        }catch (IOException e) {
		            System.out.println("Error al escribir en el archivo");
		       }
	}
	public static void cambiarContraseña(String nombre, Scanner sc, String[] nombres, String[] contraseñas, int indexUsuario, int i) throws IOException  {
		sc.nextLine();
		System.out.print("Ingrese nueva contraseña: ");
		String contraseña_Nueva; 
		contraseña_Nueva = sc.nextLine();
		
		contraseñas[indexUsuario] = contraseña_Nueva;
	    BufferedWriter bbW = new BufferedWriter(new FileWriter("Taller_1/Usuarios.txt"));//volvemos abrir el archivo para hacer los cambios
	    int x;
	    for (x = 0; x<i ; x++) { //agregamos la informacion en el archivo
		    bbW.write(nombres[x] + ";" + contraseñas[x]);
			if(x< i-1) {
		    	bbW.newLine();
			}
	    }
	    
	    bbW.close();
	   
		System.out.println("Contraseña nueva modificada con exito!!");
		
		int p;
		for (p = 0; p< nombres.length; p++){ // esto solo es para guiarnos con las contraseñas despues se eliminara
			System.out.print(nombres[p]+ " ");
			System.out.print(contraseñas[p]+ " ");
			System.out.println();
		}
		
	}
	
	
}
