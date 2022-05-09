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
import poesia.genero.model.GeneroPoesia;
import poesia.genero.service.IGeneroPoesiaService;

@RestController
@RequestMapping("/generos-poesia")
public class GeneroPoesiaController {

	@Autowired
	private IGeneroPoesiaService generoPoesiaService;
	
	/**
	 * Obtiene todos los generos de poesias en la base de datos
	 * @return Listado de generos de poesias
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<GeneroPoesia>> getAll() throws Exception {
		List<GeneroPoesia> generoPoesiaList = generoPoesiaService.getAll();
		if(generoPoesiaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran generos de poesia en la base de datos");
		}
		return new ResponseEntity<List<GeneroPoesia>>(generoPoesiaList, HttpStatus.OK);
	}
	
	/**
	 * Busca un genero de poesia por su id
	 * @param id
	 * @return generoPoesia
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<GeneroPoesia> getById(@PathVariable("id") Integer id) throws Exception {
		GeneroPoesia generoPoesia = generoPoesiaService.getById(id);
		if(generoPoesia == null) {
			throw new ModelNotFoundException("Genero de poesia con id " + id + " no encontrado");
		}
		return new ResponseEntity<GeneroPoesia>(generoPoesia, HttpStatus.OK);
	}
	
}
