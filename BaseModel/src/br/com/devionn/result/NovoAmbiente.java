package br.com.devionn.result;

import br.com.devionn.model.Empresa;
import br.com.devionn.model.Usuario;

public class NovoAmbiente {

	private Usuario usuario;
	private Empresa empresa;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
