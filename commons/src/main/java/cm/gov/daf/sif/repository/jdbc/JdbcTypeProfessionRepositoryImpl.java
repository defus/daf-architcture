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
package cm.gov.daf.sif.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import cm.gov.daf.sif.model.Profession;
import cm.gov.daf.sif.model.Specialty;
import cm.gov.daf.sif.model.TypeProfession;
import cm.gov.daf.sif.model.Vet;
import cm.gov.daf.sif.repository.ProfessionRepository;
import cm.gov.daf.sif.repository.TypeProfessionRepository;
import cm.gov.daf.sif.util.EntityUtils;

import org.springframework.stereotype.Repository;

@Repository
public class JdbcTypeProfessionRepositoryImpl implements TypeProfessionRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertTypeProfession;

    @Autowired
    public JdbcTypeProfessionRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.insertTypeProfession = new SimpleJdbcInsert(dataSource)
                .withTableName("type_professions")
                .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    @Override
    public Collection<TypeProfession> findByLibelle(String libelle) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("libelle", libelle + "%");
        List<TypeProfession> typeProfessions = this.namedParameterJdbcTemplate.query(
                "SELECT id, libelle, description, date_craetion, salaire_min FROM type_professions WHERE libelle like :libelle",
                params,
                BeanPropertyRowMapper.newInstance(TypeProfession.class)
        );
        return typeProfessions;
    }

    @Override
    public TypeProfession findById(int id) throws DataAccessException {
    	TypeProfession typeProfession;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            typeProfession = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, libelle, description, date_craetion, salaire_min  FROM type_professions WHERE id= :id",
                    params,
                    BeanPropertyRowMapper.newInstance(TypeProfession.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(TypeProfession.class, id);
        }
        return typeProfession;
    }


    @Override
    public void save(TypeProfession typeProfession) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(typeProfession);
        if (typeProfession.isNew()) {
            Number newKey = this.insertTypeProfession.executeAndReturnKey(parameterSource);
            typeProfession.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE type_professions SET libelle=:libelle, description=:description, date_creation=:dateCreation, salaire_min=:salaireMin WHERE id=:id",
                    parameterSource);
        }
    }
    
    @Override
    public Collection<TypeProfession> findAll() throws DataAccessException {
        List<TypeProfession> typeProfessions = new ArrayList<>();
        
        typeProfessions.addAll(this.namedParameterJdbcTemplate.query(
                "SELECT id, libelle, description, date_craetion, salaire_min  FROM type_professions ORDER BY libelle",
                BeanPropertyRowMapper.newInstance(TypeProfession.class)));

        return typeProfessions;
    }

}
