/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uabc.mxl.sistemas.negocio.uc;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
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

    public String getAllNegocios() {
        String response = null;

        //Get all negocios
        List<NegocioDTO> negocios = FACADEServiceLocator.getBaseInstance()
                .getAllEntities(NegocioDTO.class);

        //If everything was ok
        if (negocios != null && !negocios.isEmpty()) {
            //Stringify
            XStream xstream = new XStream(new JettisonMappedXmlDriver());

            response = xstream.toXML(negocios);
        }

        System.out.println(response);

        return response;
    }
}
