package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ProductoBean;
import net.daw.helper.Contexto;
import net.daw.parameter.ProductoParam;

public class ProductoRemove1 extends Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");        
        ProductoBean oProductoBean = new ProductoBean();        
        ProductoParam oProductoParam = new ProductoParam(request);
        oProductoBean = oProductoParam.loadId(oProductoBean);
        return "Borrar el producto " + oProductoBean.getId();
    }
}
