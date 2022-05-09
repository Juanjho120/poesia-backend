package poesia.carrera.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poesia.carrera.model.Carrera;
import poesia.generico.repository.IGenericRepository;

/**
 * Repository for Carrera Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICarreraRepository extends IGenericRepository<Carrera, Integer> {

	@Modifying
	@Query(value = "UPDATE carreras SET estado = false WHERE id_carrera = :id", nativeQuery = true)
	void deleteById(Integer id);
	
}
