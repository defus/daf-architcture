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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import cm.gov.daf.sif.model.Profession;
import cm.gov.daf.sif.repository.ProfessionRepository;

import org.springframework.stereotype.Repository;

@Repository
public class JdbcProfessionRepositoryImpl implements ProfessionRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertProfession;

    @Autowired
    public JdbcProfessionRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.insertProfession = new SimpleJdbcInsert(dataSource)
                .withTableName("professions")
                .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    @Override
    public Collection<Profession> findByLibelle(String libelle) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("libelle", libelle + "%");
        List<Profession> professions = this.namedParameterJdbcTemplate.query(
                "SELECT id, libelle, description FROM professions WHERE libelle like :libelle",
                params,
                BeanPropertyRowMapper.newInstance(Profession.class)
        );
        return professions;
    }

    @Override
    public Profession findById(int id) throws DataAccessException {
        Profession profession;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            profession = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, libelle, description FROM professions WHERE id= :id",
                    params,
                    BeanPropertyRowMapper.newInstance(Profession.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Profession.class, id);
        }
        return profession;
    }


    @Override
    public void save(Profession profession) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(profession);
        if (profession.isNew()) {
            Number newKey = this.insertProfession.executeAndReturnKey(parameterSource);
            profession.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE professions SET libelle=:libelle, description=:description WHERE id=:id",
                    parameterSource);
        }
    }

}
