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
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cm.gov.daf.sif.model.TypeProfession;

import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractTypeProfessionServiceTests {

    @Autowired
    protected TypeProfessionService typeProfessionService;

    @Test
    public void shouldFindTypeProfessionsByLibelle() {
        Collection<TypeProfession> professions = this.typeProfessionService.findTypeProfessionByLibelle("ingenieur");
        assertThat(professions.size()).isEqualTo(1);

        professions = this.typeProfessionService.findTypeProfessionByLibelle("ingenieurs");
        assertThat(professions.isEmpty());
    }

    @Test
    public void shouldFindTypeProfession() {
    	TypeProfession typeProfession = this.typeProfessionService.findTypeProfessionById(1);
        assertThat(typeProfession.getLibelle()).startsWith("ingenieur");
    }

    @Test
    @Transactional
    public void shouldInsertTypeProfession() {
    	Collection<TypeProfession> professions = this.typeProfessionService.findTypeProfessionByLibelle("consultant");
        int found = professions.size();
        
        TypeProfession typeProfession = new TypeProfession();
        typeProfession.setLibelle("consultant");
        typeProfession.setDescription("consultant");
        this.typeProfessionService.saveTypeProfession(typeProfession);
        assertThat(typeProfession.getId().longValue()).isNotEqualTo(0);

        professions = this.typeProfessionService.findTypeProfessionByLibelle("consultant");
        assertThat(professions.size()).isEqualTo(found + 1);
    }

    @Test
    @Transactional
    public void shouldUpdateTypeProfession()  {
    	TypeProfession typeProfession = this.typeProfessionService.findTypeProfessionById(1);
        String oldLastLibelle = typeProfession.getLibelle();
        String newLastName = oldLastLibelle + "X";
        
        typeProfession.setLibelle(newLastName);
        this.typeProfessionService.saveTypeProfession(typeProfession);

        // retrieving new name from database
        typeProfession = this.typeProfessionService.findTypeProfessionById(1);
        assertThat(typeProfession.getLibelle()).isEqualTo(newLastName);
    }

//	@Test
//	public void shouldFindPetWithCorrectId() {
//	    Pet pet7 = this.clinicService.findPetById(7);
//	    assertThat(pet7.getName()).startsWith("Samantha");
//	    assertThat(pet7.getOwner().getFirstName()).isEqualTo("Jean");
//	    
//	}


}
