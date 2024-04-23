package es.uoc.icl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Canjeado;

@Service
@Transactional
public class CanjeadoService {
	
	@Autowired
	CanjeadoRepository canjeadoRepository;

	public List<Canjeado> getCanjeados(){
		return canjeadoRepository.getCanjeados();
	}
	
	public Canjeado getCanjeado (Integer idCanjeado) {
		return canjeadoRepository.getCanjeado(idCanjeado);
	}

	public List<Canjeado> getCanjeadosDeUsuario (Integer idUsuario) {
		return canjeadoRepository.getCanjeadosDeUsuario(idUsuario);
	}
	
	public List<Canjeado> getCanjeadosDeNegocio (Integer idNegocio) {
		return canjeadoRepository.getCanjeadosDeNegocio(idNegocio);
	}
	
	public void guardarCanjeado(Canjeado canjeado) {
		canjeadoRepository.guardarCanjeado(canjeado);
	}
	
}
