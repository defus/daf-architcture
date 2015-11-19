package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations du riverain
 *
 * @author Albert
 */
@Entity
@Table(name = "individus")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="discriminator",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="R")
public class Riverian extends BaseEntity{
	
	@Column(name = "nom", length = 100)
	protected String nom;
	
	@Column(name = "prenoms", length = 200)
	protected String prenoms;
	
	@Column(name = "nom_prenoms", length = 400)
	protected String nomPrenoms;

	@ManyToOne
	@JoinColumn(name = "bornage_id")
	protected Bornage bornage;

	public Riverian() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getNomPrenoms() {
		return nomPrenoms;
	}

	public void setNomPrenoms(String nomPrenoms) {
		this.nomPrenoms = nomPrenoms;
	}

	public Bornage getBornage() {
		return bornage;
	}

	public void setBornage(Bornage bornage) {
		this.bornage = bornage;
	}

	@Override
	public String toString() {
		return "Riverian [nom=" + nom + ", prenoms=" + prenoms + ", id=" + id + "]";
	}

}
