package es.uoc.icl.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uoc.icl.domain.Perfil;
import es.uoc.icl.domain.Usuario;
import es.uoc.icl.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("")
	public ResponseEntity<List<Usuario>> getUsuarios() {
		List<Usuario> usuarios = usuarioService.getUsuarios();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("admin")
	public ResponseEntity<List<Usuario>> getAdministradores() {
		List<Usuario> administradores = usuarioService.getAdministradores();
		return new ResponseEntity<List<Usuario>>(administradores, HttpStatus.OK);
	}
	
	@GetMapping("/vale")
	public ResponseEntity<List<Usuario>> getUsuariosConVale() {
		List<Usuario> usuarios = usuarioService.getUsuariosConVale();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/perfil")
	public ResponseEntity<Perfil> getUsuario(@RequestParam String email) {
		Usuario usuario = usuarioService.getUsuarioConEmail(email).orElseThrow();
		if(usuario == null) {
			return new ResponseEntity("El usuario no existe", HttpStatus.BAD_REQUEST);
		}
		Perfil perfil = new Perfil(usuario);
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}
	
	@PostMapping("/modificar")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> modificarUsuario (@RequestBody Perfil perfil) {
		Usuario usuario = usuarioService.getUsuario(perfil.getId());
		if(usuario == null) {
			return new ResponseEntity("El usuario no existe", HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existeUsuarioConEmailODocumento(usuario)) {
			return new ResponseEntity("El usuario con email " +usuario.getEmail()+ " o NIF " + usuario.getDocumento() +" ya existe", HttpStatus.BAD_REQUEST);
		}
		usuarioService.modificarUsuario(perfil);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Usuario modificado"), HttpStatus.OK);
	}
}
