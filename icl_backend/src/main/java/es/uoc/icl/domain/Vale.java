package es.uoc.icl.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Vale {
    private Integer id;
    private Usuario usuario;
    private Date fechaLimite;
    private BigDecimal valorTotal;
    private Integer lote;
    private Date fechaRegistro;
}
