package es.uoc.icl.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import es.uoc.icl.domain.Canjeado;
import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.Usuario;

@Mapper
public interface CanjeadoRepository {
	Canjeado getCanjeado(Integer idCanjeado);
	List<Canjeado> getCanjeados();
	List<Canjeado> getCanjeadoDeUsuario(Usuario usuario);
	List<Canjeado> getCanjeadoDeNegocio(Negocio negocio);
	void guardarCanjeado(Canjeado canjeado);
	void modificarCanjeado(Canjeado canjeado);
}
