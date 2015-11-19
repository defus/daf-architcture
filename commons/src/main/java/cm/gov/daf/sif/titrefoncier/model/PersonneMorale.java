package cm.gov.daf.sif.titrefoncier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Encapsule les informations d'une personne morale
 *
 * @author Albert
 */
@Entity
@Table(name = "proprietaires")
@DiscriminatorValue(value="M")
public class PersonneMorale extends Proprietaire{
	
	@Column(name = "immatriculation", length = 200)
	@NotEmpty
	private String immatriculation;
	
	@Column(name = "raisonSocial", length = 200)
	@NotEmpty
	private String raisonSociale;
	
	@Column(name = "date_creation")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateCretaion;
	
	@ManyToOne
	@JoinColumn(name = "secteur_activite_id")
	private SecteurActivite secteurActivite;

	public PersonneMorale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public Date getDateCretaion() {
		return dateCretaion;
	}

	public void setDateCretaion(Date dateCretaion) {
		this.dateCretaion = dateCretaion;
	}

	public SecteurActivite getSecteurActivite() {
		return secteurActivite;
	}

	public void setSecteurActivite(SecteurActivite secteurActivite) {
		this.secteurActivite = secteurActivite;
	}

	@Override
	public String toString() {
		return "PersonneMorale [immatriculation=" + immatriculation + ", raisonSociale=" + raisonSociale
				+ ", dateCretaion=" + dateCretaion + ", secteurActivite=" + secteurActivite + ", id=" + id + "]";
	}

}
