package br.com.erp.web.managedbean;


import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.com.erp.ejb.entidade.Usuario;
import br.com.erp.ejb.enumeration.EDestino;
import br.com.erp.ejb.enumeration.ESessionKeys;
import br.com.erp.ejb.infra.PoolString;
import br.com.erp.web.util.SessionManager;
import br.com.erp.web.util.Utils;

@Named("defaultMBean")
@ViewScoped
public class DefaultMBean implements Serializable {
	
	private static final long serialVersionUID 	= 1L;
	protected static final  Logger LOGGER  		= LogManager.getLogger(DefaultMBean.class);
	private ResourceBundle bundleMessage;

	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
	
	}
	
	
	
	
	
	private void exibirMensagem(FacesMessage.Severity tipo, String bundleKeyMessage, String details,  Object[] paramsMsg) {
		String msg = null;	
		if(paramsMsg!=null && paramsMsg.length>0){
			 MessageFormat   messageFormat = new MessageFormat(bundleKeyMessage);
			 msg = messageFormat.format(paramsMsg);
		}else{
			msg = bundleKeyMessage;
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, msg, details));
	}	
	
	protected void exibirMensagemSucesso(String bundleMessage,Object[] paramsMsg) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, this.getBundleMessage().getString(bundleMessage), PoolString.SUCESSO, paramsMsg);
	}
		
	protected void exibirMensagemAviso(String bundleMessage, Object[] paramsMsg) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, this.getBundleMessage().getString(bundleMessage), PoolString.AVISO, paramsMsg);
	}
	
	protected void exibirMensagemErro(String bundleMessage, Object[] paramsMsg) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, this.getBundleMessage().getString(bundleMessage), PoolString.ERRO, paramsMsg);
	}
	
	public boolean isPossuiFacesMessage() {
        Iterator<FacesMessage> iterator = FacesContext.getCurrentInstance().getMessages();
        return iterator.hasNext();
    }
	
	public String getServerContextPath() {
		return Utils.getServerContextPath();
	}
	
	public String getServerContextPathWithoutBar() {
		return Utils.getServerContextPath().substring(0, Utils.getServerContextPath().length()-1);
	}
	
	public ResourceBundle getBundleMessage() {
      if (bundleMessage == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            bundleMessage = context.getApplication().getResourceBundle(context, "msg");
        }
        return bundleMessage;
	}
	
	public Usuario getUsuarioLogado() {
		if(usuarioLogado==null){
			usuarioLogado = (Usuario) SessionManager.restoreAttribute(ESessionKeys.USUARIO);
		}
		return usuarioLogado;
	}
	
	public String logout(){
		if(Utils.getSession()!=null){
			SessionManager.remove(ESessionKeys.USUARIO);
		}
		return EDestino.LOGIN.getDestino();
	}
	
	//Ações de tela
	protected void limpar(){}
	protected void pesquisar(){}
	protected void salvar(){}

	
}