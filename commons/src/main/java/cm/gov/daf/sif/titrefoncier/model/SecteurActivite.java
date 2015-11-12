package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur le secteur d'activité
 *
 * @author Albert
 */
@Entity
@Table(name = "secteurs_activites")
public class SecteurActivite extends BaseEntity {
	
	@Column(name = "libelle", length = 200)
	@NotEmpty
	private String libelle;

	public SecteurActivite() {
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
		return "SecteurActivite [libelle=" + libelle + ", id=" + id + "]";
	}
	
}
