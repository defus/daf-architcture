package cm.gov.daf.sif.titrefoncier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations de la levé topographique
 *
 * @author Albert
 */
@Entity
@Table(name = "leve_topos")
public class LeveTopo extends BaseEntity{

	@Column(name = "date_leve")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateLeve;
	
	@Column(name = "description", length = 200)
	@NotEmpty
	private String description;


	@ManyToOne
	@JoinColumn(name = "bornage_id")
	private Bornage bornage;
	
	public LeveTopo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDateLeve() {
		return dateLeve;
	}

	public void setDateLeve(Date dateLeve) {
		this.dateLeve = dateLeve;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bornage getBornage() {
		return bornage;
	}

	public void setBornage(Bornage bornage) {
		this.bornage = bornage;
	}

	@Override
	public String toString() {
		return "LeveTopo [dateLeve=" + dateLeve + ", description=" + description + ", id=" + id + "]";
	}
	
}
