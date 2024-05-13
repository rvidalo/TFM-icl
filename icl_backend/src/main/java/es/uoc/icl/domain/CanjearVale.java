package es.uoc.icl.domain;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CanjearVale {
	int id;
	String qr;
	String emailNegocio;
	BigDecimal total;
	BigDecimal descuento;
}
