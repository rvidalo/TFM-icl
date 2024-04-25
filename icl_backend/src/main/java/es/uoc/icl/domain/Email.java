package es.uoc.icl.domain;

import lombok.Data;

@Data
public class Email {
	
	private String remitente;
	private String destino;
	private String titulo;
	private String contenido;
}
