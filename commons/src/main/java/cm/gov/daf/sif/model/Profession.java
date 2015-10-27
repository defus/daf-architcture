package cm.gov.daf.sif.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Index;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Encapsule les informations de la profession
 *
 * @author Albert
 */
@Entity
@Table(name = "professions")
public class Profession extends BaseEntity {

	@Column(name = "libelle", length = 200)
	@NotEmpty
	private String libelle;

	@Column(name = "description", length = 1000)
	@NotEmpty
	private String description;

	@Column(name = "date_creation")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateCreation;

	@Column(name = "salaire_min")
	@NotNull
	private BigDecimal salaireMin;

	@ManyToOne
	@JoinColumn(name = "type_profession_id")
	// @Index(name = "ix_type_profession", columnList = "type_profession_id")
	private TypeProfession typeProfession;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public BigDecimal getSalaireMin() {
		return salaireMin;
	}

	public void setSalaireMin(BigDecimal salaireMin) {
		this.salaireMin = salaireMin;
	}

	public TypeProfession getTypeProfession() {
		return typeProfession;
	}

	public void setTypeProfession(TypeProfession typeProfession) {
		this.typeProfession = typeProfession;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("libelle", this.getLibelle())
				.append("description", this.getDescription()).append("dateCreation", this.getDateCreation())
				.append("salaireMin", this.getSalaireMin()).toString();
	}
}
