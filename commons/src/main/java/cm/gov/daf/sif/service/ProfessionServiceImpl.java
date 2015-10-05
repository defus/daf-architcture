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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import cm.gov.daf.sif.model.Profession;
import cm.gov.daf.sif.repository.ProfessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation Interface Service Profession
 *
 * @author Albert
 */
@Service
public class ProfessionServiceImpl implements ProfessionService {

    private ProfessionRepository professionRepository;

    @Autowired
    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Profession findProfessionById(int id) throws DataAccessException {
        return professionRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveProfession(Profession profession) throws DataAccessException {
        professionRepository.save(profession);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Profession> findProfessionByLibelle(String libelle) throws DataAccessException {
        return professionRepository.findByLibelle(libelle);
    }
}
