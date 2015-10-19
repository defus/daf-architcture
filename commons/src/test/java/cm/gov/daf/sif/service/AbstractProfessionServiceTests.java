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

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import cm.gov.daf.sif.model.Profession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public abstract class AbstractProfessionServiceTests {

	@Autowired
	protected ProfessionService professionService;

	@Test
	@Transactional
	public void shouldFindProfessionsAllWithNullLibelle() {
		Page<Profession> page = this.professionService.find(null, null);
		assertThat(page.getContent().size()).isEqualTo(15);
	}
	
	@Test
	@Transactional
	public void shouldFindProfessionsAllWithEmptyLibelle() {
		Page<Profession> page = this.professionService.find("", null);
		assertThat(page.getContent().size()).isEqualTo(15);
	}
	
	@Test
	@Transactional
	public void shouldFindProfessionsByLibelle() {
		Page<Profession> page = this.professionService.find("Eleveur", null);
		assertThat(page.getContent().isEmpty()).isFalse();
		assertThat(page.getContent().size()).isEqualTo(1);

		page = this.professionService.find("Eleveur", null);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getContent().isEmpty()).isFalse();
	}
	
	@Test
	@Transactional
	public void shouldFindProfessionsByLibelleCaseInsensitive() {
		Page<Profession> page = this.professionService.find("ELEVEUR", null);
		assertThat(page.getContent().size()).isEqualTo(1);

		page = this.professionService.find("ELEVEUR", null);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getContent().isEmpty()).isFalse();
	}

	@Test
	@Transactional
	public void shouldFindProfessionWithPagination() {
		Page<Profession> page = this.professionService.find("Eleveur", new PageRequest(0, 2));
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getSize()).isEqualTo(2);
		assertThat(page.getTotalPages()).isEqualTo(1);
		assertThat(page.getTotalElements()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void shouldFindProfession() {
		Profession profession = this.professionService.findById(1);
		assertThat(profession.getLibelle()).startsWith("Agriculteur");
	}

	@Test
	@Transactional
	public void shouldInsertProfession() {
		Page<Profession> page = this.professionService.find("commercant", new PageRequest(0, 10));
		int found = page.getContent().size();

		Profession profession = new Profession();
		profession.setLibelle("commercant");
		profession.setDescription("commercant");
		profession.setDateCreation(new Date());
		profession.setSalaireMin(BigDecimal.valueOf(9000.67));
		this.professionService.saveProfession(profession);
		assertThat(profession.getId()).isNotEqualTo(0);

		page = this.professionService.find("commercant", new PageRequest(0, 10));
		assertThat(page.getContent().size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	public void shouldUpdateProfession() {
		Profession profession = this.professionService.findById(1);
		String oldLastLibelle = profession.getLibelle();
		String newLastName = oldLastLibelle + "X";

		profession.setLibelle(newLastName);
		this.professionService.saveProfession(profession);

		// retrieving new name from database
		profession = this.professionService.findById(1);
		assertThat(profession.getLibelle()).isEqualTo(newLastName);
	}


}
