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
import es.uoc.icl.domain.PerfilNegocio;
import es.uoc.icl.domain.TipoNegocio;
import es.uoc.icl.service.NegocioService;

@RestController
@RequestMapping("/api/negocios")
@CrossOrigin
public class NegocioRestController {

	@Autowired
	NegocioService negocioService;
	
	@GetMapping("")
	public ResponseEntity<List<Negocio>> getNegocios() {
		List<Negocio> negocios = negocioService.getNegocios();
		return new ResponseEntity<List<Negocio>>(negocios, HttpStatus.OK);
	}
	
	@GetMapping("/aceptados")
	public ResponseEntity<List<Negocio>> getNegociosAceptados() {
		List<Negocio> negocios = negocioService.getNegociosAceptados();
		return new ResponseEntity<List<Negocio>>(negocios, HttpStatus.OK);
	}
	
	@GetMapping("/tipo")
	public ResponseEntity<List<Negocio>> getNegociosConTipo(@RequestParam(required = true) Integer idTipoNegocio) {
		List<Negocio> negocios = negocioService.getNegociosConTipo(idTipoNegocio);
		return new ResponseEntity<List<Negocio>>(negocios, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/perfil")
	public ResponseEntity<PerfilNegocio> getNegocio(@RequestParam (required = true) String email) {
		Negocio negocio = negocioService.getNegocioConEmail(email).orElseThrow();
		if(negocio == null) {
			return new ResponseEntity("El negocio no existe", HttpStatus.BAD_REQUEST);
		} 
		PerfilNegocio perfil = new PerfilNegocio(negocio);
		return new ResponseEntity<PerfilNegocio>(perfil, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/aceptar")
	public ResponseEntity<Negocio> aceptarNegocio(@RequestParam (required = true) String email) {
		Negocio negocio = negocioService.getNegocioConEmail(email).orElseThrow();
		if(negocio == null) {
			return new ResponseEntity("El negocio no existe", HttpStatus.BAD_REQUEST);
		} 
		negocioService.aceptar(negocio);
		return new ResponseEntity<Negocio>(negocio, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/rechazar")
	public ResponseEntity<Negocio> rechazarNegocio(@RequestParam (required = true) String email) {
		Negocio negocio = negocioService.getNegocioConEmail(email).orElseThrow();
		if(negocio == null) {
			return new ResponseEntity("El negocio no existe", HttpStatus.BAD_REQUEST);
		} 
		negocioService.rechazar(negocio);
		return new ResponseEntity<Negocio>(negocio, HttpStatus.OK);
	}
	
	@GetMapping("/tipos")
	public ResponseEntity<List<TipoNegocio>> getTiposNegocio() {
		List<TipoNegocio> tipos = negocioService.getTipos();
		return new ResponseEntity<List<TipoNegocio>>(tipos, HttpStatus.OK);
	}

	@PostMapping("/nuevo")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> nuevoNegocio (@RequestBody Negocio negocio) {
		if(negocioService.existeNegocioConEmailOCif(negocio)) {
			return new ResponseEntity("El negocio con email " +negocio.getEmail()+ " o CIF " + negocio.getCif() +" ya existe", HttpStatus.BAD_REQUEST);
		}
		negocioService.guardarNegocio(negocio);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Negocio creado"), HttpStatus.CREATED);
	}
	
	@PostMapping("/modificar")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> modificarNegocio (@RequestBody PerfilNegocio perfil) {
		Negocio negocio = negocioService.getNegocio(perfil.getId());
		if(negocio == null) {
			return new ResponseEntity("El negocio no existe", HttpStatus.BAD_REQUEST);
		}
		if(negocioService.existeNegocioConEmailOCif(negocio)) {
			return new ResponseEntity("El negocio con email " +negocio.getEmail()+ " o CIF " + negocio.getCif() +" ya existe", HttpStatus.BAD_REQUEST);
		}
		negocioService.modificarNegocio(perfil);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Negocio modificado"), HttpStatus.OK);
	}
}
