package br.com.erp.ejb.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="blog")
public class Blog implements Serializable {

	private static final long serialVersionUID = -1L;

	@Id
    @Column(name="id_blog", nullable=false, length=8)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBlog;
	
	@Column(name="titulo", nullable= false)
	private String titulo;
	
	@Column(name="descricao", nullable= false)
	private String descricao;
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="id_usuario", referencedColumnName="id_usuario", nullable=false)
	private Usuario usuario;
		
	@Column(name = "dt_cadastro", nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	public Long getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(Long idBlog) {
		this.idBlog = idBlog;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBlog == null) ? 0 : idBlog.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (idBlog == null) {
			if (other.idBlog != null)
				return false;
		} else if (!idBlog.equals(other.idBlog))
			return false;
		return true;
	}

	

	
}