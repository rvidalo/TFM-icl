package es.uoc.icl.domain;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String nombre;
    private String apellidos;
    private String documento;
    private String email;
    private String contrasena;
    private Rol rol;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return Collections.emptyList();
		return List.of(new SimpleGrantedAuthority(Rol.USUARIO.name()));
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
