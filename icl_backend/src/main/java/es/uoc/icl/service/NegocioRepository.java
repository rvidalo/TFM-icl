package es.uoc.icl.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.PerfilNegocio;
import es.uoc.icl.domain.TipoNegocio;

@Mapper
public interface NegocioRepository {
	Negocio getNegocio(Integer idNegocio);
	Negocio getNegocioConToken(String token);
	List<Negocio> getNegocios();
	List<Negocio> getNegociosConEmailOCif(Negocio negocio);
	List<Negocio> getNegociosConTipo(Integer idTipoNegocio);
	void guardarNegocio(Negocio negocio);
	void modificarPerfilNegocio(PerfilNegocio perfil);
	void modificarNegocio(Negocio negocio);
	List<TipoNegocio> getTiposNegocio();
	List<Negocio> getNegociosAceptados();
	Optional<Negocio> getNegocioConEmail(String email);
}
