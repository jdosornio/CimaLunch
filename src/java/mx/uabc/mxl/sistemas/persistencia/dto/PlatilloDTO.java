/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "platillo")
public class PlatilloDTO implements Serializable {
    
    private int id;
    private String nombre;
    private String descripcion;
    private byte[] imagen;
    private Categoria categoria;
    private double precio;
    private int tiempoPreparacion;
    private NegocioDTO negocio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false, length = 100)
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "descripcion", nullable = false, length = 500)
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column( name = "imagen", nullable = false )
    @Lob
    @Basic(fetch = LAZY)
    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Enumerated(value = STRING)
    @Column(name = "categoria", nullable = false)
    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Column(name = "precio", nullable = false)
    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Column(name = "tiempo_preparacion", nullable = false, length = 11)
    /**
     * @return the tiempoPreparacion
     */
    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    /**
     * @param tiempoPreparacion the tiempoPreparacion to set
     */
    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "id_negocio",
            referencedColumnName = "id",
            nullable = false
    )
    /**
     * @return the negocio
     */
    public NegocioDTO getNegocio() {
        return negocio;
    }

    /**
     * @param negocio the negocio to set
     */
    public void setNegocio(NegocioDTO negocio) {
        this.negocio = negocio;
    }
    
    
    public static enum Categoria {
        COMIDA, BEBIDA, DULCE, OTRO
    }
}