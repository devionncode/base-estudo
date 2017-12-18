package br.com.devionn.das;

import javax.ejb.Local;

import br.com.devionn.model.Pessoa;

@Local
public interface PessoaBeanLocal extends AbstractBean<Pessoa> {

}
