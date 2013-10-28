/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ProductoBean;

/**
 *
 * @author rafa
 */
public class ProductoParam {

    private HttpServletRequest request;

    public ProductoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public ProductoBean loadId(ProductoBean oProducto) {
        if (request.getParameter("id") != null) {
            oProducto.setId(Integer.parseInt(request.getParameter("id")));
        } else {
            oProducto.setId(0);
        }
        return oProducto;
    }

    public ProductoBean load(ProductoBean oProducto) {
        if ((request.getParameter("codigo") != null)) {
            oProducto.setCodigo(request.getParameter("codigo"));
        }
        if ((request.getParameter("descripcion") != null)) {
            oProducto.setDescripcion(request.getParameter("descripcion"));
        }
        if ((request.getParameter("precio") != null)) {
            oProducto.setPrecio(Float.parseFloat(request.getParameter("precio")));
        }
        if ((request.getParameter("id_tipoproducto") != null)) {
            oProducto.setId_tipoproducto(Integer.parseInt(request.getParameter("id_tipoproducto")));
        }        
        return oProducto;
    }
}
