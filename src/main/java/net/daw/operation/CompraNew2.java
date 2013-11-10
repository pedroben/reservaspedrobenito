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
public class CompraNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        switch (oContexto.getSearchingFor()) {
            case "producto":
            {
                oContexto.setVista("jsp/producto/list.jsp");
                oContexto.setClase("producto");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("producto");
                oContexto.setClaseRetorno("compra");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_producto");
                ProductoList1 oOperacion = new ProductoList1();
                return oOperacion.execute(request, response);
            }
            case "cliente":
            {
                oContexto.setVista("jsp/cliente/list.jsp");
                oContexto.setClase("cliente");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("cliente");
                oContexto.setClaseRetorno("compra");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_cliente");
                ClienteList1 oOperacion = new ClienteList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                CompraBean oCompraBean = new CompraBean();
                CompraDao oCompraDao = new CompraDao(oContexto.getEnumTipoConexion());
                CompraParam oCompraParam = new CompraParam(request);
                oCompraBean = oCompraParam.loadId(oCompraBean);
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
                return "Se ha añadido la información de compra con id=" + Integer.toString(oCompraBean.getId());
        }
    }
}
