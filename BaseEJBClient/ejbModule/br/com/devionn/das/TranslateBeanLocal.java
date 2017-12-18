package br.com.devionn.das;

import java.util.HashMap;

import javax.ejb.Local;

import br.com.devionn.model.Translate;

@Local
public interface TranslateBeanLocal extends AbstractBean<Translate> {
	
	public HashMap<String, String> getByIdioma(String idioma) throws Exception;

}
