package br.com.devionn.das;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.devionn.model.Sistema;

//@Singleton
//@Startup
public class AlimentarBanco {

	@EJB
	SistemaBeanLocal sistemaBean;
	@EJB
	ModuloBeanLocal moduloBean;
	@EJB
	MenuBeanLocal menuBean;
	@EJB
	TelaBeanLocal telaBean;
	@EJB
	AcaoBeanLocal acaoBean;
	@EJB
	TelaAcaoBeanLocal telaAcaoBean;
	@EJB
	DireitoGrupoBeanLocal direitoGrupoBean;	
	@EJB
	GrupoUsuarioBeanLocal grupoUsuarioBean;
	@EJB
	UsuarioBeanLocal usuarioBean;
	@EJB
	PessoaBeanLocal pessoaBean;
	@EJB
	IdiomaBeanLocal idiomaBean;
	
	@PostConstruct
	public void alimentar()throws Exception{
		// Thread.sleep(1000 * 10);
		Sistema sistema = sistemaBean.find(null, 1L);
		if (sistema == null){
			sistema = new Sistema();
			sistema.setBuild(1L);
			sistemaBean.insert(null, sistema);
			versao(1);
		}
//		versao(sistema.getBuild().intValue());
	}
	
	public void versao(Integer versao) throws Exception {
		InputStream input = getClass().getResourceAsStream("/br/com/devionn/atualizacao/versao" + versao + ".sql");
		InputStreamReader reader = new InputStreamReader(input);
		BufferedReader buffer = new BufferedReader(reader);
		StringBuilder builder = new StringBuilder();
		while (buffer.ready()){
			builder.append(buffer.readLine());
			builder.append("\n");
		}
		buffer.close();
		reader.close();
		input.close();
		//System.out.println(builder.toString());
		sistemaBean.executeSQL(builder.toString());
		
	}
	/*
	public void versao1() throws Exception { 
		
		Modulo modulo = new Modulo();
		modulo.setId(1L);
		modulo.setAtivo(true);
		modulo.setDescricao("ADM");
		modulo.setMobile(false);
		modulo.setPosicao(1);
		modulo.setPrincipal(true);
		moduloBean.insertNativo(modulo);
		
		modulo = new Modulo();
		modulo.setId(2L);
		modulo.setAtivo(true);
		modulo.setDescricao("Sales");
		modulo.setMobile(false);
		modulo.setPosicao(2);
		modulo.setPrincipal(true);
		moduloBean.insertNativo(modulo);
		
		modulo = new Modulo();
		modulo.setId(3L);
		modulo.setAtivo(true);
		modulo.setDescricao("Super Dash");
		modulo.setMobile(false);
		modulo.setPosicao(3);
		modulo.setPrincipal(true);
		moduloBean.insertNativo(modulo);
		
		Menu menu = new Menu();  
		menu.setId(1L);
		menu.setAtivo(true);
		menu.setDescricao("Empresas");
		menu.setExpandido(false);
		menu.setIcone("");
		menu.setNome("Empresas");
		menu.setPosicao(1);
		menu.setModulo(modulo);
		menuBean.insertNativo(menu);
				
		Tela tela1 = new Tela();
		tela1.setAtivo(true);
		tela1.setId(1L);
		tela1.setNome("Cadastrar Empresa");
		tela1.setDescricao("Tela para Cadastro de Empresa");
		tela1.setGrupo(1);
		tela1.setIcone("");
		tela1.setUrl("empresa-cadastro");
		tela1.setMenu(menu);
		telaBean.insertNativo(tela1);
		
		Tela tela2 = new Tela();
		tela2.setAtivo(true);
		tela2.setId(2L);
		tela2.setNome("Pesquisar Empresa");
		tela2.setDescricao("Tela para Pesquisa de Empresa");
		tela2.setGrupo(1);
		tela2.setIcone("");
		tela2.setUrl("empresa-pesquisa");
		tela2.setMenu(menu);
		telaBean.insertNativo(tela2);
		
		Tela tela3 = new Tela();
		tela3.setAtivo(true);
		tela3.setId(3L);
		tela3.setNome("Cadastrar Segmento");
		tela3.setDescricao("Tela para Cadastro de Segmento");
		tela3.setGrupo(1);
		tela3.setIcone("");
		tela3.setUrl("segmento-cadastro");
		tela3.setMenu(menu);
		telaBean.insertNativo(tela3);
		
		Tela tela4 = new Tela();
		tela4.setAtivo(true);
		tela4.setId(4L);
		tela4.setNome("Pesquisar Segmento");
		tela4.setDescricao("Tela para Pesquisar Segmento");
		tela4.setGrupo(1);
		tela4.setIcone("");
		tela4.setUrl("segmento-pesquisa");
		tela4.setMenu(menu);
		telaBean.insertNativo(tela4);
		
		Tela tela5 = new Tela();
		tela5.setAtivo(true);
		tela5.setId(5L);
		tela5.setNome("Licença");
		tela5.setDescricao("Licença de uso do sistema");
		tela5.setGrupo(1);
		tela5.setIcone("");
		tela5.setUrl("licenca");
		tela5.setMenu(menu);
		telaBean.insertNativo(tela5);
		
				
		Acao acaoNovo = new Acao();
		acaoNovo.setId(1L);
		acaoNovo.setNome("NOVO");
		acaoNovo.setDescricao("PERMITE REALIZAR O CADASTRO");
		acaoNovo.setAction("novo");
		acaoNovo.setIcone("");
		acaoBean.insertNativo(acaoNovo);
		
		Acao acaoAlterar = new Acao();
		acaoAlterar.setId(2L);
		acaoAlterar.setNome("ALTERAR");
		acaoAlterar.setDescricao("PERMITE ALTERAR O CADASTRO");
		acaoAlterar.setAction("alterar");
		acaoAlterar.setIcone("");
		acaoBean.insertNativo(acaoAlterar);
		
		Acao acaoExcluir = new Acao();
		acaoExcluir.setId(3L);
		acaoExcluir.setNome("EXCLUIR");
		acaoExcluir.setDescricao("PERMITE EXCLUIR O CADASTRO");
		acaoExcluir.setAction("remover");
		acaoExcluir.setIcone("");
		acaoBean.insertNativo(acaoExcluir);
		
		Acao acaoImprimir = new Acao();
		acaoImprimir.setId(4L);
		acaoImprimir.setNome("IMPRIMIR");
		acaoImprimir.setAction("imprimir");
		acaoImprimir.setDescricao("PERMITE IMPRIMIR OS DADOS");
		acaoImprimir.setIcone("");
		acaoBean.insertNativo(acaoImprimir);
		
		TelaAcao telaAcao1 = new TelaAcao();
		telaAcao1.setAcao(acaoNovo);
		telaAcao1.setTela(tela2);
		telaAcao1.setPosicao(0);
		telaAcao1.setId(1L); 
		telaAcaoBean.insertNativo(telaAcao1);
		
		TelaAcao telaAcao2 = new TelaAcao();
		telaAcao2.setAcao(acaoAlterar);
		telaAcao2.setTela(tela2);
		telaAcao2.setPosicao(0);
		telaAcao2.setId(2L); 
		telaAcaoBean.insertNativo(telaAcao2);
		
		
		TelaAcao telaAcao3 = new TelaAcao();
		telaAcao3.setAcao(acaoExcluir);
		telaAcao3.setTela(tela2);
		telaAcao3.setPosicao(0);
		telaAcao3.setId(3L); 
		telaAcaoBean.insertNativo(telaAcao3);
		
		
		TelaAcao telaAcao4 = new TelaAcao();
		telaAcao4.setAcao(acaoImprimir);
		telaAcao4.setTela(tela2);
		telaAcao4.setPosicao(0);
		telaAcao4.setId(4L); 
		telaAcaoBean.insertNativo(telaAcao4);
		
		GrupoUsuario grupo = new GrupoUsuario();
		grupo.setId(1L);
		grupo.setAtivo(true);
		grupo.setNome("ADMINISTRATIVO DO SISTEMA");
		grupo.setDescricao("GRUPO PRINCIPAL RESPONSAVEL Por Administrar o Sistema");
		grupo.setIdEmpresa(1L);
		grupoUsuarioBean.insertNativo(grupo);
		
		
		DireitoGrupo direito1 = new DireitoGrupo();
		direito1.setId(1L);
		direito1.setAcao(acaoNovo);
		direito1.setGrupoUsuario(grupo);
		direito1.setStatus(true);
		direito1.setTela(tela2);
		direitoGrupoBean.insertNativo(direito1);
		
		DireitoGrupo direito2 = new DireitoGrupo();
		direito2.setId(2L);
		direito2.setAcao(acaoAlterar);
		direito2.setGrupoUsuario(grupo);
		direito2.setStatus(true);
		direito2.setTela(tela2);
		direitoGrupoBean.insertNativo(direito2);
		
		DireitoGrupo direito3 = new DireitoGrupo();
		direito3.setId(3L);
		direito3.setAcao(acaoExcluir);
		direito3.setGrupoUsuario(grupo);
		direito3.setStatus(true);
		direito3.setTela(tela2);
		direitoGrupoBean.insertNativo(direito3);
		
		DireitoGrupo direito4 = new DireitoGrupo();
		direito4.setId(4L);
		direito4.setAcao(acaoImprimir);
		direito4.setGrupoUsuario(grupo);
		direito4.setStatus(true);
		direito4.setTela(tela2);
		direitoGrupoBean.insertNativo(direito4);
		
		DireitoGrupo direito5 = new DireitoGrupo();
		direito5.setId(5L);
		direito5.setAcao(acaoNovo);
		direito5.setGrupoUsuario(grupo);
		direito5.setStatus(true);
		direito5.setTela(tela4);
		direitoGrupoBean.insertNativo(direito5);
		
		DireitoGrupo direito6 = new DireitoGrupo();
		direito6.setId(6L);
		direito6.setAcao(acaoAlterar);
		direito6.setGrupoUsuario(grupo);
		direito6.setStatus(true);
		direito6.setTela(tela4);
		direitoGrupoBean.insertNativo(direito6);
		
		Idioma idioma = new Idioma();
		idioma.setColuna("portugues");
		idioma.setNome("Português, Brasil");
		idioma.setId(1L);
		idiomaBean.insertNativo(idioma);
		
		Usuario user = new Usuario();
		user.setAtivo(true);
		user.setCelular("46 991166766");
		user.setCnpj("06284720998");
		user.setEmail("anderson.devionn@gmail.com");
		user.setFantasia("Anderson ");
		user.setRazaoSocial("Anderson Rodrigues de Almeida"); 
		user.setGrupoUsuario(grupo);
		user.setIdEmpresa(1L);
		user.setSexo("M");
		user.setSenha("admin");
		user.setObservacao("");
		user.setLogin("admin");
		user.setTelefone("46984210070");
		user.setIdioma(idioma);
		user.setId(1L);
		user.setIdUsuarioAlteracao(1L);
		pessoaBean.insertNativo(user);
		usuarioBean.insertNativo(user);
		
		Empresa empresa = new Empresa();
		empresa.setAtivo(true);
		empresa.setBairro("Fraron");
		empresa.setCaixaPostal("");
		empresa.setCep("85503355");
//		empresa.set
	}
	*/
	
}
