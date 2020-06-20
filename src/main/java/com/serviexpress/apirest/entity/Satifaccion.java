

package com.serviexpress.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name = "Satifaccion", uniqueConstraints = { @UniqueConstraint(columnNames = { "idsatifaccion" }) })
@Entity
public class Satifaccion {

    /**
     * al momento de selecionar un servicio capturar id categoria enviarla al buscar productos con el id de categoria cargrar combocx de prodcuto esto para reserva
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idsatifaccion;
    private double nota1;
    private double nota2;
    private double nota3;

    public Long getIdsatifaccion() {
        return idsatifaccion;
    }

    public void setIdsatifaccion(Long idsatifaccion) {
        this.idsatifaccion = idsatifaccion;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public Satifaccion() {
    }

    public Satifaccion(double nota1, double nota2, double nota3) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Satifaccion( Satifaccion satifaccion) {

        this.nota1 = satifaccion.nota1;
        this.nota2 = satifaccion.nota2;
        this.nota3 = satifaccion.nota3;
    }




}