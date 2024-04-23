package es.uoc.icl.domain;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Usuario {
	private Integer id;
    private String nombre;
    private String apellidos;
    private String documento;
    private String email;
    private String contrasena;
    private boolean admin;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;

    public Usuario(Integer id, String nombre, String apellidos, String documento, String email, String contrasena,
    		boolean admin, LocalDate fechaNacimiento, LocalDateTime fechaRegistro) {
    	this.id = id;
    	this.nombre = nombre;
    	this.apellidos = apellidos;
    	this.documento = documento;
    	this.email = email;
    	this.contrasena = contrasena;
    	this.admin = admin;
    	this.fechaNacimiento = fechaNacimiento;
    	this.fechaRegistro = fechaRegistro;
    }
    public Usuario () { }
}
