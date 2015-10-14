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
package cm.gov.daf.sif.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import cm.gov.daf.sif.model.TypeProfession;
import cm.gov.daf.sif.repository.OwnerRepository;
import cm.gov.daf.sif.repository.TypeProfessionRepository;

/**
 * Spring Data JPA specialization of the {@link OwnerRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface SpringDataTypeProfessionRepository extends TypeProfessionRepository, Repository<TypeProfession, Integer> {
		
		@Override
	    @Query("SELECT DISTINCT typeProfession FROM TypeProfession typeProfession WHERE typeProfession.libelle LIKE :libelle%")
	    public Collection<TypeProfession> findByLibelle(@Param("libelle") String libelle);
		
		@Override
		@Query("SELECT typeProfession FROM TypeProfession typeProfession WHERE typeProfession.id =:id")
	    public TypeProfession findById(@Param("id") Integer id);
}
