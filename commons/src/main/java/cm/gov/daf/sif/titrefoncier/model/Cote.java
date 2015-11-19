
package cm.gov.daf.sif.titrefoncier.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur le côté
 *
 * @author Albert
 */
@Entity
@Table(name = "cotes")
public class Cote extends BaseEntity{
	
	@Column(name = "longueur")
	@NotNull
	private BigDecimal longueur;
	
	@Column(name = "desc_limite", length = 1000)
	private String desc_limite;

	@ManyToOne
	@JoinColumn(name = "sommet_a")
	private Sommet sommetA;

	@ManyToOne
	@JoinColumn(name = "sommet_b")
	private Sommet sommetB;

	@ManyToOne
	@JoinColumn(name = "bornage_id")
	private Bornage bornage;

	public Cote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getLongueur() {
		return longueur;
	}

	public void setLongueur(BigDecimal longueur) {
		this.longueur = longueur;
	}

	public String getDesc_limite() {
		return desc_limite;
	}

	public void setDesc_limite(String desc_limite) {
		this.desc_limite = desc_limite;
	}

	public Sommet getSommetA() {
		return sommetA;
	}

	public void setSommetA(Sommet sommetA) {
		this.sommetA = sommetA;
	}

	public Sommet getSommetB() {
		return sommetB;
	}

	public void setSommetB(Sommet sommetB) {
		this.sommetB = sommetB;
	}

	public Bornage getBornage() {
		return bornage;
	}

	public void setBornage(Bornage bornage) {
		this.bornage = bornage;
	}

	@Override
	public String toString() {
		return "Cote [longueur=" + longueur + ", desc_limite=" + desc_limite + ", id=" + id + "]";
	}
	
}
