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
	
	public boolean existeNegocioConEmailOCif(Negocio negocio) {
		List<Negocio> negociosConEmailOCif = negocioRepository.getNegociosConEmailOCif(negocio);
		for (Negocio aux : negociosConEmailOCif) {
			if(negocio.getId() != aux.getId()) {
				return true;
			}
		}
		return false;
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

	public Negocio getNegocioConEmail(String email) {
		return negocioRepository.getNegocioConEmail(email);
	}

	public void aceptar(Negocio negocio) {
		negocio.setEstado(1);
		negocioRepository.modificarNegocio(negocio);
	}

	public void rechazar(Negocio negocio) {
		negocio.setEstado(2);
		negocioRepository.modificarNegocio(negocio);
	}
	
}
