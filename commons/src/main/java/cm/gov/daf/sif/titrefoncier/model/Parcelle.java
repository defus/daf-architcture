package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur la parcelle
 *
 * @author Albert
 */
@Entity
@Table(name = "parcelles")
public class Parcelle extends BaseEntity {

	@Column(name = "num", length = 100)
	@NotEmpty
	private String num;
	
	@Column(name = "type", length = 100)
	@NotEmpty
	private String type;
	
	@Column(name = "superficie")
	@NotEmpty
	private Integer superficie;
	
	@ManyToOne
	@JoinColumn(name = "lot_id")
	private Lot lot;
	
	@ManyToOne
	@JoinColumn(name = "proprietaire_id")
	@NotEmpty
	private Proprietaire proprietaire;

	public Parcelle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public Proprietaire getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	@Override
	public String toString() {
		return "Parcelle [num=" + num + ", superficie=" + superficie + ", id=" + id + "]";
	}

}
