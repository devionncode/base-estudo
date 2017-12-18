package br.com.devionn.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.devionn.model.generic.BaseEntityImpl;

@Table(name = "Tela",schema="public",indexes={ @Index(columnList="sistema,ativo,idmenu,deletado", name="acesso_idx")})
@Entity
public class Tela  extends BaseEntityImpl{
    
	@Column(length=50)
	@Basic(optional=false)
    private String nome;
	@Column(length=200)
	private String descricao;
	@Basic(optional = false)
    private Integer posicao = 0;
    @Column(length=200)
    private String url;
    private Boolean ativo = true;    
    private Integer sistema = 1;
    private Integer grupo = 0;
    @Column(length=150)
    private String icone; 
    private Long idTelaPai;
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idmenu", referencedColumnName="id", insertable = false, updatable = false)
	private Menu menu;
    @ManyToOne
	@JoinColumn(name="idtelapai", referencedColumnName="id", foreignKey = @ForeignKey(name = "tela_telapai_fk"), insertable = false, updatable = false)
	private Tela telaPai;
    private Long idMenu;
    
    @Transient
    private List<Direito> direitos;
    
    public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public Tela getTelaPai() {
		return telaPai;
	}

	public void setTelaPai(Tela telaPai) {
		if (telaPai != null) {
			this.idTelaPai = telaPai.getId();
		}
		this.telaPai = telaPai;
	}

	public List<Direito> getDireitos() {
		return direitos;
	}
    
    public void setDireitos(List<Direito> direitos) {
		this.direitos = direitos;
	}
    
    public Long getIdTelaPai() {
		return idTelaPai;
	}
    
    public void setIdTelaPai(Long idTelaPai) {
		this.idTelaPai = idTelaPai;
	}
   
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		if (menu != null) {
			this.idMenu = menu.getId();
		}
		this.menu = menu;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Integer getGrupo() {
		return grupo;
	}
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}
    
    public Integer getSistema() {
		return sistema;
	}
    
    public void setSistema(Integer sistema) {
		this.sistema = sistema;
	}
    
    
}
