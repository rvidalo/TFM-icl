package es.uoc.icl.service;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.TipoNegocio;

@Mapper
public interface NegocioRepository {
	Negocio getNegocio(Integer idNegocio);
	Negocio getNegocioConToken(String token);
	List<Negocio> getNegocios();
	List<Negocio> getNegociosConEmailOCif(Negocio negocio);
	List<Negocio> getNegociosConTipo(Integer idTipoNegocio);
	void guardarNegocio(Negocio negocio);
	void modificarNegocio(Negocio negocio);
	List<TipoNegocio> getTiposNegocio();
	List<Negocio> getNegociosAceptados();
	Negocio getNegocioConEmail(String email);
}
