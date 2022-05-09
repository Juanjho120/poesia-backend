package poesia.carrera.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poesia.carrera.model.Carrera;
import poesia.carrera.repository.ICarreraRepository;
import poesia.carrera.service.ICarreraService;
import poesia.generico.repository.IGenericRepository;
import poesia.generico.service.CRUDImpl;

@Service
@Transactional
public class CarreraServiceImpl extends CRUDImpl<Carrera, Integer> implements ICarreraService {

	@Autowired
	private ICarreraRepository carreraRepository;

	@Override
	protected IGenericRepository<Carrera, Integer> getRepository() {
		return carreraRepository;
	}
	
	@Override
	public Carrera create(Carrera carrera) throws Exception {
		carrera.setEstado(true);
		carrera.setFechaModificacion(LocalDateTime.now());
		return carreraRepository.save(carrera);
	}
	
	@Override
	public Carrera update(Carrera carrera) throws Exception {
		carrera.setFechaModificacion(LocalDateTime.now());
		return carreraRepository.save(carrera);
	}
	
}
