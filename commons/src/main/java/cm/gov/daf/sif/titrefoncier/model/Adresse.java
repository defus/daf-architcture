package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cm.gov.daf.sif.model.BaseEntity;

/**
 * Encapsule les informations de l'adresse
 *
 * @author Albert
 */
@Entity
@Table(name = "adresses")
public class Adresse extends BaseEntity  {
	
	@Column(name = "telephone")
	private Integer telephone;
	
	@Column(name = "bp")
	private Integer bp;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "adresse", length = 400)
	private String adresse;
	
	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	public Integer getBp() {
		return bp;
	}
	public void setBp(Integer bp) {
		this.bp = bp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Adresse [telephone=" + telephone + ", bp=" + bp + ", email=" + email + ", adresse=" + adresse + "]";
	}
	
}
