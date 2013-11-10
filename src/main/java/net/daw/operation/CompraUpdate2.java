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
public class CompraUpdate2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        if ("producto".equals(oContexto.getSearchingFor())) {
            oContexto.setVista("jsp/producto/list.jsp");
            oContexto.setClase("producto");
            oContexto.setMetodo("selectone");
            oContexto.setFase("1");            
            oContexto.setClaseRetorno("producto");
            oContexto.setMetodoRetorno("update");
            oContexto.setFaseRetorno("1");
            oContexto.removeParam("id_tipoproducto");
            oContexto.removeParam("page");
            ProductoSelectone1 oOperacion = new ProductoSelectone1();
            return oOperacion.execute(request, response);
        } else {
            oContexto.setVista("jsp/mensaje.jsp");
            CompraBean oCompraBean = new CompraBean();
            CompraDao oCompraDao = new CompraDao(oContexto.getEnumTipoConexion());
            CompraParam oCompraParam = new CompraParam(request);
            oCompraBean = oCompraParam.loadId(oCompraBean);
            oCompraBean = oCompraDao.get(oCompraBean);
            try {
                oCompraBean = oCompraParam.load(oCompraBean);
            } catch (NumberFormatException e) {
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            try {
                oCompraDao.set(oCompraBean);
            } catch (Exception e) {
                throw new ServletException("CompraController: Update Error: Phase 2: " + e.getMessage());
            }
            return "Se ha modificado la información de la compra con id=" + Integer.toString(oCompraBean.getId());
        }
    }

}
