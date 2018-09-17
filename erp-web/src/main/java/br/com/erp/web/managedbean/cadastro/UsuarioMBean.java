package br.com.erp.web.managedbean.cadastro;


import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.com.erp.ejb.bean.UsuarioBean;
import br.com.erp.ejb.entidade.Usuario;
import br.com.erp.ejb.enumeration.EStatusUsuario;
import br.com.erp.ejb.expection.DALException;
import br.com.erp.ejb.expection.ErpException;
import br.com.erp.ejb.util.Utils;
import br.com.erp.web.managedbean.DashboardMBean;


@Named("usuarioMBean")
@ViewScoped
public class UsuarioMBean extends DashboardMBean {
	
	private static final long serialVersionUID 	= 1L;
	private static final  Logger LOGGER  		= LogManager.getLogger(UsuarioMBean.class);

	@EJB
	private UsuarioBean usuarioBean;
	
	private Usuario usuarioCad;
	private Usuario usuarioPesq;
	
	private List<Usuario> usuarioPesqList;

	private boolean isEditar;
	
	public void editar(Usuario u){
		isEditar = Boolean.TRUE;
		setUsuarioCad(u);
	}


	@PostConstruct
	public void init() {
		super.init();
		this.initFormPesquisa();
	}
	
	private void initFormPesquisa(){
		this.setUsuarioPesq(new Usuario());
	}
	
	/*FIXME
	 * retirar codigo fixo do perfil
	 */
	private void initFormUsuario(){
		this.setUsuarioCad(new Usuario());
		this.getUsuarioCad().setStatus(EStatusUsuario.ATIVO);
		this.getUsuarioCad().setDataCadastro(new Date());
		
		this.getUsuarioCad().setPerfil(new Integer(1));
		isEditar = false;
	}
	
	@Override
	public void pesquisar(){
		super.pesquisar();
		try{	
			this.setUsuarioPesqList(usuarioBean.pesquisar(this.getUsuarioPesq()));
		} catch (DALException e) {
			exibirMensagemErro("msg.cadastro.usuario.erro.pesquisar", null);
			LOGGER.error(e.getMessage());
		}
	}
	
	public void abrirPesquisa(){
		this.setUsuarioCad(null);
		pesquisar();
	}
	
	
	public void salvarUsuario(){
		this.salvar(true);
	}
	
	public void salvarUsuarioContinuar(){
		this.salvar(false);
	}
	
	private void salvar(boolean limpaForm){
		super.salvar();
		String msgTela	=	null;
		
		try {
			//Edição
			if(getUsuarioCad().getIdUsuario()!=null){
				getUsuarioCad().setDataCadastro(new Date());
				msgTela = "msg.cadastro.edicao.sucesso";
			}else{
				getUsuarioCad().setDataCadastro(new Date());
				getUsuarioCad().setSenha(Utils.convertStringToMD5(getUsuarioCad().getSenha()));
				msgTela = "msg.cadastro.adicao.sucesso";
			}
			
			setUsuarioCad(usuarioBean.save(getUsuarioCad()));
			if(limpaForm){
				this.initFormUsuario();
			}
			exibirMensagemSucesso(msgTela, null);
		} catch (DALException | ErpException e) {
			LOGGER.error(e.getMessage());
			exibirMensagemErro("erro.cadastrar", null);
		}
	}

	public void adicionarNovoUsuario(){
		this.initFormUsuario();
	}
	
	/*FIXME
	 * retirar codigo fixo do perfil
	 */
	public void limpar(){
			this.setUsuarioCad(new Usuario());
			this.getUsuarioCad().setStatus(EStatusUsuario.ATIVO);
			this.getUsuarioCad().setDataCadastro(new Date());
			this.getUsuarioCad().setPerfil(new Integer(2));
			isEditar = false;
	}
	
	public void excluirUsuario(){
		this.excluir(this.getUsuarioCad());
		this.initFormUsuario();
	}
	
	public void excluir(Usuario u){
		try {
			this.usuarioBean.removeById(u.getIdUsuario());
			exibirMensagemSucesso("msg.cadastro.usuario.exclusao", null);
			pesquisar();
		} catch (DALException e) {
			exibirMensagemErro("msg.cadastro.usuario.erro.exclusao", null);
			LOGGER.error(e.getMessage());
		}
	}


	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}


	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}


	public Usuario getUsuarioCad() {
		return usuarioCad;
	}


	public void setUsuarioCad(Usuario usuario) {
		this.usuarioCad = usuario;
	}


	public Usuario getUsuarioPesq() {
		return usuarioPesq;
	}


	public void setUsuarioPesq(Usuario usuarioPesq) {
		this.usuarioPesq = usuarioPesq;
	}


	public List<Usuario> getUsuarioPesqList() {
		return usuarioPesqList;
	}


	public void setUsuarioPesqList(List<Usuario> usuarioPesqList) {
		this.usuarioPesqList = usuarioPesqList;
	}
	
}