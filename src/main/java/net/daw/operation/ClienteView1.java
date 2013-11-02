package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ClienteBean;
import net.daw.dao.ClienteDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ClienteParam;

/**
 *
 * @author rafael aznar
 */
public class ClienteView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/cliente/form.jsp");        
        ClienteBean oClienteBean;
        ClienteDao oClienteDao;
        oClienteBean = new ClienteBean();
        ClienteParam oClienteParam = new ClienteParam(request);
        oClienteBean = oClienteParam.loadId(oClienteBean);
        oClienteDao = new ClienteDao(oContexto.getEnumTipoConexion());
        try {
            oClienteBean = oClienteDao.get(oClienteBean);
        } catch (Exception e) {
            throw new ServletException("ClienteController: View Error: Phase 1: " + e.getMessage());
        }
        oClienteBean = oClienteParam.load(oClienteBean);
        return oClienteBean;
    }
}
