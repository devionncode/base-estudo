package br.com.devionn.result;

import java.util.List;

import br.com.devionn.model.Direito;
import br.com.devionn.model.Usuario;

public class PermissaoAcesso {

	private List<Direito> direitos;
	private List<Usuario> usuarios;
	
	public List<Direito> getDireitos() {
		return direitos;
	}
	
	public void setDireitos(List<Direito> direitos) {
		this.direitos = direitos;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
