package br.com.devionn.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.devionn.model.generic.BaseEntityImpl;

@Entity  
@Table(schema="public")
public class Translate extends BaseEntityImpl {
	
	@Basic(optional = false)
	@Column(length=1000)
	private String pt;
	@Basic(optional = false)
	@Column(length=1000)
	private String chave;
	@Basic(optional = false)
	@Column(length=1000)
	private String es;
	@Basic(optional = false)
	@Column(length=1000)
	private String en;
	
	
	public String getPt() {
		return pt;
	}
	public void setPt(String pb_Brasil) {
		this.pt = pb_Brasil;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getEs() {
		return es;
	}
	public void setEs(String espanhol) {
		this.es = espanhol;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String ingles) {
		this.en = ingles;
	}
	
	
	
	
	
	

}
 