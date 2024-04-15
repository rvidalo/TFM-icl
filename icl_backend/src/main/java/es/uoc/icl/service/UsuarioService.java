package es.uoc.icl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Usuario;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> getUsuarios(){
		return usuarioRepository.getUsuarios();
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
	
	public Usuario getUsuarioConEmail (String email) {
		return usuarioRepository.getUsuarioConEmail(email);
	}
	
	public boolean existeUsuarioConEmail(String email) {
		return usuarioRepository.getUsuarioConEmail(email) != null;
	}
	
	public Usuario getUsuarioConToken (String token) {
		return usuarioRepository.getUsuarioConToken(token);
	}
	
	public void modificarUsuario(Usuario usuario) {
		usuarioRepository.modificarUsuario(usuario);
	}
	
	public void guardarUsuario(Usuario usuario) {
		usuarioRepository.guardarUsuario(usuario);
	}
	
}
