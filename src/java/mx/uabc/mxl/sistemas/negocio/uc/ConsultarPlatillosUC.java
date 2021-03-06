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
import mx.uabc.mxl.sistemas.persistencia.dto.ComentarioPlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.NegocioDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO;
import mx.uabc.mxl.sistemas.persistencia.dto.PlatilloDTO.Categoria;
import mx.uabc.mxl.sistemas.persistencia.dto.UsuarioDTO;
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
    private final static String PLATILLOS_BY_CATEGORIA = "platillosByCategoria";
    private final static String INFO_PLATILLO = "infoPlatillo";

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
            if (jsonNegocios.length() > 0) {
                System.out.println(jsonNegocios);
                response = ALL_NEGOCIOS + DELIMITER + jsonNegocios.toString();
            }

        }

        return response;
    }

    public String getPlatillosByCategoria(int idNegocio, String categoria) {
        String response = null;

        //Get platillos
        NegocioDTO negocio = new NegocioDTO();
        negocio.setId(idNegocio);

        List<PlatilloDTO> platillos = FACADEServiceLocator.getNegocioInstance()
                .getPlatillosByCategoria(negocio, Categoria.valueOf(categoria));

        //If everything was ok
        if (platillos != null && !platillos.isEmpty()) {
            JSONArray jsonPlatillos = new JSONArray();

            //Build json with the results
            for (PlatilloDTO platillo : platillos) {
                try {
                    //Create a json object for each 
                    JSONObject jsonPlatillo = new JSONObject();

                    jsonPlatillo.put("id", platillo.getId());
                    jsonPlatillo.put("nombre", platillo.getNombre());
                    jsonPlatillo.put("descripcion", platillo.getDescripcion());
                    jsonPlatillo.put("precio", platillo.getPrecio());
                    jsonPlatillo.put("tiempoPreparacion", platillo.getTiempoPreparacion());
                    jsonPlatillo.put("categoria", platillo.getCategoria().toString());

                    jsonPlatillos.put(jsonPlatillo);
                } catch (JSONException ex) {
                    Logger.getLogger(ConsultarPlatillosUC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //if no errors
            if (jsonPlatillos.length() > 0) {
                System.out.println(jsonPlatillos);
                response = PLATILLOS_BY_CATEGORIA + DELIMITER + jsonPlatillos.toString();
            }
        }

        return response;
    }

    public String getPlatilloInfo(int idPlatillo, int idAlumno) {
        String response;

        PlatilloDTO platillo = new PlatilloDTO();
        platillo.setId(idPlatillo);

        UsuarioDTO alumno = new UsuarioDTO();
        alumno.setId(idAlumno);

        //Get imagen
        byte[] imagen = FACADEServiceLocator.getPlatilloFACADE().getImagen(platillo);

        if (imagen == null) {
            //If no image then there is no platillo in the database so return
            //null right now
            return null;
        }
        //Get Base64 string
        String imagenString = Base64.getEncoder().encodeToString(imagen);

        //Get average calification
        double promedioCalificacion = FACADEServiceLocator.getComentarioPlatilloInstance()
                .getCalificacionPromedio(platillo);

        boolean ordenado = FACADEServiceLocator.getOrdenInstance()
                .hasOrdered(platillo, alumno);

        List<ComentarioPlatilloDTO> comentarios = FACADEServiceLocator
                .getComentarioPlatilloInstance().getComentarios(platillo);

        //Create JSON Object for storing data
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("imagen", imagenString);
            jsonObj.put("opinion", promedioCalificacion);
            jsonObj.put("ordenado", ordenado);

            //Store comentarios too
            JSONArray jsonComentarios = new JSONArray();
            for (ComentarioPlatilloDTO comentario : comentarios) {
                JSONObject jsonComentario = new JSONObject();

                jsonComentario.put("idAlumno", comentario.getAlumno().getId());
                jsonComentario.put("alumno", comentario.getAlumno().getNombre());
                jsonComentario.put("calificacion", comentario.getCalificacion());
                jsonComentario.put("comentario", comentario.getComentario());
                jsonComentario.put("fecha", comentario.getFecha());
                jsonComentarios.put(jsonComentario);
            }

            //Save comentarios too
            jsonObj.put("comentarios", jsonComentarios);

            System.out.println(jsonObj);
            response = INFO_PLATILLO + DELIMITER + jsonObj.toString();

        } catch (JSONException ex) {
            Logger.getLogger(ConsultarPlatillosUC.class.getName()).log(Level.SEVERE, null, ex);
            response = null;
        }

        return response;
    }

    public boolean setComentario(String comentario, int calificacion, int idPlatillo, int idAlumno) {

        PlatilloDTO platillo = new PlatilloDTO();
        platillo.setId(idPlatillo);

        UsuarioDTO alumno = new UsuarioDTO();
        alumno.setId(idAlumno);

        ComentarioPlatilloDTO comentarioPlatillo = new ComentarioPlatilloDTO();
        
        comentarioPlatillo.setComentario(comentario);
        comentarioPlatillo.setCalificacion(calificacion);
        comentarioPlatillo.setAlumno(alumno);
        comentarioPlatillo.setPlatillo(platillo);

        return FACADEServiceLocator.getBaseInstance().saveEntity(comentarioPlatillo);
    }

}
