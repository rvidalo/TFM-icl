package es.uoc.icl.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import es.uoc.icl.domain.Canjeado;

@Mapper
public interface CanjeadoRepository {
	Canjeado getCanjeado(Integer idCanjeado);
	List<Canjeado> getCanjeados();
	List<Canjeado> getCanjeadosDeUsuario(Integer idUsuario);
	List<Canjeado> getCanjeadosDeNegocio(Integer idNegocio);
	void guardarCanjeado(Canjeado canjeado);
}
