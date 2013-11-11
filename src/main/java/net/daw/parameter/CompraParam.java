/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.CompraBean;

/**
 *
 * @author rafa
 */
public class CompraParam {

    private final HttpServletRequest request;

    public CompraParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public CompraBean loadId(CompraBean oCompra) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oCompra.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oCompra.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oCompra;

    }

    public CompraBean load(CompraBean oCompra) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id_cliente") != null)) {
                oCompra.getCliente().setId(Integer.parseInt(request.getParameter("id_cliente")));
            }
            if ((request.getParameter("id_producto") != null)) {
                oCompra.getProducto().setId(Integer.parseInt(request.getParameter("id_producto")));
            }
            if ((request.getParameter("cantidad") != null)) {
                oCompra.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            }
            if ((request.getParameter("fecha") != null)) {
                oCompra.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("CompraParam: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oCompra;
    }
}
