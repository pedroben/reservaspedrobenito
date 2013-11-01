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
import net.daw.bean.TipoproductoBean;
import net.daw.dao.TipoproductoDao;
import net.daw.helper.Contexto;

/**
 *
 * @author rafa
 */
public class TipoproductoSelectone1  extends Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/tipoproducto/list.jsp");
        try {
            TipoproductoDao oTipoproductoDao = new TipoproductoDao(oContexto.getEnumTipoConexion());
            Integer intPages = oTipoproductoDao.getPages(oContexto.getNrpp(),null,null);
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            ArrayList<TipoproductoBean> listado = (ArrayList<TipoproductoBean>) oTipoproductoDao.getPage(oContexto.getNrpp(), oContexto.getPage(),null,null);
            ArrayList<String> vecindad = (ArrayList<String>) oTipoproductoDao.getNeighborhood("<a href=\"Controller?class=tipoproducto&method=list&rpp=" + oContexto.getNrpp() + "&page=", oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            return a;
        } catch (Exception e) {
            throw new ServletException("TipoproductoList1: View Error: " + e.getMessage());
        }
    }
    
}
