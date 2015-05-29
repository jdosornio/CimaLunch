/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.persistencia.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jesus Donaldo
 */
@Entity
@Table(name = "negocio")
public class NegocioDTO implements Serializable {
    
    private int id;
    private String nombre;
    private byte[] imagen;
    private String slogan;
    private List<PlatilloDTO> platillos = new ArrayList<>();
    private UsuarioDTO administrador;

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

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
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

    @Column( name = "imagen", nullable = false )
    @Lob
    @Basic(fetch = EAGER)
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

    @Column(name = "slogan", nullable = false, length = 100)
    /**
     * @return the slogan
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * @param slogan the slogan to set
     */
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @OneToMany(
            mappedBy = "negocio",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    public List<PlatilloDTO> getPlatillos() {
        return platillos;
    }
    
    public void setPlatillos(List<PlatilloDTO> platillos) {
        this.platillos = platillos;
    }
    
    @OneToOne
    @JoinColumn(name = "id_administrador")
    /**
     * @return the administrador
     */
    public UsuarioDTO getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(UsuarioDTO administrador) {
        this.administrador = administrador;
    }
    
    //Para lograr la correcta relacion.
    public void addPlatillo(PlatilloDTO platillo) {
        platillo.setNegocio(this);
        platillos.add(platillo);
    }
}