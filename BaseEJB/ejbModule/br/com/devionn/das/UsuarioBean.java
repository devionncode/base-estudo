/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devionn.das;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.devionn.model.Dispositivo;
import br.com.devionn.model.Usuario;
import br.com.devionn.result.Login;
import br.com.devionn.result.UserAcesso;


@Stateless
public class UsuarioBean extends AbstractBeanImpl<Usuario> implements UsuarioBeanLocal ,UsuarioBeanRemote{

	@EJB
	private DispositivoBeanLocal dispositivoBean;
	
    @Override
    public Class getClasse() {
        return Usuario.class;
    }
    
    @Override
    public Usuario save(Usuario usuario, Usuario obj) throws Exception {
    	obj.setIdEmpresa(usuario.getIdEmpresa());    	
    	return super.save(usuario, obj);
    }
    
    @Override
    public Usuario insert(Usuario usuario, Usuario obj) throws Exception {
    	obj.setDataHoraCadastro(new Date());
    	obj.setDataHoraAlteracao(new Date());
    	return super.insert(usuario, obj);
    }
    
    @Override
    public Usuario update(Usuario usuario, Usuario obj) throws Exception { 
    	obj.setDataHoraAlteracao(new Date());
    	return super.update(usuario, obj);
    }
    
    public Boolean existeLogin(Usuario usuario, String login,Long idUsuario) throws Exception {
    	String sql = "Select u.id from Usuario u where upper(u.login) = ? and u.id != ?  ";
    	Query query = entity.createNativeQuery(sql);
    	query.setParameter(1, login.toUpperCase().trim());
    	query.setParameter(2, idUsuario);
    	return !query.getResultList().isEmpty();
    }
    
    
    public UserAcesso getByLongin(Login login) throws Exception {
    	Query query = entity.createQuery("Select u from Usuario u where upper(u.login) = :login and upper(u.senha) = :senha and u.ativo = true and u.deletado = false ");
    	query.setParameter("senha", login.getSenha().toUpperCase().trim());
    	query.setParameter("login", login.getLogin().toUpperCase().trim());
    	List<Usuario> lista = query.getResultList();
    	if (lista.isEmpty()){
    		return null;
    	}
    	Usuario user = lista.get(0);
    	UserAcesso userAcesso = null; 
	    user.setToken(gerarToken(user, login));
	    user = save(user, user);
	    Dispositivo disp = dispositivoBean.buscarPor(login.getPlataforma(), login.getSerial(), login.getUuid()); 
	    if (disp == null){
	    	disp = new Dispositivo();
	    	disp.setPlataforma(login.getPlataforma());
		    disp.setSerial(login.getSerial());
		    disp.setUuid(login.getUuid());
	    }
	    disp.setToken(user.getToken());	    
	    disp.setNome(login.getNome());
	    disp.setVersaoSistema(login.getVersaoSistema());
	    disp.setIdUsuario(user.getId());
	    disp = dispositivoBean.save(user, disp);
	    
	    userAcesso = new UserAcesso();
	    userAcesso.setAtivo(user.getAtivo());
	    userAcesso.setEmail(user.getEmail());
	    userAcesso.setIdGrupoUsuario(user.getIdGrupoUsuario());
	    userAcesso.setLogin(user.getLogin());
	    userAcesso.setRazaoSocial(user.getRazaoSocial());
	    userAcesso.setSenha(user.getSenha());
	    userAcesso.setId(user.getId());
	    userAcesso.setToken(user.getToken());
	    userAcesso.setIdioma(user.getIdioma().getColuna());
    	return userAcesso;
    }
    
    public List<Usuario> buscarPorCodigoOuRazaoSocial(Usuario usuario, String nome) throws Exception {
    	String sql = "Select u From Usuario u where u.deletado = false and ( upper(u.razaoSocial) like :razaoSocial or u.id = :id ) "
    			+ "  and u.idEmpresa = :idEmpresa "
    			+ " order by u.razaoSocial  ";
    	Query query = entity.createQuery(sql);
    	query.setParameter("razaoSocial", "%"+ nome.trim().toUpperCase()+ "%");
    	query.setParameter("idEmpresa", usuario.getIdEmpresa());
    	Long id = 0L;
    	try {
    		id = Long.parseLong(nome.trim());
    	}catch (Exception e) {
    		id = -1L;
		}
    	query.setParameter("id", id);
    	return query.getResultList();
    }
    
    public List<Usuario> findAll(Usuario usuario) throws Exception {
    	String sql = "Select u From Usuario u where u.deletado = false  "
    			+ "  and u.idEmpresa = :idEmpresa "
    			+ " order by u.razaoSocial  ";
    	Query query = entity.createQuery(sql);
    	query.setParameter("idEmpresa", usuario.getIdEmpresa());
    	return query.getResultList();
    }
    
    
    public String gerarToken(Usuario user,Login login){
    	try {
    	    Algorithm algorithm = Algorithm.HMAC256(user.getEmail());  
    	    String token = JWT.create()
    	        .withIssuer(user.getLogin()).withKeyId(user.getSenha())
    	        .withClaim("plataforma", login.getPlataforma())
    	        .withClaim("versaoSistema", login.getVersaoSistema())
    	    	.withClaim("serial",login.getSerial())
    	    	.withClaim("nome", login.getNome())
    	    	.withClaim("uuid", login.getUuid())
//    	        .withExpiresAt(data)
    	        .sign(algorithm);
    	    return token;
    	} catch (UnsupportedEncodingException exception){
    	    //UTF-8 encoding not supported
    	} catch (JWTCreationException exception){
    	    //Invalid Signing configuration / Couldn't convert Claims.
    	}
    	return null;
    }
    
    
	@Override
	public Boolean insertNativo(Usuario obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into public.Usuario (id,datahoraalteracao,datahoracadastro,login, senha, observacao, ativo,idGrupoUsuario, idEmpresa, idIdioma,idUsuarioAlteracao )  "
				+ "  values (?, now(),now(), ?, ?, ?, ?, ?, ?, ?, ?); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getLogin());
    	query.setParameter(3, obj.getSenha());
    	query.setParameter(4, obj.getObservacao());
    	query.setParameter(5, obj.getAtivo());
    	query.setParameter(6, obj.getGrupoUsuario().getId());
    	query.setParameter(7, obj.getIdEmpresa());
    	query.setParameter(8, obj.getIdioma().getId());
    	query.setParameter(9, obj.getIdUsuarioAlteracao());
    	query.executeUpdate();
		return true;
	}
	
	public Login alterarSenha(Usuario usuario, Login login) throws Exception {
    	Usuario user = find(usuario, login.getId());
    	user.setSenha(login.getSenha());
    	user.setLogin(login.getLogin());
    	user.setToken(gerarToken(user, login));
    	Dispositivo disp = dispositivoBean.buscarPor(login.getPlataforma(), login.getSerial(), login.getUuid());
    	if (disp == null){
	    	disp = new Dispositivo();
	    	disp.setPlataforma(login.getPlataforma());
		    disp.setSerial(login.getSerial());
		    disp.setUuid(login.getUuid());
	    }
	    disp.setToken(user.getToken());	    
	    disp.setNome(login.getNome());
	    disp.setVersaoSistema(login.getVersaoSistema());
	    disp.setIdUsuario(user.getId());
	    disp = dispositivoBean.save(user, disp);
    	
    	user = save(user, user);
    	login.setToken(user.getToken());
    	return login;
    }

 
}
