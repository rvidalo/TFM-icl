package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Vale {
    private Integer id;
    private BigDecimal valorTotal;
    private Integer lote;
    private LocalDate fechaLimite;
    private LocalDateTime fechaRegistro;
	private Usuario usuario;

	public Vale (Usuario usuario) {
		this.usuario=usuario;
		this.valorTotal = new BigDecimal(50);
		this.lote = 1;
		this.fechaLimite = LocalDate.now().plusMonths(1);
		this.fechaRegistro = LocalDateTime.now();
	}
	
	public Vale(Integer id, BigDecimal valorTotal, Integer lote, LocalDate fechaLimite, LocalDateTime fechaRegistro,
			Integer usuario_id, String usuario_nombre, String usuario_apellidos, String usuario_documento, String usuario_email, String usuario_contrasena) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.lote = lote;
		this.fechaLimite = fechaLimite;
		this.fechaRegistro = fechaRegistro;
		this.usuario = new Usuario();
	}
	
	public Vale(Integer id, BigDecimal valorTotal, Integer lote, LocalDate fechaLimite, LocalDateTime fechaRegistro) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.lote = lote;
		this.fechaLimite = fechaLimite;
		this.fechaRegistro = fechaRegistro;
	}
}