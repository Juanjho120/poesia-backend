package poesia.inscripcion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poesia.exception.dto.ModelNotFoundException;
import poesia.inscripcion.model.Inscripcion;
import poesia.inscripcion.service.IInscripcionService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

	@Autowired
	private IInscripcionService inscripcionService;
	
	/**
	 * Obtiene todas las inscripciones en la base de datos
	 * @return Listado de inscripciones
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Inscripcion>> getAll() throws Exception {
		List<Inscripcion> inscripcionList = inscripcionService.getAll();
		if(inscripcionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inscripciones en la base de datos");
		}
		return new ResponseEntity<List<Inscripcion>>(inscripcionList, HttpStatus.OK);
	}
	
	/**
	 * Busca una inscripcion por su id
	 * @param id
	 * @return inscripcion
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Inscripcion> getById(@PathVariable("id") Integer id) throws Exception {
		Inscripcion inscripcion = inscripcionService.getById(id);
		if(inscripcion == null) {
			throw new ModelNotFoundException("inscripcion con id " + id + " no encontrado");
		}
		return new ResponseEntity<Inscripcion>(inscripcion, HttpStatus.OK);
	}
	

	/**
	 * Guarda una nueva inscripcion
	 * @param inscripcionNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Inscripcion> create(@RequestBody Inscripcion inscripcionNew) throws Exception {
		Inscripcion inscripcion = inscripcionService.create(inscripcionNew);
		if(inscripcion==null) {
			throw new Exception("No se ha podido crear la inscripcion");
		} else {
			return new ResponseEntity<Inscripcion>(inscripcion, HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la inscripcion buscandolo por su id
	 * @param inscripcionUp
	 * @return Inscripcion actualizada
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Inscripcion> update(@RequestBody Inscripcion inscripcionUp) throws Exception {
		Inscripcion inscripcion = inscripcionService.update(inscripcionUp);
		if(inscripcion==null) {
			throw new Exception("No se ha podido actualizar la inscripcion");
		}
		return new ResponseEntity<Inscripcion>(inscripcion, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un inscripcion de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Inscripcion inscripcion = inscripcionService.getById(id);
		if(inscripcion == null) {
			throw new ModelNotFoundException("Inscripcion con id " + id + " no encontrado");
		}
		inscripcionService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
