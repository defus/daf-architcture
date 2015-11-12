package cm.gov.daf.sif.titrefoncier.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cm.gov.daf.sif.titrefoncier.model.Departement;

public interface DepartementRepository {

	Departement findById(Integer id) throws DataAccessException;

	Page<Departement> find(String Search, Pageable pageable) throws DataAccessException;
	
	void save(Departement t) throws DataAccessException;

	void delete(Departement t);
	
	Collection<Departement> findAll() throws DataAccessException;

}
