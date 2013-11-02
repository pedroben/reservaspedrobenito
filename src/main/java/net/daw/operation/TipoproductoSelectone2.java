package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.TipoproductoBean;
import net.daw.dao.TipoproductoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.TipoproductoParam;

public class TipoproductoSelectone2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        TipoproductoBean oTipoproductoBean = new TipoproductoBean();
        TipoproductoDao oTipoproductoDao = new TipoproductoDao(oContexto.getEnumTipoConexion());
        TipoproductoParam oTipoproductoParam = new TipoproductoParam(request);
        oTipoproductoBean = oTipoproductoParam.loadId(oTipoproductoBean);
        oTipoproductoBean = oTipoproductoParam.load(oTipoproductoBean);
        try {
            
            
            oTipoproductoDao.set(oTipoproductoBean);
            
            
            
            
        } catch (Exception e) {
            throw new ServletException("TipoproductoController: Update Error: Phase 2: " + e.getMessage());
        }        
        //pte volver al punto de partida...
        return "Se ha modificado la información del " + oContexto.getSelectOneTable() + " con id=" + Integer.toString(oTipoproductoBean.getId());
    }
}
