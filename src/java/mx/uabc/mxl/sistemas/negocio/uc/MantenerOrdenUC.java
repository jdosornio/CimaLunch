/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.uc;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import mx.uabc.mxl.sistemas.negocio.facade.FACADEServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.OrdenDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloOrdenadoDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jesus Donaldo
 */
@ApplicationScoped
public class MantenerOrdenUC {
    
    public boolean confirmarOrden(int idAlumno, JSONArray charola, double precioTotal,
            int tiempoEstimado) {
        
        boolean ok = false;
        OrdenDTO orden = new OrdenDTO();
        
        for(int i = 0; i < charola.length(); i++) {
            try {
                JSONObject jsonPlatillo = charola.getJSONObject(i);
                PlatilloOrdenadoDTO platilloOrdenado = new PlatilloOrdenadoDTO();
                PlatilloDTO platillo = new PlatilloDTO();
                
                platillo.setId(jsonPlatillo.getInt("id"));
                
                platilloOrdenado.setPlatillo(platillo);
                platilloOrdenado.setCantidad(jsonPlatillo.getInt("cantidad"));
                platilloOrdenado.setStatus(PlatilloOrdenadoDTO.Status.PREPARACION);
                platilloOrdenado.setVisto(true);
                
                orden.addPlatilloOrdenado(platilloOrdenado);
            } catch (JSONException ex) {
                Logger.getLogger(MantenerOrdenUC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(!orden.getPlatillosOrdenados().isEmpty()) {
            //Si no esta vacía todo ok
            UsuarioDTO alumno = new UsuarioDTO();
            alumno.setId(idAlumno);
            
            orden.setPrecioTotal(precioTotal);
            orden.setRealizada(true);
            orden.setTiempoEstimado(tiempoEstimado);
            orden.setFecha(new Date());
            orden.setAlumno(alumno);
            
            System.out.println("Id alumno: " + orden.getAlumno().getId() + 
                    "Id platillo: " + orden.getPlatillosOrdenados().get(0)
                            .getPlatillo().getId());
            
            ok = FACADEServiceLocator.getBaseInstance().saveEntity(orden);
        }
        
        return ok;
    }
    
    public static void main(String[] args) {
        
        OrdenDTO orden = new OrdenDTO();
        
        UsuarioDTO alumno = new UsuarioDTO();
        alumno.setId(1);
        
        orden.setAlumno(alumno);
        orden.setFecha(new Date());
        orden.setPrecioTotal(5440.2);
        orden.setRealizada(true);
        orden.setTiempoEstimado(15);
        
        PlatilloOrdenadoDTO po = new PlatilloOrdenadoDTO();
        po.setCantidad(5);
        po.setStatus(PlatilloOrdenadoDTO.Status.LISTO);
        po.setVisto(true);
        
        PlatilloDTO platillo = new PlatilloDTO();
        platillo.setId(1);
        
        po.setPlatillo(platillo);
        
        orden.addPlatilloOrdenado(po);
        
        System.out.println(FACADEServiceLocator.getBaseInstance().saveEntity(orden));
    }
}