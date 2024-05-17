package es.uoc.icl.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uoc.icl.domain.CanjearVale;
import es.uoc.icl.service.CanjeadoService;

@RestController
@RequestMapping("/api/canjeados")
@CrossOrigin
public class CanjeadoRestController {

	@Autowired
	CanjeadoService canjeadoService;
	
	@GetMapping("")
	public ResponseEntity<List<CanjearVale>> getCanjeados() {
		List<CanjearVale> canjeados = canjeadoService.getCanjeados();
		return new ResponseEntity<List<CanjearVale>>(canjeados, HttpStatus.OK);
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<List<CanjearVale>> getCanjeadoDeUsuario(@RequestParam String email) {
		List<CanjearVale> canjeados = canjeadoService.getCanjeadosDeUsuario(email);
		return new ResponseEntity<List<CanjearVale>>(canjeados, HttpStatus.OK);
	}

	@GetMapping("/negocio")
	public ResponseEntity<List<CanjearVale>> getCanjeadoDeNegocio(@RequestParam String email) {
		List<CanjearVale> canjeados = canjeadoService.getCanjeadosDeNegocio(email);
		return new ResponseEntity<List<CanjearVale>>(canjeados, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/id")
	public ResponseEntity<CanjearVale> getCanjeado(@RequestParam (required = true) Integer idCanjeado) {
		CanjearVale canjeado = canjeadoService.getCanjeado(idCanjeado);
		if(canjeado == null) {
			return new ResponseEntity("El canjeado no existe", HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<CanjearVale>(canjeado, HttpStatus.OK);
	}

	@PostMapping("/nuevo")
	public ResponseEntity<BigDecimal> nuevoCanjeado (@RequestBody CanjearVale canjearVale) {
		BigDecimal descuento = canjeadoService.guardarCanjeado(canjearVale);
		return new ResponseEntity<BigDecimal>(descuento, HttpStatus.OK);
	}
	
}
