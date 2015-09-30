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
package cm.gov.daf.sif.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import cm.gov.daf.sif.model.Profession;
import cm.gov.daf.sif.repository.ProfessionRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JpaProfessionRepositoryImpl implements ProfessionRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public Collection<Profession> findByLibelle(String libelle) {
        Query query = this.em.createQuery("SELECT DISTINCT profession FROM Profession profession WHERE profession.libelle LIKE :libelle");
        query.setParameter("libelle", libelle + "%");
        return query.getResultList();
    }

    @Override
    public Profession findById(int id) {
        Query query = this.em.createQuery("SELECT profession FROM Profession profession WHERE profession.id =:id");
        query.setParameter("id", id);
        return (Profession) query.getSingleResult();
    }


    @Override
    public void save(Profession profession) {
    	if (profession.getId() == null) {
    		this.em.persist(profession);     		
    	}
    	else {
    		this.em.merge(profession);    
    	}

    }

}
