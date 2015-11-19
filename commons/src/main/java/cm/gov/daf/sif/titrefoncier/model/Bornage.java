package cm.gov.daf.sif.titrefoncier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur le bornage
 *
 * @author Albert
 */
@Entity
@Table(name = "bornages")
public class Bornage extends BaseEntity{
	
	@Column(name = "ref_decision", length = 100)
	private String refDecision;
	
	@Column(name = "date_travaux_com")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateTravauxCom;
	
	@Column(name = "chef_service_dep", length = 200)
	private String chefServiceDep;
	
	@Column(name = "chef_village", length = 200)
	private String chefVillage;
	
	@Column(name = "superficie")
	private Integer superficie;
	
	@ManyToOne
	@JoinColumn(name = "geometre_id")
	private Geometre geometre;
	
	@Column(name = "date_proces_verbal")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateProcesVerbal;

	public Bornage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Geometre getGeometre() {
		return geometre;
	}

	public void setGeometre(Geometre geometre) {
		this.geometre = geometre;
	}

	public Integer getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}

	public String getRefDecision() {
		return refDecision;
	}

	public void setRefDecision(String refDecision) {
		this.refDecision = refDecision;
	}

	public Date getDateTravauxCom() {
		return dateTravauxCom;
	}

	public void setDateTravauxCom(Date dateTravauxCom) {
		this.dateTravauxCom = dateTravauxCom;
	}

	public String getChefServiceDep() {
		return chefServiceDep;
	}

	public void setChefServiceDep(String chefServiceDep) {
		this.chefServiceDep = chefServiceDep;
	}

	public String getChefVillage() {
		return chefVillage;
	}

	public void setChefVillage(String chefVillage) {
		this.chefVillage = chefVillage;
	}

	public Date getDateProcesVerbal() {
		return dateProcesVerbal;
	}

	public void setDateProcesVerbal(Date dateProcesVerbal) {
		this.dateProcesVerbal = dateProcesVerbal;
	}

	@Override
	public String toString() {
		return "Bornage [refDecision=" + refDecision + ", dateTravauxCom=" + dateTravauxCom + ", chefServiceDep="
				+ chefServiceDep + ", chefVillage=" + chefVillage + ", superficie=" + superficie + ", dateProcesVerbal="
				+ dateProcesVerbal + ", id=" + id + "]";
	}

}
