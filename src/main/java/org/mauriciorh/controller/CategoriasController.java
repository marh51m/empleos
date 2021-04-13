package org.mauriciorh.controller;

import java.util.List;

import org.mauriciorh.model.Categoria;
import org.mauriciorh.model.Vacante;
import org.mauriciorh.service.IntCategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
//anotacion @requestmapping a nivel de clase
@RequestMapping(value="/categorias")

public class CategoriasController {
	
	@Autowired
	private IntCategoriasService categoriasService;
	
	@RequestMapping(value="/indexPaginado",method=RequestMethod.GET)
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Categoria>lista = categoriasService.buscarTodas(page);
		model.addAttribute("categorias", lista);
	return "categorias/listaCategorias";
	}

	@RequestMapping(value="/editar",method=RequestMethod.GET)
	public String editar(@RequestParam("id") int idCategoria, Model model) {
		Categoria categoria = categoriasService.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategorias";
		
	}
	
	
	@RequestMapping(value="/eliminar",method=RequestMethod.GET)
	public String eliminar(@RequestParam("id") int idCategoria, RedirectAttributes attributes) {
		categoriasService.eliminar(idCategoria);
		attributes.addFlashAttribute("msg", "Categoria eliminada");
		return "redirect:/categorias/index"; 
		
	}
	//anotacion anterior  para solicitar peticiones
	//@GetMapping("/index") //nueva forma
	@RequestMapping(value="/index",method=RequestMethod.GET) //anterioir forma
	
	public String mostrarIndex(Model model) {
		
		List<Categoria> lista =categoriasService.obtenerTodas();
		model.addAttribute("categorias", lista);
		for(Categoria c: lista) {
			System.out.println(c);
		}
		model.addAttribute("categorias", lista);
		
		return "categorias/listaCategorias";
		
	}
	//@GetMapping("/crear") //nueva forma
	@RequestMapping(value="/crear",method=RequestMethod.GET) //anterioir forma
	public String crear(Categoria categoria) {
		return "categorias/formCategorias";
		
	}
	//@PostMapping("/guardar") //nueva forma
	/*@RequestMapping(value="/guardar",method=RequestMethod.POST) //anterioir forma
	public String guardar(@RequestParam("nombre")String nombre,@RequestParam("descripcion") String descripcion) {
		System.out.println("Nombre: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		return "categorias/listaCategorias";
	}*/
	@RequestMapping(value="/guardar",method=RequestMethod.POST) //anterioir forma
	public String guardar(Categoria categoria, RedirectAttributes attributes) {
		//categoria.setId(categoriasService.obtenerTodas().size()+1);
		categoriasService.guardar(categoria);
		attributes.addFlashAttribute("msg","La categoria se guardo");
		return "redirect:/categorias/index";
		
	}
	

}
