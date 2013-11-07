/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ProductoBean;
import net.daw.dao.ProductoDao;
import net.daw.helper.Contexto;

/**
 *
 * @author rafa
 */
public class ProductoSelectone1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/producto/list.jsp");
        try {
            ProductoDao oProductoDao = new ProductoDao(oContexto.getEnumTipoConexion());
            Integer intPages = oProductoDao.getPages(oContexto.getNrpp(), oContexto.getHmFilter(), oContexto.getHmOrder());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<ProductoBean> listado = (ArrayList<ProductoBean>) oProductoDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getHmFilter(), oContexto.getHmOrder());
            ArrayList<String> vecindad = (ArrayList<String>) oProductoDao.getNeighborhood("<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=", oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            return a;
        } catch (Exception e) {
            throw new ServletException("TipoproductoList1: View Error: " + e.getMessage());
        }
    }
}
