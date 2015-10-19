package cm.gov.daf.sif.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cm.gov.daf.sif.model.Profession;

public interface ProfessionRepository {

	Profession findById(Integer id) throws DataAccessException;

	Page<Profession> find(String Search, Pageable pageable) throws DataAccessException;
	
	void save(Profession profession) throws DataAccessException;

	void delete(Profession profession);

}
