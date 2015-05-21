/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "comentario_platillo")
public class ComentarioPlatilloDTO implements Serializable {
    
    private int id;
    private UsuarioDTO alumno;
    private PlatilloDTO platillo;
    private int calificacion;
    private String comentario;
    private Date fecha;

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

   @Column(name = "calificacion", nullable = false, length = 11) 
    /**
     * @return the calificacion
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Column(name = "comentario", nullable = false, length = 500)
    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Temporal(value = TIMESTAMP)
    @Column(name = "fecha", nullable = false)
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
    
}