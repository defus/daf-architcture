package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur le département
 *
 * @author Albert
 */
@Entity
@Table(name = "departements")
public class Departement extends BaseEntity{

	@Column(name = "libelle", length = 200)
	@NotEmpty
	private String libelle;
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Region [libelle=" + libelle + ", id=" + id + "]";
	}
	
}
