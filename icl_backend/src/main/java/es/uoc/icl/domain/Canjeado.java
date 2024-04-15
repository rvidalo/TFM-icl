package es.uoc.icl.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Canjeado {
    private Integer id;
    private Vale vale;
    private Negocio negocio;
    private Usuario usuario;
    private BigDecimal total;
    private BigDecimal descuento;
    private Date fechaRegistro;
}
