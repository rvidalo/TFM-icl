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

import es.uoc.icl.domain.Vale;
import es.uoc.icl.service.ValeService;

@RestController
@RequestMapping("/api/vales")
@CrossOrigin
public class ValeRestController {

	@Autowired
	ValeService valeService;
	
	@GetMapping("")
	public ResponseEntity<List<Vale>> getVales() {
		List<Vale> vales = valeService.getVales();
		return new ResponseEntity<List<Vale>>(vales, HttpStatus.OK);
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<Vale> getValeDeUsuario(@RequestParam String email) {
		Vale vale = valeService.getValeDeUsuario(email);
		return new ResponseEntity<Vale>(vale, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/id")
	public ResponseEntity<Vale> getVale(@RequestParam (required = true) Integer idVale) {
		Vale vale = valeService.getVale(idVale);
		if(vale == null) {
			return new ResponseEntity("El vale no existe", HttpStatus.BAD_REQUEST);
		} 
		return new ResponseEntity<Vale>(vale, HttpStatus.OK);
	}

	@PostMapping("/nuevo")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> nuevoVale (@RequestParam String email) {
		if(valeService.existeValeDeUsuario(email)) {
			return new ResponseEntity("El usuario no puede solicitar más vales", HttpStatus.BAD_REQUEST);
		}
		valeService.guardarVale(email);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Vale creado"), HttpStatus.CREATED);
	}
	
	@PostMapping("/modificar")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity<?> modificarVale (@RequestBody Vale vale) {
		if(!valeService.existeVale(vale.getId())) {
			return new ResponseEntity("El vale no existe", HttpStatus.BAD_REQUEST);
		}
		valeService.modificarVale(vale);
		return new ResponseEntity(Collections.singletonMap("mensaje", "Vale modificado"), HttpStatus.OK);
	}
}
