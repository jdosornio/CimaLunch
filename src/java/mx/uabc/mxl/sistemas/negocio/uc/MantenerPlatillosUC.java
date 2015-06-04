/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.uc;

import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import mx.uabc.mxl.sistemas.negocio.facade.FACADEServiceLocator;
import mx.uabc.mxl.sistemas.persistencia.dto.NegocioDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO.Categoria;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jesus Donaldo
 */
@ApplicationScoped
public class MantenerPlatillosUC {
    
    private static final String ADD_PLATILLO_OK = "addPlatilloOk";
    private static final String ADD_PLATILLO_ERROR = "addPlatilloError";
    
    private static final String UPDATE_PLATILLO_OK = "updatePlatilloOk";
    private static final String UPDATE_PLATILLO_ERROR = "updatePlatilloError";
    
    private static final String DELETE_PLATILLO_OK = "deletePlatilloOk";
    private static final String DELETE_PLATILLO_ERROR = "deletePlatilloError";
    
    private final static String DELIMITER = ":DELIM>";
    
    public String agregarPlatillo(JSONObject jsonPlatillo) {
        String response = ADD_PLATILLO_ERROR;
        
        try {
            
            PlatilloDTO platillo = new PlatilloDTO();
            
            NegocioDTO negocio = new NegocioDTO();
            negocio.setId(jsonPlatillo.getInt("idNegocio"));
            
            byte[] imagen = Base64.getDecoder().decode(jsonPlatillo.getString("imagen"));
            
            //byte[] imagen = (byte[]) jsonPlatillo.get("imagen");
            
            platillo.setImagen(imagen);
            platillo.setNegocio(negocio);
            platillo.setNombre(jsonPlatillo.getString("nombre"));
            platillo.setPrecio(jsonPlatillo.getDouble("precio"));
            platillo.setTiempoPreparacion(jsonPlatillo.getInt("tiempoPreparacion"));
            platillo.setCategoria(Categoria.valueOf(jsonPlatillo.getString("categoria")));
            platillo.setDescripcion(jsonPlatillo.getString("descripcion"));

            boolean ok = FACADEServiceLocator.getBaseInstance().saveEntity(platillo);
            
            if(ok) {
                response = ADD_PLATILLO_OK;
            }
        } catch (JSONException ex) {
            Logger.getLogger(MantenerPlatillosUC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    public String actualizarPlatillo(JSONObject jsonPlatillo) {
        String response = UPDATE_PLATILLO_ERROR;
        
        try {
            
            PlatilloDTO platillo = new PlatilloDTO();
            platillo.setId(jsonPlatillo.getInt("idPlatillo"));
            
            //NegocioDTO negocio = new NegocioDTO();
            //negocio.setId(jsonPlatillo.getInt("idNegocio"));
            
            byte[] imagen = Base64.getDecoder().decode(jsonPlatillo.getString("imagen"));
            
            //byte[] imagen = (byte[]) jsonPlatillo.get("imagen");
            
            platillo.setImagen(imagen);
            //platillo.setNegocio(negocio);
            platillo.setNombre(jsonPlatillo.getString("nombre"));
            platillo.setPrecio(jsonPlatillo.getDouble("precio"));
            platillo.setTiempoPreparacion(jsonPlatillo.getInt("tiempoPreparacion"));
            platillo.setCategoria(Categoria.valueOf(jsonPlatillo.getString("categoria")));
            platillo.setDescripcion(jsonPlatillo.getString("descripcion"));

            boolean ok = FACADEServiceLocator.getBaseInstance().updateEntity(platillo);
            
            if(ok) {
                response = UPDATE_PLATILLO_OK;
            }
        } catch (JSONException ex) {
            Logger.getLogger(MantenerPlatillosUC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    public String eliminarPlatillo(int idPlatillo) {
        String response = DELETE_PLATILLO_ERROR;

        PlatilloDTO platillo = new PlatilloDTO();
        platillo.setId(idPlatillo);
        
        //Delete platillo
        boolean ok = FACADEServiceLocator.getBaseInstance().deleteEntity(platillo);

        if(ok) {
            response = DELETE_PLATILLO_OK;
        }
        
        return response;
    }
    
    public String getNegocioAdmin(int idAdminLocal) {
        
        String response = null;

        UsuarioDTO adminLocal = new UsuarioDTO();
        adminLocal.setId(idAdminLocal);
        
        //Get Negocios
        List<NegocioDTO> negocios = FACADEServiceLocator.getNegocioInstance()
                .getNegociosByAdmin(adminLocal);

        //If everything was ok
        if (negocios != null && !negocios.isEmpty()) {
            response = "idNegocio" + DELIMITER +  negocios.get(0).getId();
        }

        return response;
    }
}