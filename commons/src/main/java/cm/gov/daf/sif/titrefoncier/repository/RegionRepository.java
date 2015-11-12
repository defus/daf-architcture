package cm.gov.daf.sif.titrefoncier.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cm.gov.daf.sif.titrefoncier.model.Region;

public interface RegionRepository {

	Region findById(Integer id) throws DataAccessException;

	Page<Region> find(String Search, Pageable pageable) throws DataAccessException;
	
	void save(Region t) throws DataAccessException;

	void delete(Region t);
	
	Collection<Region> findAll() throws DataAccessException;

}
