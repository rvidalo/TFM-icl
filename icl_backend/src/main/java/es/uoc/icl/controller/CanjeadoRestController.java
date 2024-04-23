package es.uoc.icl.controller;

import java.util.Collections;
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

import es.uoc.icl.domain.Canjeado;
import es.uoc.icl.service.CanjeadoService;

@RestController
@RequestMapping("/api/canjeados")
@CrossOrigin
public class CanjeadoRestController {

	@Autowired
	CanjeadoService canjeadoService;
	
	@GetMapping("")
	public ResponseEntity<List<Canjeado>> getCanjeados() {
		List<Canjeado> canjeados = canjeadoService.getCanjeados();
		return new ResponseEntity<List<Canjeado>>(canjeados, HttpStatus.OK);
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<List<Canjeado>> getCanjeadoDeUsuario(@RequestParam(required = true) Integer idUsuario) {
		List<Canjeado> canjeados = canjeadoService.getCanjeadosDeUsuario(idUsuario);
		return new ResponseEntity<List<Canjeado>>(canjeados, HttpStatus.OK);
	}

	@GetMapping("/negocio")
	public ResponseEntity<List<Canjeado>> getCanjeadoDeNegocio(@RequestParam(required = true) Integer idNegocio) {
		List<Canjeado> canjeados = canjeadoService.getCanjeadosDeUsuario(idNegocio);
		return new ResponseEntity<List<Canjeado>>(canjeados, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/id")
	public ResponseEntity<Canjeado> getCanjeado(@RequestParam (required = true) Integer idCanjeado) {
		Canjeado canjeado = canjeadoService.getCanjeado(idCanjeado);
		if(canjeado == null) {
			return new ResponseEntity("El canjeado no existe", HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<Canjeado>(canjeado, HttpStatus.OK);
	}

	@PostMapping("/nuevo")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> nuevoCanjeado (@RequestBody Canjeado canjeado) {
		canjeadoService.guardarCanjeado(canjeado);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Canjeado creado"), HttpStatus.CREATED);
	}
	
}
