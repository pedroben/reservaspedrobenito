/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.CaptcharBean;

/**
 *
 * @author rafa
 */
public class CaptcharParam {

    private final HttpServletRequest request;

    public CaptcharParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public CaptcharBean loadId(CaptcharBean oCaptchar) {
        try {
            if (request.getParameter("id") != null) {
                oCaptchar.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oCaptchar.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oCaptchar;
    }

    public CaptcharBean load(CaptcharBean oCaptchar) throws NumberFormatException {
        try {
            if ((request.getParameter("pregunta") != null)) {
                oCaptchar.setPregunta(request.getParameter("pregunta"));
            }
            if ((request.getParameter("respuesta") != null)) {
                oCaptchar.setRespuesta(request.getParameter("respuesta"));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oCaptchar;
    }
}
