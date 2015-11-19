package cm.gov.daf.sif.titrefoncier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur la requisition
 *
 * @author Albert
 */
@Entity
@Table(name = "requisitions")
public class Requisition extends BaseEntity {
	
	@Column(name = "num", length = 100)
	private String num;

	@ManyToOne
	@JoinColumn(name = "proprietaire_id")
	private Proprietaire proprietaire;
	
	@ManyToOne
	@JoinColumn(name = "departement_id")
	private Departement departement;
	
	@Column(name = "date_demande")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateDemande;
	
	@OneToOne
	@JoinColumn(name = "bornage_id")
	private Bornage bornage;

	public Requisition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proprietaire getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Bornage getBornage() {
		return bornage;
	}

	public void setBornage(Bornage bornage) {
		this.bornage = bornage;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Override
	public String toString() {
		return "Requisition [num=" + num + ", dateDemande=" + dateDemande + ", id=" + id + "]";
	}
	
}
