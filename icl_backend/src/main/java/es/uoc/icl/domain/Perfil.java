package es.uoc.icl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {
	private Integer id;
    private String nombre;
    private String apellidos;
    private String documento;
    private String email;
    
    public Perfil(Usuario usuario) {
    	this.id = usuario.getId();
    	this.nombre = usuario.getNombre();
    	this.apellidos = usuario.getApellidos();
    	this.documento = usuario.getDocumento();
    	this.email = usuario.getEmail();
    }
}
