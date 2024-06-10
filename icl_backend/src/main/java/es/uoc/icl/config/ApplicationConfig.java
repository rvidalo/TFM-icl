package es.uoc.icl.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.uoc.icl.domain.Negocio;
import es.uoc.icl.domain.Usuario;
import es.uoc.icl.service.NegocioRepository;
import es.uoc.icl.service.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NegocioRepository negocioRepository;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Lazy
	public UserDetailsService userDetailService() {
		return username -> {
	        // Intenta buscar el usuario en usuarioRepository
	        Optional<Usuario> usuarioOptional = usuarioRepository.getUsuarioConEmail(username);

	        if (usuarioOptional.isPresent()) {
	            return usuarioOptional.orElseThrow(); 
	        } else {
	            // Usuario no encontrado en usuarioRepository, intenta en negocioRepository
	            Optional<Negocio> negocioOptional = negocioRepository.getNegocioConEmail(username);

	            if (negocioOptional.isPresent()) {
	                return negocioOptional.orElseThrow();
	            } else {
	                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
	            }
	        }
		};
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String contrasena = encoder.encode("contrasena");
		System.out.println("pass: " + contrasena);
	}
	
}
