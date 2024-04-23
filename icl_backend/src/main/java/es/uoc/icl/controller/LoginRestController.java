//package es.uoc.icl.controller;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import es.uoc.icl.domain.Usuario;
//import es.uoc.icl.service.UsuarioService;
//
//public class LoginRestController {
//
//
//	@RestController
//	@RequestMapping("/auth")
//	@CrossOrigin
//	public class AuthController {
//
//		@Autowired
//		PasswordEncoder passwordEncoder;
//
//		@Autowired
//		AuthenticationManager authenticationManager;
//
//		@Autowired
//		UsuarioService usuarioService;
//
//		@Autowired
//		RolService rolService;
//		
//		@Autowired
//		AuthService authService;
//
//		@Autowired
//		JwtProvider jwtProvider;
//		
//		@PostMapping("/registro")
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario) {
//			if (usuarioService.existsUsuarioById(nuevoUsuario.getId())) {
//				return new ResponseEntity("El usuario ya existe", HttpStatus.BAD_REQUEST);
//			}
//			if (usuarioService.existsUsuarioByEmail(nuevoUsuario.getEmail())) {
//				return new ResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);
//			}
//
//			Usuario usuario = new Usuario(nuevoUsuario.getId(), nuevoUsuario.getNombre(), nuevoUsuario.getApellido1(),
//					nuevoUsuario.getApellido2(), passwordEncoder.encode(nuevoUsuario.getPassword()),
//					nuevoUsuario.getEmail());
//			
//			List<Rol> roles = new ArrayList<Rol>();
//			roles.add(rolService.getRolByNombre("ROLE_USER"));
//			for(String rolNombre : nuevoUsuario.getRoles()) {
//				Rol rol = rolService.getRolByNombre(rolNombre);
//				if(rol == null) {
//					return new ResponseEntity("El rol: "+rolNombre+" no existe", HttpStatus.BAD_REQUEST);
//				}
//				roles.add(rol);
//			}
//			usuario.setRoles(roles);
//			usuarioService.save(usuario);
//			try {
//				authService.enviarEmailBienvenida(usuario.getEmail());
//			} catch (Exception e) {
//				System.out.println("Error enviando correo: "+e.getMessage());
//			}
//			return new ResponseEntity(Collections.singletonMap("mensaje", "Usuario creado"), HttpStatus.CREATED);
//		}
//		
//		
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		@PostMapping("/login")
//		public ResponseEntity<JwtDto> login (@RequestBody LoginUsuario loginUsuario){
//			Authentication authentication = null;
//			try {
//				authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getId(), loginUsuario.getPassword()));
//			} catch (Exception e) {
//				return new ResponseEntity(Collections.singletonMap("mensaje", "Credenciales incorrectas"), HttpStatus.BAD_REQUEST);
//			}
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			String jwt = jwtProvider.generateToken(authentication);
//			JwtDto jwtDto = new JwtDto(jwt);
//			return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
//		}
//		
//		@SuppressWarnings({ "unchecked", "rawtypes" })
//		@PostMapping("/refresh")
//		public ResponseEntity<JwtDto> refreshToken (@RequestBody JwtDto jwtDto){
//			String token;
//			try {
//				token = jwtProvider.refreshToken(jwtDto);
//			} catch (Exception e) {
//				return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
//			}
//			JwtDto jwt = new JwtDto(token);
//			return new ResponseEntity<JwtDto>(jwt, HttpStatus.OK);
//		}
//		
//		@PostMapping("/email-password")
//		@SuppressWarnings({ "unchecked", "rawtypes" })
//		public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailPasswordDTO dto) {
//			try {
//			Usuario usuario = usuarioService.getUsuarioByIdOrEmail(dto.getEmail());
//			if(usuario == null) {
//				return new ResponseEntity("No existe un usuario con esas credenciales", HttpStatus.BAD_REQUEST);
//			}
//			
//			String tokenPassword = UUID.randomUUID().toString();
//			usuario.setTokenPassword(tokenPassword);
//			
//			usuarioService.update(usuario);
//			dto.setIdUsuario(usuario.getId());
//			dto.setTokenPassword(tokenPassword);
//			authService.enviarEmailPassword(dto);
//			return new ResponseEntity(Collections.singletonMap("mensaje", "Correo enviado"), HttpStatus.OK);
//			} catch (Exception e) {
//				System.out.println("Error enviando correo: "+e.getMessage());
//				return new ResponseEntity(Collections.singletonMap("mensaje", "No se ha podido enviar el correo"), HttpStatus.BAD_REQUEST);
//			}
//		}
//		
//		@SuppressWarnings({ "unchecked", "rawtypes" })
//		@PostMapping("/cambiar-password")
//		public ResponseEntity<?> changePassword(@RequestBody CambiarPasswordDTO dto){
//			
//			if(!dto.getPassword().equals(dto.getConfirmPassword())) {
//				return new ResponseEntity("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST);
//			}
//			
//			Usuario usuario = usuarioService.getUsuarioByTokenPassword(dto.getTokenPassword());
//			if(usuario == null) {
//				return new ResponseEntity("No existe un usuario con esas credenciales", HttpStatus.BAD_REQUEST);
//			}
//			
//			String newPassword = passwordEncoder.encode(dto.getPassword());
//			usuario.setPassword(newPassword);
//			usuario.setTokenPassword(null);
//			usuarioService.update(usuario);
//			return new ResponseEntity(Collections.singletonMap("mensaje", "Contraseña actualizada"), HttpStatus.OK);
//		}
//	}
