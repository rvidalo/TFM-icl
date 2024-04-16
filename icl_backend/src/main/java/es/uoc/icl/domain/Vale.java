package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Vale {
    private Integer id;
    private Usuario usuario;
    private LocalDate fechaLimite;
    private BigDecimal valorTotal;
    private Integer lote;
    private LocalDate fechaRegistro;
}
