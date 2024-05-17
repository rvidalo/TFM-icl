package es.uoc.icl.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Canjeado {
	private Integer id;
    private Vale vale;
    private Negocio negocio;
    private BigDecimal total;
    private BigDecimal descuento;
    private LocalDateTime fechaRegistro;

    public Canjeado(CanjearVale canjearVale, Vale vale, Negocio negocio) {
    	this.vale = vale;
    	this.negocio = negocio;
    	this.total = canjearVale.getTotal();
    	this.descuento = canjearVale.getDescuento();
    }
    
    public Canjeado(Integer id, BigDecimal total, BigDecimal descuento, LocalDateTime fechaRegistro,
    		String cif, String email, String nombre, String direccion, BigDecimal valorTotal,
    		String descripcion, BigDecimal valorTotalVale, String qr, LocalDate fechaLimite,
    		String nombreUsuario, String apellidos, String documento, String emailUsuario
    		) {
    	this.id = id;
    	this.total = total;
    	this.descuento = descuento;
    	this.fechaRegistro = fechaRegistro;
    	this.negocio = new Negocio();
    	this.negocio.setCif(cif);
    	this.negocio.setEmail(email);
    	this.negocio.setNombre(nombre);
    	this.negocio.setDireccion(direccion);
    	this.negocio.setValorTotal(valorTotal);
    	TipoNegocio tipo = new TipoNegocio();
    	tipo.setDescripcion(descripcion);
    	this.negocio.setTipo(tipo);
    	this.vale = new Vale();
    	this.vale.setValorTotal(valorTotalVale);
    	this.vale.setQr(qr);
    	this.vale.setFechaLimite(fechaLimite);
    	Usuario usuario = new Usuario();
    	usuario.setNombre(nombreUsuario);
    	usuario.setApellidos(apellidos);
    	usuario.setDocumento(documento);
    	usuario.setEmail(emailUsuario);
    	this.vale.setUsuario(usuario);
    }
}
