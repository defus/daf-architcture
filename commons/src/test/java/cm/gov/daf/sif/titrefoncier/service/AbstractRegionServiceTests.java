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

import cm.gov.daf.sif.titrefoncier.model.Region;

public abstract class AbstractRegionServiceTests {

	@Autowired
	protected RegionService regionService;

	@Test
	@Transactional
	public void shouldFindRegionsAllWithNullLibelle() {
		Page<Region> page = this.regionService.find(null, null);
		assertThat(page.getContent().size()).isEqualTo(10);
	}
	
	@Test
	@Transactional
	public void shouldFindRegionsAllWithEmptyLibelle() {
		Page<Region> page = this.regionService.find("", null);
		assertThat(page.getContent().size()).isEqualTo(10);
	}
	
	@Test
	@Transactional
	public void shouldFindRegionsByLibelle() {
		Page<Region> page = this.regionService.find("Centre", null);
		assertThat(page.getContent().isEmpty()).isFalse();
		assertThat(page.getContent().size()).isEqualTo(1);

		page = this.regionService.find("Centre", null);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getContent().isEmpty()).isFalse();
	}
	
	@Test
	@Transactional
	public void shouldFindRegionsByLibelleCaseInsensitive() {
		Page<Region> page = this.regionService.find("CENTRE", null);
		assertThat(page.getContent().size()).isEqualTo(1);

		page = this.regionService.find("CENTRE", null);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getContent().isEmpty()).isFalse();
	}

	@Test
	@Transactional
	public void shouldFindRegionWithPagination() {
		Page<Region> page = this.regionService.find("Centre", new PageRequest(0, 2));
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getSize()).isEqualTo(2);
		assertThat(page.getTotalPages()).isEqualTo(1);
		assertThat(page.getTotalElements()).isEqualTo(1);
	}

	@Test
	@Transactional
	public void shouldFindRegion() {
		Region region = this.regionService.findById(1);
		assertThat(region.getLibelle()).startsWith("Adamaoua");
	}

	@Test
	@Transactional
	public void shouldInsertRegion() {
		Page<Region> page = this.regionService.find("cameroun", new PageRequest(0, 10));
		int found = page.getContent().size();

		Region region = new Region();
		region.setLibelle("cameroun");
		this.regionService.save(region);
		assertThat(region.getId()).isNotEqualTo(0);

		page = this.regionService.find("cameroun", new PageRequest(0, 10));
		assertThat(page.getContent().size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	public void shouldUpdateRegion() {
		Region region = this.regionService.findById(1);
		String oldLastLibelle = region.getLibelle();
		String newLastName = oldLastLibelle + "X";

		region.setLibelle(newLastName);
		this.regionService.save(region);

		// retrieving new name from database
		region = this.regionService.findById(1);
		assertThat(region.getLibelle()).isEqualTo(newLastName);
	}


}
