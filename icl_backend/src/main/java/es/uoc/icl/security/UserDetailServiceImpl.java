package es.uoc.icl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.Usuario;
import es.uoc.icl.service.NegocioService;
import es.uoc.icl.service.UsuarioService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	NegocioService negocioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getUsuarioConEmail(email);
		if(usuario != null) {
			return new UserDetailsImpl(usuario, null);
		}
		
		Negocio negocio = negocioService.getNegocioConEmail(email);
		if(negocio != null) {
			return new UserDetailsImpl(null, negocio);
		}
		
		throw new UsernameNotFoundException("El usuario con email " +email+ " no existe.");
	}

}
