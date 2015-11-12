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
		return "Geometre [id=" + id + "]";
	}

	
}
