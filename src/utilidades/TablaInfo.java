package utilidades;

import java.util.ArrayList;

import info.Info;

public class TablaInfo {
	
	private ArrayList<String> cabecera = new ArrayList<>();
	private ArrayList<Info> listaInfo = new ArrayList<>();	
	private Boolean isValid = false;

	//Constructores
	public TablaInfo() {
		//No hace nada
	}

	public TablaInfo(ArrayList<String> cabecera, ArrayList<Info> listaInfo) {
		this.cabecera = cabecera;
		this.listaInfo = listaInfo;
		this.isValid = true;
	}
	
	//Getters and Setters
	public ArrayList<String> getCabecera() {
		return cabecera;
	}

	public ArrayList<Info> getListaInfo() {
		return listaInfo;
	}
	
	public void setCabecera(ArrayList<String> cabecera) {
		this.cabecera = cabecera;
	}

	public void setListaInfo(ArrayList<Info> listaInfo) {
		this.listaInfo = listaInfo;
	}
	
	public Boolean getIsValid() {
		return isValid;
	}
	
	//Método que resetea la información
	public void borrarInfo() {
		this.cabecera = null;
		this.listaInfo = null;
		this.isValid = false;
	}

}
