package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ProductoBean;
import net.daw.dao.TipoproductoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ProductoParam;

public class ProductoNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        ProductoParam oProductoParam = new ProductoParam(request);
        ProductoBean oProductoBean = new ProductoBean();
        TipoproductoDao oTipoproductoDao = new TipoproductoDao(oContexto.getEnumTipoConexion());
        try {
            oProductoBean = oProductoParam.load(oProductoBean);
            oProductoBean.setTipoProducto(oTipoproductoDao.get(oProductoBean.getTipoProducto()));

        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/producto/form.jsp");
        return oProductoBean;
    }

}
