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
import net.daw.dao.ClienteDao;
import net.daw.dao.CompraDao;
import net.daw.dao.ProductoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CompraParam;

/**
 *
 * @author rafa
 */
public class CompraView1 implements Operation {

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
        ClienteDao oClienteDao = new ClienteDao(oContexto.getEnumTipoConexion());
        ProductoDao oProductoDao = new ProductoDao(oContexto.getEnumTipoConexion());
        try {
            oCompraBean = oCompraDao.get(oCompraBean);
            oCompraBean.setProducto(oProductoDao.get(oCompraBean.getProducto()));
            oCompraBean.setCliente(oClienteDao.get(oCompraBean.getCliente()));
        } catch (Exception e) {
            throw new ServletException("ClienteController: View Error: Phase 1: " + e.getMessage());
        }
        
        return oCompraBean;
    }
}
