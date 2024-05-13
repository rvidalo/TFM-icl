package es.uoc.icl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Perfil;
import es.uoc.icl.domain.Usuario;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> getUsuarios(){
		return usuarioRepository.getUsuarios();
	}
	
	public Optional<Usuario> getUsuarioConEmail(String email){
		try {
			return usuarioRepository.getUsuarioConEmail(email);
		}catch (Exception e) {
			System.out.println("Usuario con email " + email +" no encontrado");
			return null;
		}
	}
	
	public List<Usuario> getAdministradores(){
		return usuarioRepository.getAdministradores();
	}
	
	public Usuario getUsuario (Integer idUsuario) {
		return usuarioRepository.getUsuario(idUsuario);
	}
	
	public boolean existeUsuario(Integer idUsuario) {
		return usuarioRepository.getUsuario(idUsuario) != null;
	}
	
	public List<Usuario> getUsuariosConVale() {
		return usuarioRepository.getUsuariosConVale();
	}
	
	public Usuario getUsuarioConToken (String token) {
		return usuarioRepository.getUsuarioConToken(token);
	}
	
	public void modificarUsuario(Perfil perfil) {
		usuarioRepository.modificarUsuario(perfil);
	}
	
	public void guardarUsuario(Usuario usuario) {
		usuarioRepository.guardarUsuario(usuario);
	}

	public boolean existeUsuarioConEmailODocumento(Usuario usuario) {
		List<Usuario> usuariosConEmailODocumento = usuarioRepository.getUsuariosConEmailODocumento(usuario);
		for (Usuario aux : usuariosConEmailODocumento) {
			if(usuario.getId() != aux.getId()) {
				return true;
			}
		}
		return false;
	}

	public void modificarContrasenaUsuario(Integer id, String contrasena) {
		usuarioRepository.modificarContrasena(id, contrasena);
	}

}
