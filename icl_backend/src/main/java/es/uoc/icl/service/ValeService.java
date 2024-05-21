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
	@Autowired
	UsuarioService usuarioService;

	public List<Vale> getVales(){
		return valeRepository.getVales();
	}
	
	public Vale getVale (Integer idVale) {
		return valeRepository.getVale(idVale);
	}
	
	public Vale getValeDeUsuario (String email) {
		return valeRepository.getValeDeUsuario(email);
	}
	
	public void nuevoVale(String email) {
		Usuario usuario = usuarioService.getUsuarioConEmail(email).get();
		Vale vale = new Vale(usuario);		
		valeRepository.guardarVale(vale);
	}

	public boolean existeValeDeUsuario(String email) {
		return getValeDeUsuario(email) != null;
	}

	public boolean existeVale(Integer id) {
		return getVale(id) != null;
	}

	public Vale getValeConQr(String qr) {
		return valeRepository.getValeConQr(qr);
	}

}
