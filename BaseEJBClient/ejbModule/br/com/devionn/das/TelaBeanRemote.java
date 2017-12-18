package br.com.devionn.das;

import javax.ejb.Remote;

import br.com.devionn.model.Tela;

@Remote
public interface TelaBeanRemote extends AbstractBean<Tela>  {

}
