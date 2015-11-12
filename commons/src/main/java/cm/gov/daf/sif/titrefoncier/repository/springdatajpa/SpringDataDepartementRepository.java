package cm.gov.daf.sif.titrefoncier.repository.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cm.gov.daf.sif.titrefoncier.model.Departement;
import cm.gov.daf.sif.titrefoncier.repository.DepartementRepository;


/**
 * @author Albert
 */
public interface SpringDataDepartementRepository extends DepartementRepository, Repository<Departement, Integer> {

	@Override
	@Query("SELECT t FROM Departement t WHERE UPPER(t.libelle) LIKE UPPER(:search)")
	public Page<Departement> find(@Param("search") String search, Pageable pageable);

	@Override
	@Query("SELECT t FROM Departement t WHERE t.id =:id")
	public Departement findById(@Param("id") Integer id);

}
