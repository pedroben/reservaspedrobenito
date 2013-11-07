package net.daw.operation;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CompraBean;
import net.daw.dao.CompraDao;
import net.daw.helper.Contexto;

public class CompraList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/compra/list.jsp");
        try {

//            getForeignTable()
//            getForeignField()
//            getForeignFieldValue() 
                    
            CompraDao oCompraDao = new CompraDao(oContexto.getEnumTipoConexion());

            if (oContexto.getForeignField() != null) {
                //HashMap<String, String> hmfilter= new HashMap<>();      
                oContexto.getHmFilter().put(oContexto.getForeignField(), oContexto.getForeignFieldValue());
            }

            Integer intPages = oCompraDao.getPages(oContexto.getNrpp(), oContexto.getHmFilter(), oContexto.getHmOrder());

            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<CompraBean> listado = (ArrayList<CompraBean>) oCompraDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getHmFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
            ArrayList<String> vecindad = (ArrayList<String>) oCompraDao.getNeighborhood(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            return a;
        } catch (Exception e) {
            throw new ServletException("CompraList1: Error: " + e.getMessage());
        }
    }
}
