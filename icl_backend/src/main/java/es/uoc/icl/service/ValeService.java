package es.uoc.icl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Usuario;
import es.uoc.icl.domain.Vale;

@Service
@Transactional
public class ValeService {
	
	@Autowired
	ValeRepository valeRepository;

	public List<Vale> getVales(){
		return valeRepository.getVales();
	}
	
	public Vale getVale (Integer idVale) {
		return valeRepository.getVale(idVale);
	}

	public Vale getValeDeUsuario (Integer idUsuario) {
		return valeRepository.getValeDeUsuario(idUsuario);
	}
	
	public void modificarVale(Vale vale) {
		valeRepository.modificarVale(vale);
	}
	
	public void guardarVale(Vale vale) {
		valeRepository.guardarVale(vale);
	}

	public boolean existeValeDeUsuario(Usuario usuario) {
		return getValeDeUsuario(usuario.getId()) != null;
	}

	public boolean existeVale(Integer id) {
		return getVale(id) != null;
	}
}
