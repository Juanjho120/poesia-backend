package poesia.genero.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poesia.generico.repository.IGenericRepository;
import poesia.generico.service.CRUDImpl;
import poesia.genero.model.Genero;
import poesia.genero.repository.IGeneroRepository;
import poesia.genero.service.IGeneroService;

@Service
@Transactional
public class GeneroServiceImpl extends CRUDImpl<Genero, String> implements IGeneroService {

	@Autowired
	private IGeneroRepository generoRepository;

	@Override
	protected IGenericRepository<Genero, String> getRepository() {
		return generoRepository;
	}
	
}
