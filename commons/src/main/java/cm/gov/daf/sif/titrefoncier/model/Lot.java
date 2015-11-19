package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations sur le lot
 *
 * @author Albert
 */
@Entity
@Table(name = "lots")
public class Lot extends BaseEntity{

	@Column(name = "num", length = 200)
	@NotEmpty
	private String num;
	
	@Column(name = "superficie")
	@NotEmpty
	private Integer superficie;
	
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	public Lot() {
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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "Lot [num=" + num + ", superficie=" + superficie + ", id=" + id + "]";
	}

}
