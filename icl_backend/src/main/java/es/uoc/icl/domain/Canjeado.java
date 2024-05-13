package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Canjeado {
	private Integer id;
    private Vale vale;
    private Negocio negocio;
    private Usuario usuario;
    private BigDecimal total;
    private BigDecimal descuento;
    private LocalDate fechaRegistro;

    public Canjeado(CanjearVale canjearVale, Vale vale, Negocio negocio) {
    	this.vale = vale;
    	this.negocio = negocio;
    	this.usuario = vale.getUsuario();
    	this.total = canjearVale.getTotal();
    	this.descuento = canjearVale.getDescuento();
    }
}
