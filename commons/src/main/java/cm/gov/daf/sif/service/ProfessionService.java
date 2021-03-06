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

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cm.gov.daf.sif.model.Profession;

/**
 * Interface Service Profession
 *
 * @author albert
 */
public interface ProfessionService {

	Profession findById(Integer id) throws DataAccessException;

	void saveProfession(Profession profession) throws DataAccessException;

	Page<Profession> find(String search, Pageable pageable) throws DataAccessException;
	
	void deleteProfession(Profession profession);

}
