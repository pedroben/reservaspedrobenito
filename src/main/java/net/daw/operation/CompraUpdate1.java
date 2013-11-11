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
public class CompraUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/compra/form.jsp");
        CompraBean oCompraBean;
        CompraDao oCompraDao;
        oCompraBean = new CompraBean();
        CompraParam oCompraParam = new CompraParam(request);
        oCompraBean = oCompraParam.loadId(oCompraBean);
        oCompraDao = new CompraDao(oContexto.getEnumTipoConexion());
        try {
            oCompraBean = oCompraDao.get(oCompraBean);
        } catch (Exception e) {
            throw new ServletException("CompraController: Update Error: Phase 1: " + e.getMessage());
        }
        try {
            oCompraBean = oCompraParam.load(oCompraBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        return oCompraBean;
    }
}
