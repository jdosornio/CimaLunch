/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "platillo_ordenado")
public class PlatilloOrdenadoDTO implements Serializable {
    
    private int id;
    private OrdenDTO orden;
    private PlatilloDTO platillo;
    private int cantidad;
    private Status status;
    private boolean visto;

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

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "id_orden",
            referencedColumnName = "id",
            insertable = false,
            updatable = false,
            nullable = false
    )
    /**
     * @return the orden
     */
    public OrdenDTO getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(OrdenDTO orden) {
        this.orden = orden;
    }

    @ManyToOne
    @JoinColumn(name = "id_platillo")
    /**
     * @return the platillo
     */
    public PlatilloDTO getPlatillo() {
        return platillo;
    }

    /**
     * @param platillo the platillo to set
     */
    public void setPlatillo(PlatilloDTO platillo) {
        this.platillo = platillo;
    }

    @Column(name = "cantidad", nullable = false, length = 11)
    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Enumerated(value = STRING)
    @Column(name = "status", nullable = false)
    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    
    @Column(name = "visto", nullable = false)
    /**
     * @return the visto
     */
    public boolean isVisto() {
        return visto;
    }

    /**
     * @param visto the visto to set
     */
    public void setVisto(boolean visto) {
        this.visto = visto;
    }
    
    public static enum Status {
        PREPARACION, LISTO
    }
}