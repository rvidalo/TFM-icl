package es.uoc.icl.domain;

import lombok.Data;

@Data
public class TipoNegocio {
	private Integer id;
	private String descripcion;
	
	public TipoNegocio(Integer id) {
        this.id = id;
    }
}
