package br.com.devionn.das;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Dispositivo;
import br.com.devionn.model.Usuario;

@Stateless
public class DispositivoBean extends AbstractBeanImpl<Dispositivo> implements DispositivoBeanLocal, DispositivoBeanRemote {

	@Override
	public Class getClasse() {
		return Dispositivo.class;
	}

	public Dispositivo buscarPor(String plataforma,String serial, String uuid) throws Exception {
		String sql = "Select d From Dispositivo d where d.plataforma = :plataforma and d.uuid = :uuid and d.serial = :serial ";
		Query query = entity.createQuery(sql, Dispositivo.class);
		query.setParameter("plataforma", plataforma);
		query.setParameter("uuid", uuid);
		query.setParameter("serial", serial);
		List<Dispositivo> lista = query.getResultList();
		if (lista.isEmpty()){
			return null;
		}
		return lista.get(0);
	}
	
	public Dispositivo buscarPorToken(Usuario usuario, String token) throws Exception {
		String sql = "Select d From Dispositivo d where d.token = :token ";
		Query query = entity.createQuery(sql, Dispositivo.class);
		query.setParameter("token", token);
		List<Dispositivo> lista = query.getResultList();
		if (lista.isEmpty()){
			return null;
		}
		return lista.get(0);
	}
	
}
