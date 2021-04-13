package org.mauriciorh.controller;

import java.util.List;

import org.mauriciorh.model.Usuario;
import org.mauriciorh.service.IntUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/usuarios")
public class UsuariosController {
	
	@Autowired
	private IntUsuariosService usuariosService;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Usuario> lista= usuariosService.obtenerTodas();
		for(Usuario u: lista) {
			System.out.println(u);
		}
		model.addAttribute("usuarios", lista);
		return "usuarios/listaUsuarios";
	}
	
}
