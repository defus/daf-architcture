package cm.gov.daf.sif.titrefoncier.model;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

import cm.gov.daf.sif.model.BaseEntity;

public class DossierMorcellement extends BaseEntity{

	@Column(name = "num_tf", length = 100)
	@NotEmpty
	private String numTf;
	
	@Column(name = "num_tf_morc", length = 100)
	@NotEmpty
	private String numTfMorc;
	
	@Column(name = "num_dm", length = 100)
	@NotEmpty
	private String numDm;
	
	@Column(name = "requisition", length = 200)
	@NotEmpty
	private String requisition;
}
