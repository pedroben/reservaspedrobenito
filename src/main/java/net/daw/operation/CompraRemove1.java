/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CompraBean;
import net.daw.helper.Contexto;
import net.daw.parameter.CompraParam;

/**
 *
 * @author rafa
 */
public class CompraRemove1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        CompraBean oCompraBean = new CompraBean();
        CompraParam oCompraParam = new CompraParam(request);
        oCompraBean = oCompraParam.loadId(oCompraBean);
        return "Borrar la compra " + oCompraBean.getId();

    }
}
