package es.uoc.icl.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uoc.icl.domain.Canjeado;
import es.uoc.icl.domain.CanjearVale;
import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.Vale;

@Service
@Transactional
public class CanjeadoService {
	
	@Autowired
	CanjeadoRepository canjeadoRepository;
	
	@Autowired
	NegocioService negocioService;

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	ValeService valeService;

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
	
	public BigDecimal guardarCanjeado(CanjearVale canjearVale) {
		Negocio negocio = negocioService.getNegocioConEmail(canjearVale.getEmailNegocio()).orElseThrow();
		BigDecimal totalCanjeadoNegocio = negocioService.getTotalCanjeado(negocio);
		Vale valeUsuario = valeService.getValeConQr(canjearVale.getQr());
		BigDecimal totalCanjeadoUsuario = valeService.getTotalCanjeado(valeUsuario);
		BigDecimal restoNegocio = negocio.getValorTotal().subtract(totalCanjeadoNegocio);
		BigDecimal restoUsuario = valeUsuario.getValorTotal().subtract(totalCanjeadoUsuario);
		
		//Obtiene el 50% redondeando hacía abajo en caso ded ser necesario en el segundo decimal
		BigDecimal descuentoMitad = canjearVale.getTotal().multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_DOWN);
		//Tenemos en cuenta los totales disponibles tanto del usuario como del negocios
		BigDecimal descuento = restoNegocio.min(restoUsuario).min(descuentoMitad);
		
		canjearVale.setDescuento(descuento);
		Canjeado canjeado = new Canjeado(canjearVale, valeUsuario, negocio);
		canjeadoRepository.guardarCanjeado(canjeado);
		
		return descuento;
	}

}
