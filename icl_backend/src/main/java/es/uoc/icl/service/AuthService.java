package es.uoc.icl.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Email;
import es.uoc.icl.domain.Usuario;

@Service
@Transactional
public class AuthService {
	
	@Autowired
	EmailService emailService;
	
	@Value("${url.frontend}")
	private String urlFront;
	
	private static final String PLANTILLA_BIENVENIDA = "email/bienvenida";
	private static final String PLANTILLA_CAMBIO_CONTRASENA = "email/cambio-contrasena";
	private static final String URL_CAMBIO_CONTRASENA = "cambio-contrasena/";
	
	public void enviarEmailBienvenida(String correoElectronico) throws Exception {
		Email email = new Email();
		email.setDestino(correoElectronico);
		email.setTitulo("Bienvenida a la plataforma de Impulso del comercio local del Ayuntamiento de Chiclana");
		Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("url", urlFront);
		emailService.enviarEmailPlantilla(email, modelo, PLANTILLA_BIENVENIDA);
	}
	
	public void enviarEmailCambioContrasena(Usuario usuario, String token) throws Exception {
		Email email = new Email();
		email.setDestino(usuario.getEmail());
		email.setTitulo("Cambio de contraseña en la plataforma de Impulso del comercio local del Ayuntamiento de Chiclana");
		Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("idUsuario", usuario.getId());
		modelo.put("url", urlFront + URL_CAMBIO_CONTRASENA + token);
		emailService.enviarEmailPlantilla(email, modelo, PLANTILLA_CAMBIO_CONTRASENA);
	}
	
}
