package es.uoc.icl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilNegocio {
	private Integer id;
    private String nombre;
    private String direccion;
    private String cif;
    private String email;
    private TipoNegocio tipo;
    
    public PerfilNegocio(Negocio negocio) {
    	this.id = negocio.getId();
    	this.nombre = negocio.getNombre();
    	this.direccion = negocio.getDireccion();
    	this.cif = negocio.getCif();
    	this.email = negocio.getEmail();
    	this.tipo = negocio.getTipo();
    }
}
