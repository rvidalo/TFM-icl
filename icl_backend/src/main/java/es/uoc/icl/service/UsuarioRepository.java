package es.uoc.icl.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import es.uoc.icl.domain.Perfil;
import es.uoc.icl.domain.Usuario;

@Mapper
public interface UsuarioRepository {
	Usuario getUsuario(Integer idUsuario);
	Usuario getUsuarioConToken(String token);
	List<Usuario> getUsuarios();
	List<Usuario> getUsuariosConEmailODocumento(Usuario usuario);
	List<Usuario> getUsuariosConVale();
	List<Usuario> getAdministradores();
	void guardarUsuario(Usuario usuario);
	void modificarUsuario(Perfil perfil);
	Optional<Usuario> getUsuarioConEmail(String email);
	void modificarContrasena(Integer id, String contrasena);
}
