package poesia.reportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poesia.reportes.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
	
	@Autowired
	private ReporteService reporteService;
	
	@GetMapping(value = "/inscripciones/fecha-declamacion/{fechaInicio}/{fechaFin}/genero/{idGenero}/genero-poesia/{idGeneroPoesia}/carrera/{idCarrera}", 
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> crearReporteInscripcion(@PathVariable("fechaInicio") String fechaInicio,
			@PathVariable("fechaFin") String fechaFin, @PathVariable("idGenero") String idGenero, @PathVariable("idGeneroPoesia") Integer idGeneroPoesia, 
			@PathVariable("idCarrera") Integer idCarrera) throws Exception {
		byte[] data = null;
		data = reporteService.crearReporteInscripcion(fechaInicio, fechaFin, idCarrera, idGenero, idGeneroPoesia);
		if(data==null) {
			throw new Exception("No se ha logrado generar el reporte");
		}
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

}
