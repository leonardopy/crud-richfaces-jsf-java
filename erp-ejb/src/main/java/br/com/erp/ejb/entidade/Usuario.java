package br.com.erp.ejb.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.erp.ejb.enumeration.EPerfilUsuario;
import br.com.erp.ejb.enumeration.EStatusUsuario;

@Entity
@Table(name="erp_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1L;

	@Id
    @Column(name="id_usuario", nullable=false, length=8)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column(name="login", nullable= false, length = 50)
	private String login;
	
	@Column(name="senha", nullable= false, length = 32)
	private String senha;
	
	@Column(name="status", nullable= false, length = 1)
	@Enumerated(EnumType.ORDINAL)
	private EStatusUsuario status;
	
	@Column(name="tipo", nullable= false, length = 1)
	private Integer perfil;
	
	@Column(name = "dt_cadastro", nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Transient
	private String perfilUsuario;
	
	
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EStatusUsuario getStatus() {
		return status;
	}

	public void setStatus(EStatusUsuario status) {
		this.status = status;
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
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	public String getPerfilUsuario() {
		if(this.perfil!=null){
			if(this.perfil.equals(1))
				perfilUsuario =EPerfilUsuario.ADMIN.getDescricao(); 
			else
				perfilUsuario =EPerfilUsuario.PUBLICADOR.getDescricao();
		}
		return perfilUsuario;
	}

	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

}