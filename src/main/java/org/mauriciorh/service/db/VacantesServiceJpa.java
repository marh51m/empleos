package org.mauriciorh.service.db;

import java.util.List;
import java.util.Optional;
import org.mauriciorh.model.Vacante;
import org.mauriciorh.repository.VacantesRepository;
import org.mauriciorh.service.IntVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VacantesServiceJpa implements IntVacantesService {
	
	@Autowired
	private VacantesRepository repoVacantes;

	@Override
	public List<Vacante> obtenerTodas() {
		// TODO Auto-generated method stub
		return repoVacantes.findAll();
	}

	public Vacante buscarPorId(Integer idCategoria) {
		Optional<Vacante> optional = repoVacantes.findById(idCategoria);
		if(optional.isPresent()){
			return optional.get();			
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
		repoVacantes.save(vacante);
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		repoVacantes.deleteById(idVacante);
		
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoVacantes.findAll(page);
	}

	@Override
	public void eliminar(Vacante vacante) {
		// TODO Auto-generated method stub
		
	}


}
