package poesia.genero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poesia.exception.dto.ModelNotFoundException;
import poesia.genero.model.Genero;
import poesia.genero.service.IGeneroService;

@RestController
@RequestMapping("/generos")
public class GeneroController {

	@Autowired
	private IGeneroService generoService;
	
	/**
	 * Obtiene todos los generos en la base de datos
	 * @return Listado de generos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Genero>> getAll() throws Exception {
		List<Genero> generoList = generoService.getAll();
		if(generoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran generos en la base de datos");
		}
		return new ResponseEntity<List<Genero>>(generoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un genero por su id
	 * @param id
	 * @return genero
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Genero> getById(@PathVariable("id") String id) throws Exception {
		Genero genero = generoService.getById(id);
		if(genero == null) {
			throw new ModelNotFoundException("genero con id " + id + " no encontrado");
		}
		return new ResponseEntity<Genero>(genero, HttpStatus.OK);
	}
}
