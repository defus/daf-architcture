package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur le pays
 *
 * @author Albert
 */
@Entity
@Table(name = "pays")
public class Pays extends BaseEntity {

	@Column(name = "nom", length = 200)
	@NotEmpty
	private String nom;
	
	@Column(name = "nationalite", length = 200)
	@NotEmpty
	private String nationalite;

	public Pays() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	@Override
	public String toString() {
		return "Pays [nom=" + nom + ", nationalite=" + nationalite + ", id=" + id + "]";
	}
	
}
