package es.uoc.icl.controller;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uoc.icl.domain.Jwt;
import es.uoc.icl.domain.Login;
import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.Usuario;
import es.uoc.icl.service.AuthService;
import es.uoc.icl.service.NegocioService;
import es.uoc.icl.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginRestController {

//	@Autowired
//	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	NegocioService negocioService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/registroUsuario")
	public ResponseEntity<?> registro(@RequestBody Usuario nuevoUsuario) {
		if (usuarioService.existeUsuario(nuevoUsuario.getId())) {
			return new ResponseEntity<String>("El usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		if (usuarioService.existeUsuarioConEmailODocumento(nuevoUsuario)) {
			return new ResponseEntity<String>("El email ya existe", HttpStatus.BAD_REQUEST);
		}

		try {
			if(nuevoUsuario.getEmail() != null)
				authService.enviarEmailBienvenida(nuevoUsuario.getEmail());
		} catch (Exception e) {
			System.out.println("Error enviando correo: "+e.getMessage());
		}
		return ResponseEntity.ok(authService.registroUsuario(nuevoUsuario));
	}
	
	@PostMapping("/registroNegocio")
	public ResponseEntity<?> registroNegocios(@RequestBody Negocio nuevoNegocio) {
		if (negocioService.existeNegocio(nuevoNegocio.getId())) {
			return new ResponseEntity<String>("El negocio ya existe", HttpStatus.BAD_REQUEST);
		}
		if (negocioService.existeNegocioConEmailOCif(nuevoNegocio)) {
			return new ResponseEntity<String>("El email ya existe", HttpStatus.BAD_REQUEST);
		}

		try {
			if(nuevoNegocio.getEmail() != null)
				authService.enviarEmailBienvenida(nuevoNegocio.getEmail());
		} catch (Exception e) {
			System.out.println("Error enviando correo: "+e.getMessage());
		}
		return ResponseEntity.ok(authService.registroNegocio(nuevoNegocio));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Jwt> login (@RequestBody Login login){
		Optional<Negocio> negocio = negocioService.getNegocioConEmail(login.getEmail());
		Optional<Usuario> usuario = usuarioService.getUsuarioConEmail(login.getEmail());
		Jwt loginAux = null;
		if(usuario.isPresent()) {
			loginAux = authService.loginUsuario(login);
			return ResponseEntity.ok(loginAux);
		}else if(negocio.isPresent()) {
			loginAux = authService.loginNegocio(login);
			return ResponseEntity.ok(loginAux);
		}else {
			return new ResponseEntity<Jwt>(loginAux, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/email-password")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> enviarEmailCambioContrasena(@RequestBody Usuario us) {
		try {
			Usuario usuario = usuarioService.getUsuarioConEmail(us.getEmail()).get();
			if(usuario == null) {
				return new ResponseEntity("No existe un usuario con esas credenciales", HttpStatus.BAD_REQUEST);
			}
			
			String token = UUID.randomUUID().toString();
			authService.enviarEmailCambioContrasena(usuario, token);
			return new ResponseEntity(Collections.singletonMap("mensaje", "Correo enviado"), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error enviando correo: "+e.getMessage());
			return new ResponseEntity(Collections.singletonMap("mensaje", "No se ha podido enviar el correo"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/cambio-contrasena")
	public ResponseEntity<?> cambiarContrasena(@RequestBody Usuario aux){

		//VALIDAR ESTO EN EL FRONTEND 
//		if(!dto.getPassword().equals(dto.getConfirmPassword())) {
//			return new ResponseEntity("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST);
//		}
		
		Usuario usuario = usuarioService.getUsuario(aux.getId());
		if(usuario == null) {
			return new ResponseEntity("No existe el usuario", HttpStatus.BAD_REQUEST);
		}
		
		usuarioService.modificarContrasenaUsuario(aux.getId(), passwordEncoder.encode(aux.getContrasena()));
		return new ResponseEntity(Collections.singletonMap("mensaje", "Contraseña actualizada"), HttpStatus.OK);
	}
}
