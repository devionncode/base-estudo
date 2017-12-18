/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devionn.das;

import java.util.List;

import javax.ejb.Local;

import br.com.devionn.model.Usuario;
import br.com.devionn.result.Login;
import br.com.devionn.result.UserAcesso;


@Local
public interface UsuarioBeanLocal extends AbstractBean<Usuario>{
  
	
	public UserAcesso getByLongin(Login login) throws Exception;
	
	public List<Usuario> buscarPorCodigoOuRazaoSocial(Usuario usuario, String nome) throws Exception;
	
	public Login alterarSenha(Usuario usuario, Login user) throws Exception;
	
	public Boolean existeLogin(Usuario usuario, String login,Long idUsuario) throws Exception;
}
