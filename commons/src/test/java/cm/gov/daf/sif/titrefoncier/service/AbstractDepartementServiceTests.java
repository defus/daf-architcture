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
package cm.gov.daf.sif.titrefoncier.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import cm.gov.daf.sif.titrefoncier.model.Departement;
import cm.gov.daf.sif.titrefoncier.model.Region;

public abstract class AbstractDepartementServiceTests {

	@Autowired
	protected DepartementService departementService;

	@Test
	@Transactional
	public void shouldFindDepartementsAllWithNullLibelle() {
		Page<Departement> page = this.departementService.find(null, null);
		assertThat(page.getContent().size()).isEqualTo(10);
	}
	
	@Test
	@Transactional
	public void shouldFindDepartementsAllWithEmptyLibelle() {
		Page<Departement> page = this.departementService.find("", null);
		assertThat(page.getContent().size()).isEqualTo(10);
	}
	
	@Test
	@Transactional
	public void shouldFindDepartementsByLibelle() {
		Page<Departement> page = this.departementService.find("Vina", null);
		assertThat(page.getContent().isEmpty()).isFalse();
		assertThat(page.getContent().size()).isEqualTo(1);

		page = this.departementService.find("Vina", null);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getContent().isEmpty()).isFalse();
	}
	
	@Test
	@Transactional
	public void shouldFindDepartementsByLibelleCaseInsensitive() {
		Page<Departement> page = this.departementService.find("VINA", null);
		assertThat(page.getContent().size()).isEqualTo(1);

		page = this.departementService.find("VINA", null);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getContent().isEmpty()).isFalse();
	}

	@Test
	@Transactional
	public void shouldFindDepartementWithPagination() {
		Page<Departement> page = this.departementService.find("Vina", new PageRequest(0, 2));
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getSize()).isEqualTo(2);
		assertThat(page.getTotalPages()).isEqualTo(1);
		assertThat(page.getTotalElements()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void shouldFindDepartement() {
		Departement departement = this.departementService.findById(5);
		assertThat(departement.getLibelle()).startsWith("Vina");
	}

	@Test
	@Transactional
	public void shouldInsertDepartement() {
		Page<Departement> page = this.departementService.find("Kadey", new PageRequest(0, 10));
		int found = page.getContent().size();

		Departement departement = new Departement();
		departement.setLibelle("Kadey");
		this.departementService.save(departement);
		assertThat(departement.getId()).isNotEqualTo(0);

		page = this.departementService.find("Kadey", new PageRequest(0, 10));
		assertThat(page.getContent().size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	public void shouldUpdateDepartement() {
		Departement departement = this.departementService.findById(1);
		String oldLastLibelle = departement.getLibelle();
		String newLastName = oldLastLibelle + "X";

		departement.setLibelle(newLastName);
		this.departementService.save(departement);

		// retrieving new name from database
		departement = this.departementService.findById(1);
		assertThat(departement.getLibelle()).isEqualTo(newLastName);
	}


}
