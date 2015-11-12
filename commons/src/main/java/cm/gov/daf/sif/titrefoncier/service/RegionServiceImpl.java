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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cm.gov.daf.sif.titrefoncier.model.Region;
import cm.gov.daf.sif.titrefoncier.repository.RegionRepository;

/**
 * @author Albert
 */
@Service
public class RegionServiceImpl implements RegionService {

	private RegionRepository regionRepository;

	@Autowired
	public RegionServiceImpl(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Region findById(Integer id) throws DataAccessException {
		return regionRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Region> find(String search, Pageable pageable) throws DataAccessException {
		if (search == null) {
			search = "%%";
		} else {
			search = "%" + search + "%";
		}
		return regionRepository.find(search, pageable);
	}

	@Override
	@Transactional
	public void save(Region t) throws DataAccessException {
		regionRepository.save(t);
	}

	@Override
	@Transactional
	public void delete(Region t) {
		regionRepository.delete(t);
	}
	
	@Override
	public Collection<Region> findAll() throws DataAccessException {
		return regionRepository.findAll();
	}
}
