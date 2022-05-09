package poesia.reportes.service;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import poesia.carrera.model.Carrera;
import poesia.carrera.service.ICarreraService;
import poesia.exception.dto.ReporteException;
import poesia.genero.model.Genero;
import poesia.genero.model.GeneroPoesia;
import poesia.genero.service.IGeneroPoesiaService;
import poesia.genero.service.IGeneroService;
import poesia.inscripcion.model.Inscripcion;
import poesia.inscripcion.service.IInscripcionService;
import poesia.reportes.dto.InscripcionReporteDTO;
import poesia.util.Utilities;
/**
 * Servicios para la creacion de reportes
 * 
 * @author Juan Tzun
 */
@Service
public class ReporteService {
	
	@Autowired
	private IInscripcionService inscripcionService;
	
	@Autowired
	private IGeneroService generoService;
	
	@Autowired
	private IGeneroPoesiaService generoPoesiaService;
	
	@Autowired
	private ICarreraService carreraService;
	
	public byte[] crearReporteInscripcion(String fechaInicio, String fechaFin, Integer idCarrera, 
			String idGenero, Integer idGeneroPoesia) throws Exception {
		byte[] data = null;
		List<InscripcionReporteDTO> inscripcionDtoList = new ArrayList<InscripcionReporteDTO>();
		List<Inscripcion> inscripcionList = inscripcionService.getByFilters(fechaInicio, fechaFin, idGeneroPoesia, idGenero, idCarrera);
		
		if(!inscripcionList.isEmpty()) {
			for(Inscripcion inscripcion : inscripcionList) {
				InscripcionReporteDTO inscripcionDto = new InscripcionReporteDTO();
				inscripcionDto.setCarnet(inscripcion.getCarnet());
				inscripcionDto.setCarrera(inscripcion.getCarrera().getNombre());
				inscripcionDto.setGeneroPoesia(inscripcion.getGeneroPoesia().getNombre());
				inscripcionDto.setNombreCompleto(inscripcion.getNombreCompleto());
				inscripcionDto.setFechaInscripcion(Utilities.darFormatoFechaSinHora(inscripcion.getFechaInscripcion(), false));
				inscripcionDto.setFechaDeclamacion(Utilities.darFormatoFechaSinHora(inscripcion.getFechaDeclamacion(), false));
				inscripcionDtoList.add(inscripcionDto);
			}
			Genero genero = generoService.getById(idGenero);
			GeneroPoesia generoPoesia = generoPoesiaService.getById(idGeneroPoesia);
			Carrera carrera = carreraService.getById(idCarrera);
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("GENERO", idGenero.equalsIgnoreCase("T") ? "TODOS" : genero != null ? genero.getNombre() : "");
			parametros.put("GENERO_POESIA", idGeneroPoesia == 0 ? "TODOS" : generoPoesia != null ? generoPoesia.getNombre() : "");
			parametros.put("CARRERA", idCarrera == 0 ? "TODOS" : carrera != null ? carrera.getNombre() : "");
			parametros.put("RANGO_FECHAS", fechaInicio + " " + fechaFin);
			parametros.put("TOTAL_INSCRITOS", inscripcionDtoList.size());
			parametros.put("FECHA_IMPRESION", Utilities.darFormatoFechaSinHora(LocalDate.now(), false));
			
			String urlLogo = new ClassPathResource("/reports/logo.png").getPath();
		    BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource(urlLogo));
		    
		    parametros.put("LOGO", image);
		    
			InputStream inputMaster = new ClassPathResource("/reports/Inscripciones.jrxml").getInputStream();
			JasperDesign jasperDesignMaster = JRXmlLoader.load(inputMaster);
			JasperReport jasperReportMaster = JasperCompileManager.compileReport(jasperDesignMaster);
			
			JasperPrint print = JasperFillManager.fillReport(jasperReportMaster, parametros, new JRBeanCollectionDataSource(inscripcionDtoList));
			data = JasperExportManager.exportReportToPdf(print);
		} else {
			throw new ReporteException("No se encontraron inscripciones en este rango de búsqueda");
		}
		return data;
	}

}
