package poesia.genero.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poesia.generico.repository.IGenericRepository;
import poesia.generico.service.CRUDImpl;
import poesia.genero.model.GeneroPoesia;
import poesia.genero.repository.IGeneroPoesiaRepository;
import poesia.genero.service.IGeneroPoesiaService;

@Service
@Transactional
public class GeneroPoesiaServiceImpl extends CRUDImpl<GeneroPoesia, Integer> implements IGeneroPoesiaService {

	@Autowired
	private IGeneroPoesiaRepository generoPoesiaRepository;

	@Override
	protected IGenericRepository<GeneroPoesia, Integer> getRepository() {
		return generoPoesiaRepository;
	}
	
	@Override
	public GeneroPoesia create(GeneroPoesia generoPoesia) throws Exception {
		generoPoesia.setEstado(true);
		generoPoesia.setFechaModificacion(LocalDateTime.now());
		return generoPoesiaRepository.save(generoPoesia);
	}
	
	@Override
	public GeneroPoesia update(GeneroPoesia generoPoesia) throws Exception {
		generoPoesia.setFechaModificacion(LocalDateTime.now());
		return generoPoesiaRepository.save(generoPoesia);
	}
	
}
