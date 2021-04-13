package org.mauriciorh.repository;

import java.util.List;

import org.mauriciorh.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	public List<Vacante> findByEstatus(String estatus);
	 public List<Vacante> findByEstatusAndDestacado(String estatus,Integer destacado);


}