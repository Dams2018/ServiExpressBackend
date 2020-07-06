package com.serviexpress.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Table(name = "Reserva", uniqueConstraints = { @UniqueConstraint(columnNames = { "idreserva" }) })
@Entity
public class Reserva implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idreserva;
    private Long idcliente;
    private Long idvehiculo;

    // guardar lista de id serv y prodcuto luego en el front mostrar los servicio
    // por ende cuando se mande la lista al front debo sacar los id recorrerlos y
    // devolver lista de servicio
    @NotBlank
    private String servicios;
    private String productos;
	@Temporal(TemporalType.DATE)
	private Date fecha;
    private String horareserva;
    private int estado;
    private Boolean activo;

    public Reserva() {
    }

    
    public Reserva(Reserva reserva) {

        this.idreserva = reserva.idreserva;
        this.idcliente = reserva.idcliente;
        this.servicios = reserva.servicios;
        this.fecha = reserva.fecha;
        this.horareserva = reserva.horareserva;
        this.estado = reserva.estado;
        this.productos = reserva.productos;
        this.idvehiculo = reserva.idvehiculo;
        this.activo = reserva.activo;
    }
    public Reserva(Long idreserva, Long idcliente, Long idvehiculo, @NotBlank String servicios, String productos,
            Date fecha, String horareserva, int estado, Boolean activo) {
        this.idreserva = idreserva;
        this.idcliente = idcliente;
        this.idvehiculo = idvehiculo;
        this.servicios = servicios;
        this.productos = productos;
        this.fecha = fecha;
        this.horareserva = horareserva;
        this.estado = estado;
        this.activo = activo;
    }

    public Long getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Long idreserva) {
        this.idreserva = idreserva;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public Long getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Long idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHorareserva() {
        return horareserva;
    }

    public void setHorareserva(String horareserva) {
        this.horareserva = horareserva;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Reserva [activo=" + activo + ", estado=" + estado + ", fecha=" + fecha + ", horareserva=" + horareserva
                + ", idcliente=" + idcliente + ", idreserva=" + idreserva + ", idvehiculo=" + idvehiculo
                + ", productos=" + productos + ", servicios=" + servicios + "]";
    }


}