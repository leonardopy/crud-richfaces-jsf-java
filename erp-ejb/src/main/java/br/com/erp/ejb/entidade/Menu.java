package br.com.erp.ejb.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.erp.ejb.enumeration.EStatusMenu;

@Entity
@Table(name="erp_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = -1L;

	@Id
    @Column(name="id_menu", nullable=false, length=8)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMenu;
	
	@Column(name="nome", nullable= false, length = 50)
	private String nome;
	
	@Column(name="icone", nullable= true, length = 10)
	private String icone;
	
	@Column(name="path", nullable= false, length = 50)
	private String path;
	
	@Column(name="status", nullable= false, length = 1)
	@Enumerated(EnumType.ORDINAL)
	private EStatusMenu status;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_menu_pai", referencedColumnName="id_menu", insertable=false, updatable=false)
	private List<Menu> menuFilhos;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_menu_pai", referencedColumnName="id_menu", insertable=false, updatable=false)
	private Menu menuPai;
	
	@Column(name="tipo_acesso", nullable= false, length = 1)
	private Integer tipoAcesso;

	
	@Column(name="order", nullable= true)
	private Integer order;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public Integer getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(Integer tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public EStatusMenu getStatus() {
		return status;
	}

	public void setStatus(EStatusMenu status) {
		this.status = status;
	}
	
	public List<Menu> getMenuFilhos() {
		return menuFilhos;
	}

	public void setMenuFilhos(List<Menu> menuFilhos) {
		this.menuFilhos = menuFilhos;
	}

	public Menu getMenuPai() {
		return menuPai;
	}

	public void setMenuPai(Menu menuPai) {
		this.menuPai = menuPai;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMenu == null) ? 0 : idMenu.hashCode());
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
		Menu other = (Menu) obj;
		if (idMenu == null) {
			if (other.idMenu != null)
				return false;
		} else if (!idMenu.equals(other.idMenu))
			return false;
		return true;
	}

}