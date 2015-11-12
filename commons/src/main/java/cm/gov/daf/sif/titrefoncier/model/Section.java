package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur la section
 *
 * @author Albert
 */
@Entity
@Table(name = "sections")
public class Section extends BaseEntity{

	@Column(name = "num", length = 200)
	@NotEmpty
	private String num;
	
	@Column(name = "superficie")
	@NotEmpty
	private Integer superficie;
	
	@ManyToOne
	@JoinColumn(name = "commune_id")
	protected Commune commune;

	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}

	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	@Override
	public String toString() {
		return "Section [num=" + num + ", superficie=" + superficie + ", id=" + id + "]";
	}

}
