package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur la région
 *
 * @author Albert
 */
@Entity
@Table(name = "regions")
public class Region extends BaseEntity{

	@Column(name = "libelle", length = 200)
	@NotEmpty
	private String libelle;

	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Region [libelle=" + libelle + ", id=" + id + "]";
	}
	
}
