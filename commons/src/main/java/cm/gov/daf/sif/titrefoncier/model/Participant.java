package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Encapsule les informations du participant du proces verbal
 *
 * @author Albert
 */
@Entity
@Table(name = "individus")
@DiscriminatorValue(value="P")
public class Participant extends Riverian{
	
	@Column(name = "mention", length = 100)
	private String mention;
	
	public Participant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	@Override
	public String toString() {
		return "Participant [mention=" + mention + ", nom=" + nom + ", prenoms=" + prenoms + ", id=" + id + "]";
	}
	

}
