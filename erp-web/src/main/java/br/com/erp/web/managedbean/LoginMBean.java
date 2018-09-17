package br.com.erp.web.managedbean;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.com.erp.ejb.bean.UsuarioBean;
import br.com.erp.ejb.entidade.Usuario;
import br.com.erp.ejb.enumeration.EDestino;
import br.com.erp.ejb.enumeration.ESessionKeys;
import br.com.erp.ejb.expection.DALException;
import br.com.erp.ejb.expection.ErpException;
import br.com.erp.ejb.util.Utils;
import br.com.erp.web.util.SessionManager;


@Named("loginMBean")
@ViewScoped
public class LoginMBean extends DefaultMBean {
	
	private static final long serialVersionUID = 1L;
	private static final  Logger LOGGER  = LogManager.getLogger(LoginMBean.class);

	
	@EJB
	private UsuarioBean usuarioBean;
	
	private String senha, login;
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public String logarUsuario(){
		Usuario usuario	=	null;
		try {
			if(StringUtils.isEmpty(this.getLogin()) || StringUtils.isEmpty(this.getSenha())){
				this.exibirMensagemAviso("msg.login.senha.embranco", null);
			}else{
				usuario	=	usuarioBean.getUsuarioLogin(this.getLogin(), Utils.convertStringToMD5(this.getSenha()));
				if(usuario!=null){
					SessionManager.putAttribute(ESessionKeys.USUARIO, usuario);
					return EDestino.DASHBOARD.getDestino();
				}else{
					exibirMensagemAviso("msg.login.senha.invalido", null);
				}
			}
		} catch (DALException | ErpException e) {
			LOGGER.error(e.getMessage());
			this.exibirMensagemErro("erro.usuario.login", null);
		}
		return null;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


}