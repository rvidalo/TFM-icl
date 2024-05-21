package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private BigDecimal totalCanjeado;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(Rol.NEGOCIO.name()));
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
