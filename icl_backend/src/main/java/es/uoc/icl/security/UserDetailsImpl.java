package es.uoc.icl.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.Usuario;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Usuario usuario;
	private final Negocio negocio;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		if(usuario != null) {
			return usuario.getContrasena();
		}
		return negocio.getContrasena();
	}

	@Override
	public String getUsername() {
		if(usuario != null) {
			return usuario.getEmail();
		}
		return negocio.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getNombre() {
		if(usuario != null) {
			return usuario.getNombre();
		}
		return negocio.getNombre();
	}

}
