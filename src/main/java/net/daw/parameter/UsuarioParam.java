/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.UsuarioBean;

/**
 *
 * @author rafa
 */
public class UsuarioParam {

    private HttpServletRequest request;

    public UsuarioParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public UsuarioBean loadId(UsuarioBean oUsuario) {
        return null;
    }

    public UsuarioBean load(UsuarioBean oUsuario) {
        if ((request.getParameter("login") != null)) {
            oUsuario.setLogin(request.getParameter("login"));
        }
        if ((request.getParameter("password") != null)) {
            oUsuario.setPassword(request.getParameter("password"));
        }
        return oUsuario;
    }
}
