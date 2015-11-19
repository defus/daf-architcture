package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Encapsule les informations sur le géomètre
 *
 * @author Albert
 */
@Entity
@Table(name = "geometres")
public class Geometre extends PersonnePhysique{

	public Geometre() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Geometre [cni=" + cni + ", genre=" + genre + ", status=" + status + ", nom=" + nom + ", prenoms="
				+ prenoms + ", dateNaissance=" + dateNaissance + ", lieuNaissance=" + lieuNaissance + ", pere=" + pere
				+ ", mere=" + mere + ", conjoint=" + conjoint + ", nbrEnfant=" + nbrEnfant + ", cellulaire="
				+ cellulaire + ", profession=" + profession + ", code=" + code + ", pays=" + pays + ", adresse="
				+ adresse + ", id=" + id + "]";
	}
	
}
