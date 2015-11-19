package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations du sommet
 *
 * @author Albert
 */
@Entity
@Table(name = "sommets")
public class Sommet extends BaseEntity{
	
	@Column(name = "num", length = 100)
	private String num;
	
	@Column(name = "coordonnee_x")
	private Integer coordonneeX;

	@Column(name = "coordonnee_y")
	private Integer coordonneeY;
	
	@ManyToOne
	@JoinColumn(name = "bornage_id")
	private Bornage bornage;

	public Sommet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCoordonneeX() {
		return coordonneeX;
	}

	public void setCoordonneeX(Integer coordonneeX) {
		this.coordonneeX = coordonneeX;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getCoordonneeY() {
		return coordonneeY;
	}

	public void setCoordonneeY(Integer coordonneeY) {
		this.coordonneeY = coordonneeY;
	}

	public Bornage getBornage() {
		return bornage;
	}

	public void setBornage(Bornage bornage) {
		this.bornage = bornage;
	}

	@Override
	public String toString() {
		return "Sommet [num=" + num + ", coordonneeX=" + coordonneeX + ", coordonneeY=" + coordonneeY + ", id=" + id
				+ "]";
	}
	 
}
