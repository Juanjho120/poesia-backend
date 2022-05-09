package poesia.carrera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poesia.carrera.model.Carrera;
import poesia.carrera.service.ICarreraService;
import poesia.exception.dto.ModelNotFoundException;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
	
	@Autowired
	private ICarreraService carreraService;
	
	/**
	 * Obtiene todas las carreras en la base de datos
	 * @return Listado de carreras
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Carrera>> getAll() throws Exception {
		List<Carrera> carreraList = carreraService.getAll();
		if(carreraList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran carreras en la base de datos");
		}
		return new ResponseEntity<List<Carrera>>(carreraList, HttpStatus.OK);
	}
	
	/**
	 * Busca una carrera por su id
	 * @param id
	 * @return carrera
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Carrera> getById(@PathVariable("id") Integer id) throws Exception {
		Carrera carrera = carreraService.getById(id);
		if(carrera == null) {
			throw new ModelNotFoundException("carrera con id " + id + " no encontrado");
		}
		return new ResponseEntity<Carrera>(carrera, HttpStatus.OK);
	}
}
