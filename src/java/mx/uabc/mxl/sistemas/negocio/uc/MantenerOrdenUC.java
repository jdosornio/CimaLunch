/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.uc;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import mx.uabc.mxl.sistemas.negocio.facade.FACADEServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.NegocioDTO;
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

    private static final String PLACE_ORDER_ERROR = "placeOrderError";
    private static final String PLACE_ORDER_OK = "placeOrderOk";

    private static final String NEW_ORDER = "newOrder";
    private final static String DELIMITER = ":DELIM>";
    
    public String confirmarOrden(int idAlumno, JSONArray charola, double precioTotal,
            int tiempoEstimado) {

        String response = PLACE_ORDER_ERROR;

        OrdenDTO orden = new OrdenDTO();

        for (int i = 0; i < charola.length(); i++) {
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

        boolean ok = false;
        if (!orden.getPlatillosOrdenados().isEmpty()) {
            //Si no esta vacía todo ok
            UsuarioDTO alumno = new UsuarioDTO();
            alumno.setId(idAlumno);

            orden.setPrecioTotal(precioTotal);
            orden.setRealizada(true);
            orden.setTiempoEstimado(tiempoEstimado);
            orden.setFecha(new Date());
            orden.setAlumno(alumno);

            System.out.println("Id alumno: " + orden.getAlumno().getId()
                    + "Id platillo: " + orden.getPlatillosOrdenados().get(0)
                    .getPlatillo().getId());

            ok = FACADEServiceLocator.getBaseInstance().saveEntity(orden);
        }

        if (ok) {
            response = PLACE_ORDER_OK + "/" + orden.getId();
        }

        return response;
    }

    public String notificarNegocio(int idOrden) {
        String response = null;

        try {
            //Notify when new order was placed

            OrdenDTO orden = new OrdenDTO();
            orden.setId(idOrden);

            NegocioDTO negocio = new NegocioDTO();
            negocio.setNombre("Deli");
            //Search orden for each negocio in the order....
            List<PlatilloOrdenadoDTO> platillos = FACADEServiceLocator
                    .getOrdenInstance().getPlatillosOrdenByNegocio(orden, negocio);

            if (platillos != null && !platillos.isEmpty()) {
                //Send the data...
                JSONObject jsonOrden = new JSONObject();

                jsonOrden.put("idOrden", platillos.get(0).getOrden().getId());
                jsonOrden.put("fecha", platillos.get(0).getOrden().getFecha());
                jsonOrden.put("nombreAlumno", platillos.get(0).getOrden()
                        .getAlumno().getNombre());

                JSONArray jsonPlatillos = new JSONArray();

                double precioTotal = 0;
                
                for (PlatilloOrdenadoDTO platillo : platillos) {
                    JSONObject jsonPlatillo = new JSONObject();
                    
                    jsonPlatillo.put("nombrePlatillo", platillo.getPlatillo().getNombre());
                    jsonPlatillo.put("cantidad", platillo.getCantidad());
                    
                    jsonPlatillos.put(jsonPlatillo);
                    precioTotal += platillo.getPlatillo().getPrecio() 
                            * platillo.getCantidad();
                }
                
                jsonOrden.put("platillos", jsonPlatillos);
                jsonOrden.put("precioTotal", precioTotal);
                
                response = NEW_ORDER + DELIMITER + jsonOrden.toString();
            }
        } catch (JSONException ex) {
            Logger.getLogger(MantenerOrdenUC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
}
