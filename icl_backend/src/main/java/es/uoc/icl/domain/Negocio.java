package es.uoc.icl.domain;

import lombok.Data;

@Data
public class Negocio {
    private Integer id;
    private String cif;
    private String email;
    private String nombre;
    private TipoNegocio tipo;
    private String direccion;
    private Double valorTotal;
    private String contrasena;
    private Integer estado;
    private String fechaRegistro;
}
