package cm.gov.daf.sif.titrefoncier.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

@Entity
@Table(name = "proprietaires")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="discriminator",
    discriminatorType=DiscriminatorType.STRING
)
public class Proprietaire extends BaseEntity {
	
	@Column(name = "code", length = 50)
	@NotEmpty
	protected String code;
	
	@ManyToOne
	@JoinColumn(name = "pays_id")
	@NotEmpty
	protected Pays pays;
	
	@OneToOne
	@JoinColumn(name = "adresse_id")
	@NotEmpty
	protected Adresse adresse;

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
