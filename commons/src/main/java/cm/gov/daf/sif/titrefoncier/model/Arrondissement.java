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
@Table(name = "arrondissements")
public class Arrondissement extends BaseEntity{

	@Column(name = "libelle", length = 200)
	@NotEmpty
	private String libelle;
	
	@Column(name = "type", length = 10)
	@NotEmpty
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "departement_id")
	private Departement departement;

	public Arrondissement() {
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

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@Override
	public String toString() {
		return "Arrondissement [libelle=" + libelle + ", type=" + type + ", id=" + id + "]";
	}

}
