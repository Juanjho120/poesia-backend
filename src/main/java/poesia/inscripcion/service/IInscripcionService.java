package poesia.inscripcion.service;

import java.util.List;

import poesia.generico.service.ICRUD;
import poesia.inscripcion.model.Inscripcion;

/**
 * Services for Carrera Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IInscripcionService extends ICRUD<Inscripcion, Integer> {

	List<Inscripcion> getByFilters(String fechaInicio, String fechaFin, Integer idGeneroPoesia, String idGenero, Integer idCarrera);
	
}
