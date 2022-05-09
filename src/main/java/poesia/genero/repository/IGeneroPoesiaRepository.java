package poesia.genero.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poesia.generico.repository.IGenericRepository;
import poesia.genero.model.GeneroPoesia;

/**
 * Repository for GeneroPoesia Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IGeneroPoesiaRepository extends IGenericRepository<GeneroPoesia, Integer> {

	@Modifying
	@Query(value = "UPDATE generos_poesia SET estado = false WHERE id_genero_poesia = :id", nativeQuery = true)
	void deleteById(Integer id);
	
}
