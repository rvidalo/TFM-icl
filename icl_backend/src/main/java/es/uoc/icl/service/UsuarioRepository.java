package es.uoc.icl.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import es.uoc.icl.domain.Usuario;

@Mapper
public interface UsuarioRepository {
	Usuario getUsuario(Integer idUsuario);
	Usuario getUsuarioConEmail(String email);
	Usuario getUsuarioConToken(String token);
	List<Usuario> getUsuarios();
	List<Usuario> getAdministradores();
	void guardarUsuario(Usuario usuario);
	void modificarUsuario(Usuario usuario);
}