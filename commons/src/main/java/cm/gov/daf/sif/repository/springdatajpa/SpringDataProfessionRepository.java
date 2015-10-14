package cm.gov.daf.sif.repository.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import cm.gov.daf.sif.model.Profession;
import cm.gov.daf.sif.repository.ProfessionRepository;

public interface SpringDataProfessionRepository extends ProfessionRepository, Repository<Profession, Integer> {

	@Override
	@Query("SELECT p FROM Profession p WHERE p.libelle LIKE %:search%")
	public Page<Profession> find(@Param("search") String search, Pageable pageable);

	@Override
	@Query("SELECT p FROM Profession p WHERE p.id =:id")
	public Profession findById(@Param("id") Integer id);
}
