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

import cm.gov.daf.sif.model.Profession;

/**
 * Encapsule les informations d'une personne physique
 *
 * @author Albert
 */
@Entity
@Table(name = "proprietaires")
@DiscriminatorValue(value="P")
public class PersonnePhysique extends Proprietaire {

	@Column(name = "cni", length = 200)
	@NotEmpty
	protected String cni;
	
	@Column(name = "genre", length = 1)
	@NotEmpty
	protected String genre;
	
	@Column(name = "status", length = 4)
	@NotEmpty
	protected String status;
	
	@Column(name = "nom", length = 100)
	@NotEmpty
	protected String nom;
	
	@Column(name = "prenoms", length = 200)
	@NotEmpty
	protected String prenoms;
	
	@Column(name = "nom_Prenoms", length = 400)
	@NotEmpty
	protected String nomPrenoms;
	
	@Column(name = "date_naissance")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	protected Date dateNaissance;
	
	@Column(name = "lieu_naissance", length = 200)
	@NotEmpty
	protected String lieuNaissance;
	
	@Column(name = "pere", length = 200)
	protected String pere;
	
	@Column(name = "mere", length = 200)
	protected String mere;
	
	@Column(name = "conjoint", length = 200)
	protected String conjoint;
	
	@Column(name = "nbr_enfant")
	@NotEmpty
	protected Integer nbrEnfant;
	
	@Column(name = "cellulaire")
	protected Integer cellulaire;
	
	@ManyToOne
	@JoinColumn(name = "profession_id")
	protected Profession profession;
	
	public PersonnePhysique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCni() {
		return cni;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getPere() {
		return pere;
	}

	public void setPere(String pere) {
		this.pere = pere;
	}

	public String getMere() {
		return mere;
	}

	public void setMere(String mere) {
		this.mere = mere;
	}

	public String getConjoint() {
		return conjoint;
	}

	public void setConjoint(String conjoint) {
		this.conjoint = conjoint;
	}

	public Integer getNbrEnfant() {
		return nbrEnfant;
	}

	public void setNbrEnfant(Integer nbrEnfant) {
		this.nbrEnfant = nbrEnfant;
	}

	public Integer getCellulaire() {
		return cellulaire;
	}

	public void setCellulaire(Integer cellulaire) {
		this.cellulaire = cellulaire;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
	@Override
	public String toString() {
		return "PersonnePhysique [cni=" + cni + ", genre=" + genre + ", status=" + status + ", nom=" + nom
				+ ", prenoms=" + prenoms + ", dateNaissance=" + dateNaissance + ", lieuNaissance=" + lieuNaissance
				+ ", pere=" + pere + ", mere=" + mere + ", conjoint=" + conjoint + ", nbrEnfant=" + nbrEnfant
				+ ", cellulaire=" + cellulaire + ", profession=" + profession + ", id=" + id
				+ "]";
	}

}
