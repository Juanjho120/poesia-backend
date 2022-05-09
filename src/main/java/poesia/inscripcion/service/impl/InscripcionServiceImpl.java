package poesia.inscripcion.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poesia.exception.dto.CarnetException;
import poesia.exception.dto.EdadException;
import poesia.exception.dto.TelefonoException;
import poesia.generico.repository.IGenericRepository;
import poesia.generico.service.CRUDImpl;
import poesia.genero.model.GeneroPoesia;
import poesia.genero.service.IGeneroPoesiaService;
import poesia.inscripcion.dto.CarnetDTO;
import poesia.inscripcion.model.Inscripcion;
import poesia.inscripcion.repository.IInscripcionRepository;
import poesia.inscripcion.service.IInscripcionService;

@Service
@Transactional
public class InscripcionServiceImpl extends CRUDImpl<Inscripcion, Integer> implements IInscripcionService {

	@Autowired
	private IInscripcionRepository inscripcionRepository;
	
	@Autowired
	private IGeneroPoesiaService generoPoesiaService;

	@Override
	protected IGenericRepository<Inscripcion, Integer> getRepository() {
		return inscripcionRepository;
	}
	
	@Override
	public Inscripcion create(Inscripcion inscripcion) throws Exception {
		Inscripcion inscripcionAux = inscripcionRepository.findByCarnet(inscripcion.getCarnet());
		
		if(inscripcionAux != null) {
			throw new CarnetException("El carnet ingresado ya se encuentra registrado");
		}
		
		if(!this.esMayorEdad(17, inscripcion.getFechaNacimiento())) {
			throw new EdadException("El participante debe ser mayor a 17 años");
		}
		
		if(inscripcion.getTelefono() == null || inscripcion.getTelefono().toString().length() != 8) {
			throw new TelefonoException("El teléfono debe tener 8 dígitos");
		}
		
		CarnetDTO carnetDto = this.validarCarnet(inscripcion.getCarnet());
		if(!carnetDto.getValido()) {
			throw new CarnetException("Carnet invalido: " + carnetDto.getRazon());
		}
		
		GeneroPoesia generoPoesia = generoPoesiaService.getById(inscripcion.getGeneroPoesia().getIdGeneroPoesia());
		inscripcion.setEstado(true);
		inscripcion.setFechaModificacion(LocalDateTime.now());
		inscripcion.setFechaInscripcion(LocalDate.now());
		inscripcion.setFechaDeclamacion(this.generarFechaDeclamacion(inscripcion.getCarnet(), 
				generoPoesia != null ? generoPoesia.getNombre() : ""));
		return inscripcionRepository.save(inscripcion);
	}
	
	@Override
	public Inscripcion update(Inscripcion inscripcion) throws Exception {
		CarnetDTO carnetDto = this.validarCarnet(inscripcion.getCarnet());
		if(carnetDto.getValido()) {
			inscripcion.setFechaModificacion(LocalDateTime.now());
			return inscripcionRepository.save(inscripcion);
		}
		return null;
	}
	
	private CarnetDTO validarCarnet(String carnet) {
		CarnetDTO carnetDto = new CarnetDTO();
		carnetDto.setValido(false);
		if(carnet == null) {
			carnetDto.setRazon("Campo carnet nulo");
		} else if(carnet.length() != 6) {
			carnetDto.setRazon("La longitud del carnet debe ser de 6 caracteres");
		} else if(carnet.contains("0")) {
			carnetDto.setRazon("El carnet no debe contener 0");
		} else if(!carnet.substring(0, 1).equalsIgnoreCase("A")) {
			carnetDto.setRazon("El primer caracter debe ser A");
		} else if(!carnet.substring(2, 3).equalsIgnoreCase("5")) {
			carnetDto.setRazon("El tercer caracter debe ser 5");
		} else if(!"139".contains(carnet.substring(carnet.length() - 1))) {
			carnetDto.setRazon("El último caracter debe ser 1, 3 o 9");
		} else {
			carnetDto.setValido(true);
			carnetDto.setRazon("Carnet válido");
		}
		return carnetDto;
	}
	
	private LocalDate generarFechaDeclamacion(String carnet, String genero) {
		LocalDate fechaDeclamacion = null;
		LocalDate fechaInicial = LocalDate.now();
		if(carnet.substring(carnet.length() - 1).equalsIgnoreCase("1") && genero.toUpperCase().equalsIgnoreCase("DRAMÁTICA")) {
			int contadorDias = 0;
			while (contadorDias < 5) {
				if(fechaInicial.getDayOfWeek().getValue() != DayOfWeek.SATURDAY.getValue() && fechaInicial.getDayOfWeek().getValue() != DayOfWeek.SUNDAY.getValue()) {
					fechaDeclamacion = fechaInicial;
					contadorDias++;
				}
				fechaInicial = fechaInicial.plusDays(1);
			}
		} else if(carnet.substring(carnet.length() - 1).equalsIgnoreCase("3") && genero.toUpperCase().equalsIgnoreCase("ÉPICA")) {
			fechaDeclamacion = fechaInicial.with(TemporalAdjusters.lastDayOfMonth());
			if(fechaDeclamacion.compareTo(fechaInicial) == 0) {
				fechaDeclamacion = fechaDeclamacion.plusMonths(1);
			}
			boolean diaInvalido = fechaDeclamacion.getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue() || fechaDeclamacion.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue();
			while(diaInvalido) {
				fechaDeclamacion = fechaDeclamacion.minusDays(1);
				diaInvalido = fechaDeclamacion.getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue() || fechaDeclamacion.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue();
			}
		} else {
			fechaDeclamacion = fechaInicial.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		}		
		return fechaDeclamacion;
	}
	
	private Boolean esMayorEdad(Integer edadLimite, LocalDate fechaNacimiento) {
		int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
		return edad > edadLimite;
	}

	@Override
	public List<Inscripcion> getByFilters(String fechaInicio, String fechaFin, Integer idGeneroPoesia, String idGenero,
			Integer idCarrera) {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaInicioParse = LocalDate.parse(fechaInicio, formatter);
		LocalDate fechaFinParse = LocalDate.parse(fechaFin, formatter);
		
		if(idGeneroPoesia == 0 && idGenero.equalsIgnoreCase("T") && idCarrera > 0) {
			return inscripcionRepository.findByFechaDeclamacionAndCarrera(fechaInicioParse, fechaFinParse, idCarrera);
		} else if(idGeneroPoesia == 0 && !idGenero.equalsIgnoreCase("T") && idCarrera == 0) {
			return inscripcionRepository.findByFechaDeclamacionAndGenero(fechaInicioParse, fechaFinParse, idGenero);
		} else if(idGeneroPoesia > 0 && idGenero.equalsIgnoreCase("T") && idCarrera == 0) {
			return inscripcionRepository.findByFechaDeclamacionAndGeneroPoesia(fechaInicioParse, fechaFinParse, idGeneroPoesia);
		} else if(idGeneroPoesia > 0 && !idGenero.equalsIgnoreCase("T") && idCarrera == 0) {
			return inscripcionRepository.findByFechaDeclamacionAndGeneroPoesiaAndGenero(fechaInicioParse, fechaFinParse, idGeneroPoesia, idGenero);
		} else if(idGeneroPoesia == 0 && !idGenero.equalsIgnoreCase("T") && idCarrera > 0) {
			return inscripcionRepository.findByFechaDeclamacionAndGeneroAndCarrera(fechaInicioParse, fechaFinParse, idGenero, idCarrera);
		} else if(idGeneroPoesia > 0 && idGenero.equalsIgnoreCase("T") && idCarrera > 0) {
			return inscripcionRepository.findByFechaDeclamacionAndGeneroPoesiaAndCarrera(fechaInicioParse, fechaFinParse, idGeneroPoesia, idCarrera);
		} else if(idGeneroPoesia == 0 && idGenero.equalsIgnoreCase("T") && idCarrera == 0) {
			return inscripcionRepository.findByFechaDeclamacion(fechaInicioParse, fechaFinParse);
		} else {
			return inscripcionRepository.findByFechaDeclamacionAndGeneroPoesiaAndGeneroAndCarrera(fechaInicioParse, fechaFinParse, idGeneroPoesia, idGenero, idCarrera);
		}
	}
	
}
