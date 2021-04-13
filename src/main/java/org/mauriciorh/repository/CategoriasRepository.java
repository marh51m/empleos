package org.mauriciorh.repository;

import org.mauriciorh.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

	public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
	}
