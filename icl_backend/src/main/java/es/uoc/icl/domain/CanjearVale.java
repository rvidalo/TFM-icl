package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CanjearVale {
	int id;
	String qr;
	String emailNegocio;
	BigDecimal total;
	BigDecimal descuento;
	LocalDateTime fechaRegistro;
	
	public CanjearVale (Canjeado canjeado){
		this.id = canjeado.getId();
		this.qr = canjeado.getVale().getQr();
		this.emailNegocio = canjeado.getNegocio().getEmail();
		this.total = canjeado.getTotal();
		this.descuento = canjeado.getDescuento();
		this.fechaRegistro = canjeado.getFechaRegistro();
	}

	public CanjearVale(Canjeado canjeado, boolean nombreUsuario, boolean nombreNegocio) {
		if(canjeado != null) {
			this.id = canjeado.getId();
			if(nombreUsuario) {
				this.qr = canjeado.getVale().getUsuario().getNombreCompleto();
			}else {
				this.qr = canjeado.getVale().getQr();
			}
			if(nombreNegocio) {
				this.emailNegocio = canjeado.getNegocio().getNombre() + " [" + canjeado.getNegocio().getTipo().getDescripcion() + "]";
			}else {
				this.emailNegocio = canjeado.getNegocio().getEmail();
			}
			this.total = canjeado.getTotal();
			this.descuento = canjeado.getDescuento();
			this.fechaRegistro = canjeado.getFechaRegistro();
		}
	}
}
