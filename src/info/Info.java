package info;

import java.util.ArrayList;

public class Info {

	private int empresa;
	private int almacen;
	private float existencias;
	private String codArticulo;
	private String desArticulo;
	private String ubicacion;
	private String fechaHora;
	
	//Constructores
	public Info() {

	}
	
	public Info(int empresa, int almacen, String codArticulo, String desArticulo, String ubicacion, float existencias, String fechaHora) {
		this.empresa = empresa;
		this.almacen = almacen;
		this.codArticulo = codArticulo;
		this.desArticulo = desArticulo;
		this.ubicacion = ubicacion;
		this.existencias = existencias;
		this.fechaHora = fechaHora;
	}
	
	//Getters and Setters
	public int getEmpresa() {
		return empresa;
	}
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	public int getAlmacen() {
		return almacen;
	}
	public void setAlmacen(int almacen) {
		this.almacen = almacen;
	}
	public float getExistencias() {
		return existencias;
	}
	public void setExistencias(float existencias) {
		this.existencias = existencias;
	}
	public String getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}
	public String getDesArticulo() {
		return desArticulo;
	}
	public void setDesArticulo(String desArticulo) {
		this.desArticulo = desArticulo;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	
	
	public ArrayList<Object> toArrayList() {
		
		ArrayList<Object> objetos = new ArrayList<Object>();
		objetos.add(this.empresa);
		objetos.add(this.almacen);
		objetos.add(this.codArticulo);
		objetos.add(this.desArticulo);
		objetos.add(this.ubicacion);
		objetos.add(this.existencias);
		objetos.add(this.fechaHora);
		return objetos;
	}
	
	public String toString() {
		
		String cadena = 
		
		"Empresa: " + this.empresa + "\n" +
		"Almacén: " + this.almacen + "\n" +
		"Código Articulo: " + this.codArticulo + "\n" +
		"Descripción Articulo: " + this.desArticulo + "\n" +
		"Ubicación: " + this.ubicacion + "\n" +
		"Existencias: " + this.existencias + "\n" +
		"Fecha/Hora: " + this.fechaHora + "\n";
		
		
		return cadena;
		
		
	}

}
