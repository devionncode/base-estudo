package br.com.devionn.das;

import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.devionn.model.Translate;

/**
 * Session Bean implementation class TranslateBean
 */
@Stateless
@LocalBean
public class TranslateBean extends AbstractBeanImpl<Translate> implements TranslateBeanLocal {

    /**
     * Default constructor. 
     */
    public TranslateBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Class getClasse() {
		return Translate.class;
	}
	
	public HashMap<String, String> getByIdioma(String idioma) throws Exception{
		try {
			Query query = entity.createQuery("Select t.chave, t." + idioma + "  From Translate t order by t.chave asc");
			List<Object[]> data = query.getResultList();
			HashMap<String, String> hash = new HashMap<>();
			for (Object[] object : data) {
				hash.put(object[0].toString(), object[1].toString());
			}
			return hash;
		} catch (Exception e) {
			throw e;
		}
	}

}
