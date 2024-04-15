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

import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.TipoNegocio;
import es.uoc.icl.service.NegocioService;

@RestController
@RequestMapping("/api/negocios")
@CrossOrigin
public class NegocioController {

	@Autowired
	NegocioService negocioService;
	
	@GetMapping
	public ResponseEntity<List<Negocio>> getNegociosByGrupo(
			@RequestParam(required = false) Integer idTipoNegocio) {
		
		List<Negocio> negocios = negocioService.getNegociosConTipo(idTipoNegocio);
		return new ResponseEntity<List<Negocio>>(negocios, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("negocio")
	public ResponseEntity<Negocio> getNegocio(@RequestParam int idNegocio) {
		Negocio negocio = negocioService.getNegocio(idNegocio);
		if(negocio == null) {
			return new ResponseEntity("El negocio no existe", HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<Negocio>(negocio, HttpStatus.OK);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@GetMapping("/email")
	public ResponseEntity<Negocio> getNegocioConEmail(@RequestParam String email) {
		Negocio negocio = negocioService.getNegocioConEmail(email);
		if(negocio == null) {
			return new ResponseEntity("El negocio con email " +email+ " no existe", HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<Negocio>(negocio, HttpStatus.OK);
	}
	
	@GetMapping("/tipos")
	public ResponseEntity<List<TipoNegocio>> getTiposNegocio() {
		List<TipoNegocio> tipos = negocioService.getTipos();
		return new ResponseEntity<List<TipoNegocio>>(tipos, HttpStatus.OK);
	}

	@PostMapping("nuevo")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> nuevoNegocio (@RequestBody Negocio negocio) {
		if(negocioService.existeNegocioConEmail(negocio.getEmail())) {
			return new ResponseEntity("El negocio ya existe", HttpStatus.BAD_REQUEST);
		}
		negocioService.guardarNegocio(negocio);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Negocio creado"), HttpStatus.CREATED);
	}
	
	@PostMapping("actualizar")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> modificarNegocio (@RequestBody Negocio negocio) {
		if(!negocioService.existeNegocio(negocio.getId())) {
			return new ResponseEntity("El negocio no existe", HttpStatus.BAD_REQUEST);
		}
		Negocio aux = negocioService.getNegocioConEmail(negocio.getEmail());
		if(aux != null && negocio.getId() != aux.getId()) {
			return new ResponseEntity("El negocio con email " +negocio.getEmail()+ " ya existe", HttpStatus.BAD_REQUEST);
		}
		negocioService.modificarNegocio(negocio);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Negocio modificado"), HttpStatus.OK);
	}
}
