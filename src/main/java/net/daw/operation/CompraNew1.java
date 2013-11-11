/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CompraBean;
import net.daw.dao.ClienteDao;
import net.daw.dao.ProductoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CompraParam;

/**
 *
 * @author rafa
 */
public class CompraNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        CompraParam oCompraParam = new CompraParam(request);
        CompraBean oCompraBean = new CompraBean();
        ProductoDao oProductoDao = new ProductoDao(oContexto.getEnumTipoConexion());
        ClienteDao oClienteDao = new ClienteDao(oContexto.getEnumTipoConexion());
        try {
            oCompraBean = oCompraParam.load(oCompraBean);
            oCompraBean.setProducto(oProductoDao.get(oCompraBean.getProducto()));
            oCompraBean = oCompraParam.load(oCompraBean);
            oCompraBean.setCliente(oClienteDao.get(oCompraBean.getCliente()));
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/compra/form.jsp");
        return oCompraBean;
    }

}
