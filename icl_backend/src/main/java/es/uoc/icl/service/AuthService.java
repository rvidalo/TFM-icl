package es.uoc.icl.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Email;
import es.uoc.icl.domain.Jwt;
import es.uoc.icl.domain.Login;
import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.Usuario;
import es.uoc.icl.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
	EmailService emailService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	NegocioService negocioService;
	@Autowired
	JwtService jwtService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Value("${url.frontend}")
	private String urlFront;
	
	private static final String PLANTILLA_BIENVENIDA = "email/bienvenida";
	private static final String PLANTILLA_CAMBIO_CONTRASENA = "email/cambio-contrasena";
	private static final String URL_CAMBIO_CONTRASENA = "cambio-contrasena/";
	
	public void enviarEmailBienvenida(String correoElectronico) throws Exception {
		Email email = new Email();
		email.setDestino("ricardovidalortiz@gmail.com");//correoElectronico);
		email.setTitulo("Bienvenida a la plataforma de Impulso del comercio local del Ayuntamiento de Chiclana");
		Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("url", urlFront);
		emailService.enviarEmailPlantilla(email, modelo, PLANTILLA_BIENVENIDA);
	}
	
	public void enviarEmailCambioContrasena(Usuario usuario, String token) throws Exception {
		Email email = new Email();
		email.setDestino("ricardovidalortiz@gmail.com");//usuario.getEmail());
		email.setTitulo("Cambio de contraseña en la plataforma de Impulso del comercio local del Ayuntamiento de Chiclana");
		Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("idUsuario", usuario.getId());
		modelo.put("url", urlFront + URL_CAMBIO_CONTRASENA + token);
		emailService.enviarEmailPlantilla(email, modelo, PLANTILLA_CAMBIO_CONTRASENA);
	}

	public Jwt registroUsuario(Usuario nuevoUsuario) {
		nuevoUsuario.setContrasena(passwordEncoder.encode(nuevoUsuario.getContrasena()));
		usuarioService.guardarUsuario(nuevoUsuario);
		
		return Jwt.builder()
				.token(jwtService.getToken(nuevoUsuario))
				.build();
	}
	
	public Jwt registroNegocio(Negocio nuevoNegocio) {
		nuevoNegocio.setContrasena(passwordEncoder.encode(nuevoNegocio.getContrasena()));
		negocioService.guardarNegocio(nuevoNegocio);
		
		return Jwt.builder()

				.token(jwtService.getToken(nuevoNegocio))
				.build();
	}

	public Jwt loginUsuario(Login login) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getContrasena()));
		UserDetails usuario = usuarioService.getUsuarioConEmail(login.getEmail()).orElseThrow();
		return Jwt.builder()
				.token(jwtService.getToken(usuario))
				.build();
	}
	
	public Jwt loginNegocio(Login login) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getContrasena()));
		UserDetails negocio = negocioService.getNegocioConEmail(login.getEmail()).orElseThrow();
		return Jwt.builder()
				.token(jwtService.getToken(negocio))
				.build();
	}
	
}
