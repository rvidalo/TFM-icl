package es.uoc.icl.domain;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Usuario {
    private Long id;
    private String nombre;
    private String apellidos;
    private String documento;
    private LocalDate fechaNacimiento;
    private String email;
    private String contrasena;
    private boolean admin;
    private LocalDateTime fechaRegistro;
}
