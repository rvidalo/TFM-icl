package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Vale {
    private Integer id;
    private BigDecimal valorTotal;
    private String qr;
    private LocalDate fechaLimite;
    private LocalDateTime fechaRegistro;
	private Usuario usuario;

	public Vale (Usuario usuario) {
		this.usuario=usuario;
		this.valorTotal = new BigDecimal(50);
		this.fechaLimite = LocalDate.now().plusMonths(1);
		this.fechaRegistro = LocalDateTime.now();
		this.qr = generarCodigoQr(usuario.getDocumento());
	}
	
	private static String generarCodigoQr(String documento) {
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(documento.getBytes());
            String encoded = Base64.getEncoder().encodeToString(hash);

            // Tomamos los primeros 20 caracteres como el código aleatorio
            return encoded.substring(0, 20);
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
	}

	public Vale(Integer id, BigDecimal valorTotal, LocalDate fechaLimite, LocalDateTime fechaRegistro, String qr,
			Integer usuario_id, String usuario_nombre, String usuario_apellidos, String usuario_documento, String usuario_email, String usuario_contrasena) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.fechaLimite = fechaLimite;
		this.fechaRegistro = fechaRegistro;
		this.qr = qr;
		this.usuario = new Usuario();
	}
	
	public Vale(Integer id, BigDecimal valorTotal, LocalDate fechaLimite, LocalDateTime fechaRegistro, String qr) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.fechaLimite = fechaLimite;
		this.fechaRegistro = fechaRegistro;
	}
	
}