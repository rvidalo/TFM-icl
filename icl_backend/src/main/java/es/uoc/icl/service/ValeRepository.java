package es.uoc.icl.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import es.uoc.icl.domain.Usuario;
import es.uoc.icl.domain.Vale;

@Mapper
public interface ValeRepository {
	Vale getVale(Integer idVale);
	Vale getValeDeUsuario(Usuario usuario);
	List<Vale> getVales();
	void guardarVale(Vale vale);
	void modificarVale(Vale vale);
}
