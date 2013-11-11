/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CompraBean;
import net.daw.dao.CompraDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CompraParam;

/**
 *
 * @author rafa
 */
public class CompraRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        CompraBean oCompraBean = new CompraBean();
        CompraParam oCompraParam = new CompraParam(request);
        oCompraBean = oCompraParam.loadId(oCompraBean);
        try {
            CompraDao oCompraDAO = new CompraDao(oContexto.getEnumTipoConexion());
            oCompraDAO.remove(oCompraBean);
        } catch (Exception e) {
            throw new ServletException("CompraController: Remove Error: " + e.getMessage());
        }
        String strMensaje = "Se ha eliminado la información de la compra con id=" + Integer.toString(oCompraBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=compra&method=list\">Ir al listado de compras</a><br />";
        String Mensaje = strMensaje;
        return Mensaje;
    }

}
