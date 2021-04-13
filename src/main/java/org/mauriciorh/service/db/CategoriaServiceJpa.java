package org.mauriciorh.service.db;

import java.util.List;
import java.util.Optional;

import org.mauriciorh.model.Categoria;
import org.mauriciorh.repository.CategoriasRepository;
import org.mauriciorh.service.IntCategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CategoriaServiceJpa implements IntCategoriasService {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	
	@Override
	public List<Categoria> obtenerTodas() {
		// TODO Auto-generated method stub
		return repoCategorias.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
		if(optional.isPresent()){
			return optional.get();			
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		repoCategorias.save(categoria);

	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		repoCategorias.deleteById(idCategoria);
		
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoCategorias.findAll(page);
	}
}
