package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ProductoBean;
import net.daw.dao.ProductoDao;
import net.daw.helper.Contexto;

public class ProductoList1 extends Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/producto/list.jsp");
        try {
            ProductoDao oProductoDAO = new ProductoDao(oContexto.getEnumTipoConexion());
            Integer intPages = oProductoDAO.getPages(oContexto.getNrpp());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            ArrayList<ProductoBean> listado = (ArrayList<ProductoBean>) oProductoDAO.getPage(oContexto.getNrpp(), oContexto.getPage());
            ArrayList<String> vecindad = (ArrayList<String>) oProductoDAO.getNeighborhood("<a href=\"Controller?class=producto&method=list&rpp=" + oContexto.getNrpp() + "&page=", oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            return a;
        } catch (Exception e) {
            throw new ServletException("ProductoList1: View Error: " + e.getMessage());
        }
    }
}
