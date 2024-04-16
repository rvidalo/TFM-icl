package es.uoc.icl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Usuario;
import es.uoc.icl.domain.Vale;

@Service
@Transactional
public class CanjeadoService {
	
	@Autowired
	ValeRepository valeRepository;

	public List<Vale> getVales(){
		return valeRepository.getVales();
	}
	
	public Vale getVale (Integer idVale) {
		return valeRepository.getVale(idVale);
	}

	public Vale getValeDeUsuario (Usuario usuario) {
		return valeRepository.getValeDeUsuario(usuario);
	}
	
	public void modificarVale(Vale vale) {
		valeRepository.modificarVale(vale);
	}
	
	public void guardarVale(Vale vale) {
		valeRepository.guardarVale(vale);
	}
	
}
