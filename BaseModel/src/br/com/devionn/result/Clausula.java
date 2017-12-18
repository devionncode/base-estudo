package br.com.devionn.result;

import java.io.Serializable;

public class Clausula implements Serializable {

	private String labelOperador;
	private String titulo;
	private String propriedade;
	private String operador;
	private String value;
	private String value1;
	private String value2;
	
	
	
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getLabelOperador() {
		return labelOperador;
	}
	public void setLabelOperador(String labelOperador) {
		this.labelOperador = labelOperador;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPropriedade() {
		return propriedade;
	}
	public void setPropriedade(String propriedade) {
		this.propriedade = propriedade;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getWhere(){
		String where = "";
		switch (operador.trim().toLowerCase()) {
		case "=": case "<=":case ">=":case "<":case ">":
			where = " " +propriedade + " " + operador + " '" + value1 + "' ";
			break;
		case "like":
			where = " upper(" +propriedade + ") " + operador + " '%" + value1.trim().toUpperCase() + "%' ";
			break;
		case "between":
			where = " " + propriedade + " " + operador + "  "+ value1 + " and "+ value2 + " ";
		default:
			break;
		}
		return where;
	}
	 
}
