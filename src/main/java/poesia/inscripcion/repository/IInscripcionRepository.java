package poesia.inscripcion.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poesia.generico.repository.IGenericRepository;
import poesia.inscripcion.model.Inscripcion;

/**
 * Repository for Inscripcion Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IInscripcionRepository extends IGenericRepository<Inscripcion, Integer> {

	@Modifying
	@Query(value = "UPDATE inscripciones SET estado = false WHERE id_inscripcion = :id", nativeQuery = true)
	void deleteById(Integer id);
	
	@Query("FROM Inscripcion WHERE carnet = :carnet")
	Inscripcion findByCarnet(String carnet);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin")
	List<Inscripcion> findByFechaDeclamacion(LocalDate fechaInicio, LocalDate fechaFin);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin AND generoPoesia.idGeneroPoesia = :idGeneroPoesia")
	List<Inscripcion> findByFechaDeclamacionAndGeneroPoesia(LocalDate fechaInicio, LocalDate fechaFin, 
			Integer idGeneroPoesia);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin AND genero.idGenero = :idGenero")
	List<Inscripcion> findByFechaDeclamacionAndGenero(LocalDate fechaInicio, LocalDate fechaFin, 
			String idGenero);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin AND carrera.idCarrera = :idCarrera")
	List<Inscripcion> findByFechaDeclamacionAndCarrera(LocalDate fechaInicio, LocalDate fechaFin, Integer idCarrera);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin AND generoPoesia.idGeneroPoesia = :idGeneroPoesia AND carrera.idCarrera = :idCarrera")
	List<Inscripcion> findByFechaDeclamacionAndGeneroPoesiaAndCarrera(LocalDate fechaInicio, LocalDate fechaFin, 
			Integer idGeneroPoesia, Integer idCarrera);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin AND generoPoesia.idGeneroPoesia = :idGeneroPoesia AND genero.idGenero = :idGenero")
	List<Inscripcion> findByFechaDeclamacionAndGeneroPoesiaAndGenero(LocalDate fechaInicio, LocalDate fechaFin, 
			Integer idGeneroPoesia, String idGenero);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin AND generoPoesia.idGeneroPoesia = :idGeneroPoesia AND genero.idGenero = :idGenero AND carrera.idCarrera = :idCarrera")
	List<Inscripcion> findByFechaDeclamacionAndGeneroPoesiaAndGeneroAndCarrera(LocalDate fechaInicio, LocalDate fechaFin, 
			Integer idGeneroPoesia, String idGenero, Integer idCarrera);
	
	@Query("FROM Inscripcion WHERE fechaDeclamacion BETWEEN :fechaInicio AND :fechaFin AND genero.idGenero = :idGenero AND carrera.idCarrera = :idCarrera")
	List<Inscripcion> findByFechaDeclamacionAndGeneroAndCarrera(LocalDate fechaInicio, LocalDate fechaFin, 
			String idGenero, Integer idCarrera);
	
}
