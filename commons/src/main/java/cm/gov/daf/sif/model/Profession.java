/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cm.gov.daf.sif.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Albert
 */
@Entity
@Table(name = "professions")
public class Profession extends BaseEntity {

    @Column(name = "libelle")
    @NotEmpty
    protected String libelle;

    @Column(name = "description")
    @NotEmpty
    protected String description;
    
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dateCreation;
    
    @Column(name = "salaire_min")
    @NotNull
    protected Double salaireMin;
    
    @ManyToOne
	@JoinColumn(name = "type_profession_id")
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

	public Double getSalaireMin() {
		return salaireMin;
	}

	public void setSalaireMin(Double salaireMin) {
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

	                .append("id", this.getId())
	                .append("new", this.isNew())
	                .append("libelle", this.getLibelle())
	                .append("description", this.getDescription())
	                .append("dateCreation", this.getDateCreation())
	                .append("salaireMin", this.getSalaireMin())
	                .toString();
	    }
}
