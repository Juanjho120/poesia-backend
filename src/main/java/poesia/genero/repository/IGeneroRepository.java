package poesia.genero.repository;

import org.springframework.stereotype.Repository;

import poesia.generico.repository.IGenericRepository;
import poesia.genero.model.Genero;

/**
 * Repository for Genero Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IGeneroRepository extends IGenericRepository<Genero, String> {

}
