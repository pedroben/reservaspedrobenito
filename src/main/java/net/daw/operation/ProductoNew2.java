package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ProductoBean;
import net.daw.dao.ProductoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ProductoParam;

public class ProductoNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        if ("tipoproducto".equals(oContexto.getSearchingFor())) {
            oContexto.setVista("jsp/producto/list.jsp");
            oContexto.setClase("tipoproducto");
            oContexto.setMetodo("list");
            oContexto.setFase("1");
            oContexto.setClaseRetorno("producto");
            oContexto.setMetodoRetorno("new");
            oContexto.setFaseRetorno("1");
            oContexto.removeParam("id_tipoproducto");
            TipoproductoList1 oOperacion = new TipoproductoList1();
            return oOperacion.execute(request, response);
        } else {
            oContexto.setVista("jsp/mensaje.jsp");
            ProductoBean oProductoBean = new ProductoBean();
            ProductoDao oProductoDao = new ProductoDao(oContexto.getEnumTipoConexion());
            ProductoParam oProductoParam = new ProductoParam(request);
            oProductoBean = oProductoParam.loadId(oProductoBean);
            try {
                oProductoBean = oProductoParam.load(oProductoBean);
            } catch (NumberFormatException e) {
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            try {
                oProductoDao.set(oProductoBean);
            } catch (Exception e) {
                throw new ServletException("ProductoController: Update Error: Phase 2: " + e.getMessage());
            }
            String strMensaje = "Se ha añadido la información del producto con id=" + Integer.toString(oProductoBean.getId()) + "<br />";            
            strMensaje += "<a href=\"Controller?class=producto&method=view&id=" + oProductoBean.getId() + "\">Ver producto creado</a><br />";
            return strMensaje;
        }
    }
}
