package es.uoc.icl.controller;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uoc.icl.domain.Jwt;
import es.uoc.icl.domain.Usuario;
import es.uoc.icl.security.TokenUtils;
import es.uoc.icl.service.AuthService;
import es.uoc.icl.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginRestController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/registro")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> nuevo(@RequestBody Usuario nuevoUsuario) {
		if (usuarioService.existeUsuario(nuevoUsuario.getId())) {
			return new ResponseEntity("El usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		if (usuarioService.existeUsuarioConEmailODocumento(nuevoUsuario)) {
			return new ResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);
		}

		nuevoUsuario.setContrasena(passwordEncoder.encode(nuevoUsuario.getContrasena()));
		usuarioService.guardarUsuario(nuevoUsuario);
		try {
			authService.enviarEmailBienvenida(nuevoUsuario.getEmail());
		} catch (Exception e) {
			System.out.println("Error enviando correo: "+e.getMessage());
		}
		return new ResponseEntity(Collections.singletonMap("mensaje", "Usuario creado"), HttpStatus.CREATED);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/login")
	public ResponseEntity<Jwt> login (@RequestBody Usuario loginUsuario){
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getId(), loginUsuario.getContrasena()));
		} catch (Exception e) {
			return new ResponseEntity(Collections.singletonMap("mensaje", "Credenciales incorrectas"), HttpStatus.BAD_REQUEST);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Jwt jwt = new Jwt(TokenUtils.createToken(usuario.getNombre(), usuario.getEmail()));
		return new ResponseEntity<Jwt>(jwt, HttpStatus.OK);
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@PostMapping("/refresh")
//	public ResponseEntity<Jwt> refreshToken (@RequestBody Jwt jwt){
//		String token;
//		try {
//			token = TokenUtils.refreshToken(jwt);
//		} catch (Exception e) {
//			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
//		}
//		Jwt jwt = new Jwt(token);
//		return new ResponseEntity<Jwt>(jwt, HttpStatus.OK);
//	}
	
	@PostMapping("/email-password")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> enviarEmailCambioContrasena(@RequestBody Usuario us) {
		try {
			Usuario usuario = usuarioService.getUsuarioConEmail(us.getEmail());
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
		
		String newPassword = passwordEncoder.encode(aux.getContrasena());
		usuario.setContrasena(newPassword);
		usuarioService.modificarUsuario(usuario);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Contraseña actualizada"), HttpStatus.OK);
	}
}
