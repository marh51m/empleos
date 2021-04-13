package org.mauriciorh.controller;

import java.util.List;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mauriciorh.model.Categoria;
import org.mauriciorh.model.Vacante;
import org.mauriciorh.service.IntCategoriasService;
import org.mauriciorh.service.IntVacantesService;
import org.mauriciorh.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/vacantes")
public class VacantesController {
	
	@Autowired
	private IntCategoriasService serviceCategoria;
	
	@Autowired
	private IntVacantesService serviceVacante;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacante.obtenerTodas();
		for(Vacante v : lista) {
			System.out.println(v);
		}
		model.addAttribute("vacantes", lista);
		return "vacantes/listaVacantes";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Vacante>lista = serviceVacante.buscarTodas(page);
	model.addAttribute("vacantes", lista);
	return "vacantes/listaVacantes";
	}
	
	
	@GetMapping(value="/editar")
	public String editar(@RequestParam("id") int idVacante, Model model) {
		Vacante vacante = serviceVacante.buscarPorId(idVacante);
		model.addAttribute("categorias", serviceCategoria.obtenerTodas());
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";	
	}
	
	//vinculación entre el formulario y la clase modelo
	//Data Binding
	
	@PostMapping("/guardar")
	public String guardar(Vacante vacante, BindingResult result,
			RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart,Model model) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
				}
			model.addAttribute("categorias", serviceCategoria.obtenerTodas());
			return "redirect:/vacantes/indexPaginado";
			}
		if (!multiPart.isEmpty()) {
				//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
				String ruta = "c:/empleos/images_vacantes/"; // Windows
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null){ 
						// La imagen si se subio
					// Procesamos la variable nombreImagen
					vacante.setImagen(nombreImagen);
				}
			}
		
		//guardar el tabla o en la lista
		Categoria c = serviceCategoria.buscarPorId(vacante.getCategoria().getId());
		vacante.setCategoria(c);
		//vacante.setId(serviceVacante.obtenerTodas().size()+1);
		serviceVacante.guardar(vacante);
		System.out.println("Vacante: " + vacante);
		System.out.println("Numero de Vacantes : " + serviceVacante.obtenerTodas().size());
		//return "vacantes/listaVacantes";
		//post-redirect-get
		//model.addAttribute("msg", "Registro guardado con exito!");
		attributes.addFlashAttribute("msg","Registro guardado con exito!");
		return "redirect:/vacantes/indexPaginado";
	}	
		
	
	/*@GetMapping("/guardar")
	public String guardar(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha,
			@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles) {
		
		Vacante v = new Vacante();
		v.setNombre(nombre);
		v.setDescripcion(descripcion);
		//v.setFecha(fecha);
		v.setDestacado(destacado);
		v.setSalario(salario);
		//guardar el objeto de tipo vacante
		//tabla o en la lista doblemente enlazada
		System.out.println(v);
		return "vacantes/listaVacantes";
	}
	*/
	@GetMapping("/crear")
	public String crear(Vacante vacante, Model model) {
		model.addAttribute("categorias", serviceCategoria.obtenerTodas());
		for(Categoria c : serviceCategoria.obtenerTodas()) {
			System.err.print(c);
		}
		return "vacantes/formVacante";
	}
	
	@GetMapping("/detalle/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model){
		System.out.println("Vacante = " + serviceVacante.buscarPorId(idVacante));
		model.addAttribute("vacante", serviceVacante.buscarPorId(idVacante));
			return "vacantes/detalle";
	}
	
	
	@GetMapping("/eliminar")
	//RequestMapping - Vincula los parametros (Binding)
	public String eliminar(@RequestParam("id") int idVacante, RedirectAttributes attributes) {
		//System.out.println("IdVacante : " + idVacante);
		//model.addAttribute("idVacante", idVacante);
		serviceVacante.eliminar(idVacante);
		attributes.addFlashAttribute("msg", "Vacante Eliminado");
		return "redirect:/vacantes/indexPaginado";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }
      });
    }
}
