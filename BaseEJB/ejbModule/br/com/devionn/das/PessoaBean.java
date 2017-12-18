package br.com.devionn.das;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Pessoa;

/**
 * Session Bean implementation class PessoaBean
 */
@Stateless
@LocalBean
public class PessoaBean extends AbstractBeanImpl<Pessoa> implements PessoaBeanLocal {

    /**
     * Default constructor. 
     */
    public PessoaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Pessoa.class;
	}

	@Override
	public Boolean insertNativo(Pessoa obj) throws Exception {
		Query query = entity.createNativeQuery(" insert into Pessoa (id,dataalteracao,datacadastro,deletado, versao,razaoSocial, fantasia, cnpj, juridica,email, telefone, celular, sexo )  "
				+ "  values (?, now(),now(),false , 0, ?, ?, ?, ?, ?, ?, ?, ?); ");
    	query.setParameter(1, obj.getId());
    	query.setParameter(2, obj.getRazaoSocial());
    	query.setParameter(3, obj.getFantasia());
    	query.setParameter(4, obj.getCnpj());
    	query.setParameter(5, obj.getJuridica());
    	query.setParameter(6, obj.getEmail());
    	query.setParameter(7, obj.getTelefone());
    	query.setParameter(8, obj.getCelular());
    	query.setParameter(9, obj.getSexo());
    	query.executeUpdate();
		return true;
	}
}
