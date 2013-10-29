/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.TipoproductoBean;

/**
 *
 * @author rafa
 */
public class TipoproductoParam {

    private HttpServletRequest request;

    public TipoproductoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public TipoproductoBean loadId(TipoproductoBean oTiporoducto) {
        if (request.getParameter("id") != null) {
            oTiporoducto.setId(Integer.parseInt(request.getParameter("id")));
        } else {
            oTiporoducto.setId(0);
        }
        return oTiporoducto;
    }

    public TipoproductoBean load(TipoproductoBean oTiporoducto) {
        if ((request.getParameter("descripcion") != null)) {
            oTiporoducto.setDescripcion(request.getParameter("descripcion"));
        }
        return oTiporoducto;
    }
}
