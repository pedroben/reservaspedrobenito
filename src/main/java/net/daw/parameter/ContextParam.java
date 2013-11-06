/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.UsuarioBean;
import net.daw.helper.Contexto;

/**
 *
 * @author rafa
 */
public class ContextParam {

    private final HttpServletRequest request;

    public ContextParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public Contexto loadSession(Contexto oContexto) {
        oContexto.setHaySesion(request.getSession().getAttribute("usuarioBean") != null);
        if (oContexto.getHaySesion()) {
            oContexto.setUserBeanSession((UsuarioBean) request.getSession().getAttribute("usuarioBean"));
        } else {
            oContexto.setUserBeanSession(null);
        }
        return oContexto;
    }

    public Contexto load(Contexto oContexto) throws NumberFormatException {
        try {
            HashMap<String, String> parameterNames = new HashMap<>();
            Enumeration enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String parameterName = (String) enumeration.nextElement();
                parameterNames.put(parameterName, request.getParameter(parameterName));
            }
            oContexto.setParameters(parameterNames);

            if (!oContexto.getHaySesion()) {
                oContexto.setClase("usuario");
                if (!"login".equals(request.getParameter("method"))) {
                    oContexto.setMetodo("ocioso");
                }

            }

//            if (request.getParameter("phase") == null) {
//                oContexto.setFase("1");
//            }
//            if ("list".equals(oContexto.getMetodo()) || "selectone".equals(oContexto.getMetodo())) {
//
//                if (request.getParameter("page") == null) {
//                    oContexto.setPage(1);
//                }
//
//                if (request.getParameter("nrpp") == null) {
//                    oContexto.setNrpp(10);
//                }
//
                if (request.getParameter("filter") == null) {
                    oContexto.setHmFilter(null);
                } else {
                    if (request.getParameter("filtervalue") == null) {
                        oContexto.setHmFilter(null);
                    } else {
                        HashMap<String, String> hmFilter = new HashMap<>();
                        hmFilter.put(request.getParameter("filter"), request.getParameter("filtervalue"));
                        oContexto.setHmFilter(hmFilter);
                    }
                }

                if (request.getParameter("order") == null) {
                    oContexto.setHmOrder(null);
                } else {
                    if (request.getParameter("ordervalue") == null) {
                        oContexto.setHmOrder(null);
                    } else {
                        HashMap<String, String> hmOrder = new HashMap<>();
                        hmOrder.put(request.getParameter("order"), request.getParameter("ordervalue"));
                        oContexto.setHmOrder(hmOrder);
                    }
                }
            
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oContexto;
    }
}
