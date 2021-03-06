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

import org.springframework.dao.DataAccessException;
import cm.gov.daf.sif.model.TypeProfession;


/**
 * Interface Service TypeProfession
 *
 * @author albert
 */
public interface TypeProfessionService {
    
	TypeProfession findTypeProfessionById(int id) throws DataAccessException;
    
    void saveTypeProfession(TypeProfession t) throws DataAccessException;
    
    Collection<TypeProfession> findTypeProfessionByLibelle(String libelle) throws DataAccessException;
    
    Collection<TypeProfession> findAll() throws DataAccessException;

}
