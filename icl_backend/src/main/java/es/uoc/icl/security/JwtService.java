package es.uoc.icl.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final static String ACCESS_TOKEN_SECRET = "Afguvv3456HJG78jysdGbT457kj4390543jnklrg89435SDDTHSD54645HThgh";
	
	public String getToken(UserDetails usuario) {
		return getToken(new HashMap<>(), usuario);
	}

	private String getToken(Map<String, Object> claims, UserDetails usuario) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(usuario.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		byte[] key = Decoders.BASE64.decode(ACCESS_TOKEN_SECRET);
		return Keys.hmacShaKeyFor(key);
	}

	public String getEmailFromToken(String token) {
		return getClaims(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token, UserDetails usuario) {
		final String email = getEmailFromToken(token);
		return (email.equals(usuario.getUsername()) && !isTokenExpired(token));
	}
	
	private Claims getAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public <T> T getClaims(String token, Function<Claims,T> claimsResolver) {
		final Claims claims = getAllClaims(token);
				return claimsResolver.apply(claims);
	}
	
	private Date getExpiration(String token) {
		return getClaims(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}

}
