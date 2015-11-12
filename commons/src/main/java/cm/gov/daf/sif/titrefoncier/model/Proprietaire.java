package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

@Entity
@Table(name = "proprietaires")
public class Proprietaire extends BaseEntity {
	
	@Column(name = "code", length = 50)
	@NotEmpty
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "pays_id")
	@NotEmpty
	private Pays pays;
	
	@OneToOne
	@JoinColumn(name = "adresse_id")
	@NotEmpty
	private Adresse adresse;

	public Proprietaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@Override
	public String toString() {
		return "Proprietaire [code=" + code + ", pays=" + pays + ", adresse=" + adresse + ", id=" + id + "]";
	}
	
}
