package br.com.erp.web.managedbean.cadastro;


import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.com.erp.ejb.bean.BlogBean;
import br.com.erp.ejb.entidade.Blog;
import br.com.erp.ejb.entidade.Usuario;
import br.com.erp.ejb.expection.DALException;
import br.com.erp.web.managedbean.DashboardMBean;


@Named("blogMBean")
@ViewScoped
public class BlogMBean extends DashboardMBean {
	
	private static final long serialVersionUID 	= 1L;
	private static final  Logger LOGGER  		= LogManager.getLogger(BlogMBean.class);

	@EJB
	private BlogBean blogBean;
	
	private Blog blog;
	private Blog blogPesq;
	
	private List<Blog> blogPesqList;
	private List<Blog> blogApresentacaoList;

	private boolean isEditar;
	
	public void editar(Blog blog){
		isEditar = Boolean.TRUE;
		setBlog(blog);
	}

	public List<Blog> getBlogApresentacaoList() {
		return blogApresentacaoList;
	}

	public void setBlogApresentacaoList(List<Blog> blogApresentacaoList) {
		this.blogApresentacaoList = blogApresentacaoList;
	}

	public BlogBean getBlogBean() {
		return blogBean;
	}

	public void setBlogBean(BlogBean blogBean) {
		this.blogBean = blogBean;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Blog getBlogPesq() {
		return blogPesq;
	}

	public void setBlogPesq(Blog blogPesq) {
		this.blogPesq = blogPesq;
	}
	
	public List<Blog> getBlogPesqList() {
		return blogPesqList;
	}

	public void setBlogPesqList(List<Blog> blogPesqList) {
		this.blogPesqList = blogPesqList;
	}

	@PostConstruct
	public void init() {
		super.init();
		this.initFormPesquisa();
		this.initFormApresentacao();
	}
	
	private void initFormPesquisa(){
		this.setBlogPesq(new Blog());
		this.getBlogPesq().setUsuario(new Usuario());
	}
	
	private void initFormApresentacao(){
		try {
			this.setBlogApresentacaoList(blogBean.pesquisar(this.getBlogPesq()));
		} catch (DALException e) {
			exibirMensagemErro("msg.cadastro.blog.erro.pesquisar", null);
			LOGGER.error(e.getMessage());
		}
	}
	
	private void initFormBlog(){
		this.setBlog(new Blog());
		this.getBlog().setUsuario(this.getUsuarioLogado());
		this.getBlog().setDataCadastro(new Date());
		isEditar = false;
	}
	
	@Override
	public void pesquisar(){
		super.pesquisar();
		try{	
			this.setBlogPesqList(blogBean.pesquisar(this.getBlogPesq()));
		} catch (DALException e) {
			exibirMensagemErro("msg.cadastro.blog.erro.pesquisar", null);
			LOGGER.error(e.getMessage());
		}
	}
	
	public void abrirPesquisa(){
		this.setBlog(null);
		pesquisar();
	}
	
	
	public void salvarBlog(){
		this.salvar(true);
	}
	
	public void salvarBlogContinuar(){
		this.salvar(false);
	}
	
	private void salvar(boolean limpaForm){
		super.salvar();
		String msgTela	=	null;
		
		try {
			//Edição
			if(getBlog().getIdBlog()!=null){
				getBlog().setDataCadastro(new Date());
				msgTela = "msg.cadastro.edicao.sucesso";
			}else{
				getBlog().setDataCadastro(new Date());
				msgTela = "msg.cadastro.adicao.sucesso";
			}
			
			setBlog(blogBean.save(getBlog()));
			if(limpaForm){
				this.initFormBlog();
			}
			exibirMensagemSucesso(msgTela, null);
		} catch (DALException e) {
			LOGGER.error(e.getMessage());
			exibirMensagemErro("erro.cadastrar", null);
		}
	}

	public void adicionarNovoBlog(){
		this.initFormBlog();
	}
	
	public void excluirBlog(){
		this.excluir(this.getBlog());
		this.initFormBlog();
	}
	
	public void excluir(Blog blog){
		try {
			this.blogBean.removeById(blog.getIdBlog());
			exibirMensagemSucesso("msg.cadastro.blog.exclusao", null);
			pesquisar();
		} catch (DALException e) {
			exibirMensagemErro("msg.cadastro.blog.erro.exclusao", null);
			LOGGER.error(e.getMessage());
		}
	}
	
}