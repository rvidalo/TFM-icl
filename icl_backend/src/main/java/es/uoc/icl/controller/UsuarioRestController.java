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

import es.uoc.icl.domain.Usuario;
import es.uoc.icl.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
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
	@GetMapping("/id")
	public ResponseEntity<Usuario> getUsuario(@RequestParam (required = true) Integer idUsuario) {
		Usuario usuario = usuarioService.getUsuario(idUsuario);
		if(usuario == null) {
			return new ResponseEntity("El usuario no existe", HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> nuevoUsuario (@RequestBody Usuario usuario) {
		if(usuarioService.existeUsuarioConEmailODocumento(usuario)) {
			return new ResponseEntity("El usuario con email " +usuario.getEmail()+ " o NIF " + usuario.getDocumento() +" ya existe", HttpStatus.BAD_REQUEST);
		}
		usuarioService.guardarUsuario(usuario);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Usuario creado"), HttpStatus.CREATED);
	}
	
	@PostMapping("/modificar")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> modificarUsuario (@RequestBody Usuario usuario) {
		if(!usuarioService.existeUsuario(usuario.getId())) {
			return new ResponseEntity("El usuario no existe", HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existeUsuarioConEmailODocumento(usuario)) {
			return new ResponseEntity("El usuario con email " +usuario.getEmail()+ " o NIF " + usuario.getDocumento() +" ya existe", HttpStatus.BAD_REQUEST);
		}
		usuarioService.modificarUsuario(usuario);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Usuario modificado"), HttpStatus.OK);
	}
}
