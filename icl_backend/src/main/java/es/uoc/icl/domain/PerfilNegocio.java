package es.uoc.icl.domain;

import java.math.BigDecimal;

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
    private BigDecimal valorTotal;
    private TipoNegocio tipo;
    private BigDecimal totalCanjeado;
    
    public PerfilNegocio(Negocio negocio) {
    	this.id = negocio.getId();
    	this.nombre = negocio.getNombre();
    	this.direccion = negocio.getDireccion();
    	this.cif = negocio.getCif();
    	this.email = negocio.getEmail();
    	this.valorTotal = negocio.getValorTotal();
    	this.tipo = negocio.getTipo();
    	this.totalCanjeado = negocio.getTotalCanjeado();
    }
}
