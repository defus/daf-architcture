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
package cm.gov.daf.sif.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cm.gov.daf.sif.model.Profession;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractProfessionServiceTests {

    @Autowired
    protected ProfessionService professionService;

    @Test
    public void shouldFindProfessionsByLibelle() {
        Collection<Profession> professions = this.professionService.findProfessionByLibelle("eleveur");
        assertThat(professions.size()).isEqualTo(1);

        professions = this.professionService.findProfessionByLibelle("eleveur");
        assertThat(professions.isEmpty());
    }

    @Test
    public void shouldFindProfession() {
    	Profession profession = this.professionService.findProfessionById(1);
        assertThat(profession.getLibelle()).startsWith("agriculteur");
    }

    @Test
    @Transactional
    public void shouldInsertProfession() {
    	Collection<Profession> professions = this.professionService.findProfessionByLibelle("commercant");
        int found = professions.size();
        
        Profession profession = new Profession();
        profession.setLibelle("commercant");
        profession.setDescription("commercant");
        profession.setDateCreation(new DateTime());
        profession.setSalaireMin(9000.67D);
        this.professionService.saveProfession(profession);
        assertThat(profession.getId().longValue()).isNotEqualTo(0);

        professions = this.professionService.findProfessionByLibelle("commercant");
        assertThat(professions.size()).isEqualTo(found + 1);
    }

    @Test
    @Transactional
    public void shouldUpdateProfession()  {
    	Profession profession = this.professionService.findProfessionById(1);
        String oldLastLibelle = profession.getLibelle();
        String newLastName = oldLastLibelle + "X";
        
        profession.setLibelle(newLastName);
        this.professionService.saveProfession(profession);

        // retrieving new name from database
        profession = this.professionService.findProfessionById(1);
        assertThat(profession.getLibelle()).isEqualTo(newLastName);
    }

//	@Test
//	public void shouldFindPetWithCorrectId() {
//	    Pet pet7 = this.clinicService.findPetById(7);
//	    assertThat(pet7.getName()).startsWith("Samantha");
//	    assertThat(pet7.getOwner().getFirstName()).isEqualTo("Jean");
//	    
//	}


}
