package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import info.Info;

public class Lectura {
	
	public static TablaInfo leerArchivo(File archivo) {

		ArrayList<String> cabecera = new ArrayList<>();
		ArrayList<Info> listaInfo = new ArrayList<>();	
		
		TablaInfo tablaInfo = new TablaInfo();
		
		if(archivo!=null && archivo.isFile()) {
			
			try {
				
				FileInputStream fis = new FileInputStream(archivo);
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				BufferedReader obj = new BufferedReader(isr);

				try {
					
					String linea = "";
					String lineaCabecera = "";
					String celda = "";
					
					boolean isCabecera = false;
					String values[];
					
					int fila = 0;
					
					//Lee cada línea
					while ((linea = obj.readLine ())!= null) {    

						//Separa los valores por punto y coma
						values = linea.split(";");
						
						//Cantidad de campos mayor que 1
						if(values.length>1) {

							//Cabecera (primera linea)
							if(!isCabecera) {
								
								for(int i = 0; i<values.length; i++) {
									lineaCabecera = values[i].trim();
									cabecera.add(lineaCabecera);
								}	
								
								isCabecera = true;
								
							//Filas
							}else {
								
								Info info = new Info();
								listaInfo.add(info);
								
								for(int i = 0; i < values.length; i++) {

									celda = values[i];	
									
									//Se eliminan los espacios en blanco
									celda = celda.trim();
									
									switch(i) {
										
										//Empresa
										case 0:
											
											try {
												Integer empresa;
												empresa = Integer.parseInt(celda);
												listaInfo.get(fila).setEmpresa(empresa);
											} catch (NumberFormatException e) {
												System.out.println("Se ha producido un error en el campo Empresa");
											}

											break;
											
										//Almacén
										case 1:
											
											try {
												Integer almacen;
												almacen = Integer.parseInt(celda);
												listaInfo.get(fila).setAlmacen(almacen);
											} catch (NumberFormatException e) {
												System.out.println("Se ha producido un error en el campo Almacén");
											}										
											break;	
											
										//Código Articulo
										case 2:
											listaInfo.get(fila).setCodArticulo(celda);
											break;	
											
										//Descripción Articulo
										case 3:
											listaInfo.get(fila).setDesArticulo(celda);
											break;	
											
										//Ubicación
										case 4:
											listaInfo.get(fila).setUbicacion(celda);
											break;	
											
										//Existencias
										case 5:
											
											try {
												celda = celda.replaceAll(",",".");
												Float existencias;
												existencias = Float.parseFloat(celda);
												listaInfo.get(fila).setExistencias(existencias);
											} catch (NumberFormatException e) {
												System.out.println("Se ha producido un error en el campo Existencias");
											}	
											break;	
											
										//Fecha y Hora
										case 6:
											
											listaInfo.get(fila).setFechaHora(celda);
											
											break;
										default:
											System.out.println("Valor no asignado");
										break;
									}

								}
								
								fila++;
							}
						}
		
					}//FIN WHILE
							
							
						
					//Archivo leido correctamente
					tablaInfo = new TablaInfo(cabecera,listaInfo);
					

					
				} catch (IOException e) {
					System.out.println("Se ha producido un error");
				} finally {
					
					
					//Cerrar el Buffered Reader
					try {
						if(obj!=null) {
							obj.close();
						}
					} catch (IOException e) {
						System.out.println("Se ha producido un error al cerrar el archivo");
					}
					
					//Cerrar el Input Stream Reader
					try {
						if(isr!=null) {
							isr.close();
						}
					} catch (IOException e) {
						System.out.println("Se ha producido un error al cerrar el archivo");
					}		
					
					//Cerrar el File Input Stream
					try {
						if(fis!=null) {
							fis.close();
						}
					} catch (IOException e) {
						System.out.println("Se ha producido un error al cerrar el archivo");
					}	
					
					

				}
				
				
				
			} catch (FileNotFoundException e) {
				System.out.println("Archivo no encontrado");
			}
		}
		

			return tablaInfo;
		
		
		
	}
	


}
