package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class Negocio implements UserDetails{

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String cif;
    private String email;
    private String nombre;
    private TipoNegocio tipo;
    private String direccion;
    private BigDecimal valorTotal;
    private String contrasena;
    private Integer estado;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
		//List.of(new SimpleGrantedAuthority(rol.name()));
	}
	@Override
	public String getPassword() {
		return contrasena;
	}
	@Override
	public String getUsername() {
		return email;
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
}
