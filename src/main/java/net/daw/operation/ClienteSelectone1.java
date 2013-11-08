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
import net.daw.bean.ClienteBean;
import net.daw.dao.ClienteDao;
import net.daw.helper.Contexto;

/**
 *
 * @author rafa
 */
public class ClienteSelectone1 implements Operation {
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/cliente/list.jsp");
        try {
            ClienteDao oClienteDao = new ClienteDao(oContexto.getEnumTipoConexion());
            Integer intPages = oClienteDao.getPages(oContexto.getNrpp(), oContexto.getHmFilter(), oContexto.getHmOrder());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<ClienteBean> listado = (ArrayList<ClienteBean>) oClienteDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getHmFilter(), oContexto.getHmOrder());
            ArrayList<String> vecindad = (ArrayList<String>) oClienteDao.getNeighborhood("<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=", oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            return a;
        } catch (Exception e) {
            throw new ServletException("ClienteList1: View Error: " + e.getMessage());
        }
    }
}
