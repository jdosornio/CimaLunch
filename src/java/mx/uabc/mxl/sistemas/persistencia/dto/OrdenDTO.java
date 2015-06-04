/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "orden")
public class OrdenDTO implements Serializable {
    
    private int id;
    private UsuarioDTO alumno;
    private double precioTotal;
    private int tiempoEstimado;
    private Date fecha;
    private boolean realizada = false;
    private List<PlatilloOrdenadoDTO> platillosOrdenados = new ArrayList<>();
    
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

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    /**
     * @return the alumno
     */
    public UsuarioDTO getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(UsuarioDTO alumno) {
        this.alumno = alumno;
    }

    @Column(name = "precio_total", nullable = false)
    /**
     * @return the precioTotal
     */
    public double getPrecioTotal() {
        return precioTotal;
    }

    /**
     * @param precioTotal the precioTotal to set
     */
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Column(name = "tiempo_estimado", nullable = false, length = 11)
    /**
     * @return the tiempoEstimado
     */
    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    /**
     * @param tiempoEstimado the tiempoEstimado to set
     */
    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    @Temporal(value = TIMESTAMP)
    @Column(name = "fecha")
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "realizada", nullable = false)
    /**
     * @return the realizada
     */
    public boolean isRealizada() {
        return realizada;
    }

    /**
     * @param realizada the realizada to set
     */
    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
    
    @OneToMany(
            mappedBy = "orden",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    public List<PlatilloOrdenadoDTO> getPlatillosOrdenados() {
        return platillosOrdenados;
    }
    
    public void setPlatillosOrdenados(List<PlatilloOrdenadoDTO> platillosOrdenados) {
        this.platillosOrdenados = platillosOrdenados;
    }
    
    public void addPlatilloOrdenado(PlatilloOrdenadoDTO platillo) {
        platillo.setOrden(this);
        platillosOrdenados.add(platillo);
        System.out.println("Agregados!");
    }
}