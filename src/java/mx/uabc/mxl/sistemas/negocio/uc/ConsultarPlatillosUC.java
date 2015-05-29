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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jesus Donaldo
 */
@ApplicationScoped
public class ConsultarPlatillosUC {

    private final static String ALL_NEGOCIOS = "allNegocios";
    private final static String DELIMITER = ":DELIM>";

    public String getAllNegocios() {
        String response = null;

        //Get all negocios
        List<NegocioDTO> negocios = FACADEServiceLocator.getBaseInstance()
                .getAllEntities(NegocioDTO.class);

        //If everything was ok
        if (negocios != null && !negocios.isEmpty()) {
            JSONArray jsonNegocios = new JSONArray();

            //Build json with the results
            for (NegocioDTO negocio : negocios) {
                try {
                    //Create a json object for each 
                    JSONObject jsonNegocio = new JSONObject();

                    jsonNegocio.put("id", negocio.getId());
                    jsonNegocio.put("nombre", negocio.getNombre());
                    jsonNegocio.put("slogan", negocio.getSlogan());
                    
                    //Get Base64 string
                    String imagen = Base64.getEncoder()
                            .encodeToString(negocio.getImagen());
            
                    jsonNegocio.put("imagen", imagen);

                    jsonNegocios.put(jsonNegocio);
                } catch (JSONException ex) {
                    Logger.getLogger(ConsultarPlatillosUC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //if no errors
            if(jsonNegocios.length() > 0) {
                System.out.println(jsonNegocios);
                response = ALL_NEGOCIOS + DELIMITER + jsonNegocios.toString();
            }
            
            //response = ALL_NEGOCIOS + DELIMITER + "{\"imagen\": \"" + imagen + "\"}";
        }

        return response;
    }
}
