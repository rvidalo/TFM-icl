package es.uoc.icl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.TipoNegocio;

@Service
@Transactional
public class NegocioService {
	
	@Autowired
	NegocioRepository negocioRepository;

	public List<Negocio> getNegocios(){
		return negocioRepository.getNegocios();
	}
	
	public List<Negocio> getNegociosConTipo(Integer idTipoNegocio){
		return negocioRepository.getNegociosConTipo(idTipoNegocio);
	}
	
	public Negocio getNegocio (Integer idNegocio) {
		return negocioRepository.getNegocio(idNegocio);
	}
	
	public boolean existeNegocio(Integer idNegocio) {
		return negocioRepository.getNegocio(idNegocio) != null;
	}
	
	public Negocio getNegocioConEmail (String email) {
		return negocioRepository.getNegocioConEmail(email);
	}
	
	public boolean existeNegocioConEmail(String email) {
		return negocioRepository.getNegocioConEmail(email) != null;
	}
	
	public Negocio getNegocioConToken (String token) {
		return negocioRepository.getNegocioConToken(token);
	}
	
	public void modificarNegocio(Negocio negocio) {
		negocioRepository.modificarNegocio(negocio);
	}
	
	public void guardarNegocio(Negocio negocio) {
		negocioRepository.guardarNegocio(negocio);
	}

	public List<TipoNegocio> getTipos() {
		return negocioRepository.getTiposNegocio();
	}

	public List<Negocio> getNegociosAceptados() {
		return negocioRepository.getNegociosAceptados();
	}
	
}
