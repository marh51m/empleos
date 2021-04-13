package org.mauriciorh.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.mauriciorh.model.Usuario;
import org.springframework.stereotype.Service;

@Service

public class UsuariosServiceImp implements IntUsuariosService {
	
	private List <Usuario> lista = null;

	@Override
	public List<Usuario> obtenerTodas() {
		
		
		return lista;
	}
	
	public UsuariosServiceImp(){
		
		lista = new LinkedList<Usuario>();
		
		try {
			Usuario u1 = new Usuario();
			
			//Objeto 1 
			u1.setId(1);
			u1.setNombre("Mauricio");
			u1.setUsername("Mau13");
			u1.setEmail("mauricio@gmail.com");
			u1.setPassword("12345");
			u1.setEstatus(1);
			u1.setFechaRegistro(LocalDate.parse("12-01-2021",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lista.add(u1);
			
			//Objeto 2 
			Usuario u2 = new Usuario();
			u2.setId(2);
			u2.setNombre("Frida");
			u2.setUsername("Frida12");
			u2.setEmail("frida@gmail.com");
			u2.setPassword("12345");
			u2.setEstatus(0);
			u2.setFechaRegistro(LocalDate.parse("12-01-2021",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lista.add(u2);
			
			//Objeto 3
			Usuario u3 = new Usuario();
			u3.setId(3);
			u3.setNombre("Alejandro");
			u3.setUsername("Ale234");
			u3.setEmail("alejandro@gmail.com");
			u3.setPassword("45678");
			u3.setEstatus(1);
			u3.setFechaRegistro(LocalDate.parse("12-01-2021",DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			lista.add(u3);
			
			
			
			
		}catch(DateTimeParseException ex) {
			System.out.println("Error: "+ex.getMessage());
			
		}
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		// TODO Auto-generated method stub
		for(Usuario usuario: lista) {
			if (usuario.getId() == idUsuario) {
			
				return usuario;
			}
		}
		
		return null;
	}

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		lista.add(usuario);

	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		lista.remove(idUsuario);
	}

}
