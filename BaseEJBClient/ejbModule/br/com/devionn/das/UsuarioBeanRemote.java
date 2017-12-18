package br.com.devionn.das;

import javax.ejb.Remote;

import br.com.devionn.model.Usuario;

@Remote
public interface UsuarioBeanRemote extends AbstractBean<Usuario>{

}
