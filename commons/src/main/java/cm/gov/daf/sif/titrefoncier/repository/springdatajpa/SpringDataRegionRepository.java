package cm.gov.daf.sif.titrefoncier.repository.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import cm.gov.daf.sif.titrefoncier.model.Region;
import cm.gov.daf.sif.titrefoncier.repository.RegionRepository;


/**
 * @author Albert
 */
public interface SpringDataRegionRepository extends RegionRepository, Repository<Region, Integer> {

	@Override
	@Query("SELECT t FROM Region t WHERE UPPER(t.libelle) LIKE UPPER(:search)")
	public Page<Region> find(@Param("search") String search, Pageable pageable);

	@Override
	@Query("SELECT t FROM Region t WHERE t.id =:id")
	public Region findById(@Param("id") Integer id);

}
