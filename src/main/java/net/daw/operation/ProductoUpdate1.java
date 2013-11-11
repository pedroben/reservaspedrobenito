package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.ProductoBean;
import net.daw.dao.ProductoDao;
import net.daw.dao.TipoproductoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ProductoParam;

public class ProductoUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/producto/form.jsp");
        ProductoBean oProductoBean;
        ProductoDao oProductoDao;
        oProductoBean = new ProductoBean();
        ProductoParam oProductoParam = new ProductoParam(request);
        oProductoBean = oProductoParam.loadId(oProductoBean);
        oProductoDao = new ProductoDao(oContexto.getEnumTipoConexion());
        try {
            oProductoBean = oProductoDao.get(oProductoBean);
        } catch (Exception e) {
            throw new ServletException("ProductoController: Update Error: Phase 1: " + e.getMessage());
        }

            try {
                oProductoBean = oProductoParam.load(oProductoBean);
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }

        TipoproductoDao oTipoproductoDao = new TipoproductoDao(oContexto.getEnumTipoConexion());
        oProductoBean.setTipoProducto(oTipoproductoDao.get(oProductoBean.getTipoProducto()));
        return oProductoBean;
    }
}
