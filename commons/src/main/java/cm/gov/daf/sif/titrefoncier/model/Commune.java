package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur l'arrondissement
 *
 * @author Albert
 */
@Entity
@Table(name = "communes")
public class Commune extends BaseEntity{

	@Column(name = "libelle", length = 200)
	@NotEmpty
	private String libelle;
	
	@Column(name = "type", length = 10)
	@NotEmpty
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "arrondissement_id")
	protected Arrondissement arrondissement;

	public Commune() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Arrondissement getArrondissement() {
		return arrondissement;
	}

	public void setArrondissement(Arrondissement arrondissement) {
		this.arrondissement = arrondissement;
	}

	@Override
	public String toString() {
		return "Commune [libelle=" + libelle + ", type=" + type + ", arrondissement=" + arrondissement + ", id=" + id
				+ "]";
	}

}
