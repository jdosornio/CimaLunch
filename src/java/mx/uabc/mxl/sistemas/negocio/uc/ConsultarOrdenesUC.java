/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.uc;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import mx.uabc.mxl.sistemas.negocio.facade.FACADEServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.OrdenDTO;
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
public class ConsultarOrdenesUC {
    
    private final static String ORDENES_ALUMNO = "ordenesAlumno";
    private final static String DELIMITER = ":DELIM>";
    
    public String getOrdenesAlumno(int idAlumno) {
        String response = null;

        UsuarioDTO alumno = new UsuarioDTO();
        alumno.setId(idAlumno);
        
        //Get Ordenes
        List<OrdenDTO> ordenes = FACADEServiceLocator.getOrdenInstance()
                .getOrdenesAlumno(alumno);

        //If everything was ok
        if (ordenes != null && !ordenes.isEmpty()) {
            JSONArray jsonOrdenes = new JSONArray();

            //Build json with the results
            for (OrdenDTO orden : ordenes) {
                try {
                    //Create a json object for each 
                    JSONObject jsonOrden = new JSONObject();

                    jsonOrden.put("id", orden.getId());
                    jsonOrden.put("precioTotal", orden.getPrecioTotal());
                    jsonOrden.put("tiempoEstimado", orden.getTiempoEstimado());
                    jsonOrden.put("fecha", orden.getFecha());

                    //Get platillos ordenados
                    JSONArray jsonPlatillos = new JSONArray();
                    for(PlatilloOrdenadoDTO platillo : orden.getPlatillosOrdenados()) {
                        JSONObject jsonPlatillo = new JSONObject();
                        
                        jsonPlatillo.put("nombre", platillo.getPlatillo().getNombre());
                        jsonPlatillo.put("negocio", platillo.getPlatillo().getNegocio().getNombre());
                        jsonPlatillo.put("precio", platillo.getPlatillo().getPrecio());
                        jsonPlatillo.put("tiempoPreparacion", platillo.getPlatillo()
                                .getTiempoPreparacion());
                        jsonPlatillo.put("cantidad", platillo.getCantidad());
                        jsonPlatillo.put("status", platillo.getStatus());
                        
                        jsonPlatillos.put(jsonPlatillo);
                    }
                    
                    jsonOrden.put("platillosOrdenados", jsonPlatillos);
                    
                    jsonOrdenes.put(jsonOrden);
                } catch (JSONException ex) {
                    Logger.getLogger(ConsultarPlatillosUC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //if no errors
            if (jsonOrdenes.length() > 0) {
                System.out.println(jsonOrdenes);
                response = ORDENES_ALUMNO + DELIMITER + jsonOrdenes.toString();
            }
        }

        return response;
    }
}