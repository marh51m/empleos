package org.mauriciorh.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.mauriciorh.model.Vacante;
import org.mauriciorh.service.IntVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@Autowired
	private IntVacantesService serviceVacante;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		//Devuelve una vista (Un archivo html)
		List<Vacante> vacantes = serviceVacante.obtenerTodas();
		model.addAttribute("vacantes", vacantes);
		return "home";
	}
	
	@GetMapping("/mensaje")
	public String mensaje(Model model) {
		//formatear una fecha 
		//LocalDate fecha = LocalDate.now();
		LocalDate fecha= null;
		try {
			fecha = LocalDate.parse("23-12-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			System.out.println("Fecha: " + fecha);
		}catch(DateTimeException ex) {
			System.out.println("Error : " + ex.getMessage());
		}

		model.addAttribute("msg", "Hola Mundo");
		//actualmente thymeleaf no soporta localDate, por tanto convertirlo a Date
		model.addAttribute("fecha", java.sql.Date.valueOf(fecha));
		return "mensaje";
	}
	//Nuevo metodo 
	
	@GetMapping("/vacante")
	public String vacante(Model model) {
		Integer id = 1000;
		String nombre = "Programador de Aplicaciones web";
		LocalDate fecha = LocalDate.now();
		Double  salario= 12500.00;
		Boolean vigente = true;
		model.addAttribute("id", id);
		model.addAttribute("nombre", nombre);
		model.addAttribute("fecha", fecha);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);
		return "vacante";
	}
	
	//Una coleccion de datos 
	@GetMapping("/lista")
	public String lista(Model model) {
		List<String> vacantes = new LinkedList<String>();
		vacantes.add("Contador Publico");
		vacantes.add("Programador de Aplicaciones");
		vacantes.add("Tecnico de Mantenimiento de Computo");
		vacantes.add("Arquitecto");
		vacantes.add("Ingeniero en Electronica");
		//Impimir el areglo 
		for(String elemento : vacantes) {
			System.out.println(elemento);
		}
		model.addAttribute("vacantes",vacantes);
		return "vacante";	
	}
	
	@GetMapping("/detalles")
	public String detalles(Model model) {
		Vacante v = new Vacante();
		System.out.println(v);
		v.setId(100);
		v.setNombre("Ingeniero Electronico");
		v.setDescripcion("Relaciona con aplicaciones para IOT");
		v.setFecha(LocalDate.now());
		v.setSalario(12800.0);
		System.out.println(v);
		model.addAttribute("vacante", v);
		return "detalles";
	}
	
	@GetMapping("/tabla")
	public String mostrarVacantes(Model model) {
		List<Vacante> vacantes = serviceVacante.obtenerTodas();
		for(Vacante elemento: vacantes) {
			System.out.println(elemento.getId());
			System.out.println(elemento.getNombre());
			System.out.println(elemento.getDescripcion());
			System.out.println(elemento.getFecha());
			System.out.println(elemento.getSalario());
			System.out.println(elemento.getDestacado());
		}
		model.addAttribute("vacantes", vacantes);
		return "tabla";
	}
	
}
