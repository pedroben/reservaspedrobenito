package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.ClienteBean;
import net.daw.dao.ClienteDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ClienteParam;

public class ClienteRemove2 extends Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        ClienteBean oClienteBean = new ClienteBean(); 
        ClienteParam oClienteParam = new ClienteParam(request);
        oClienteBean = oClienteParam.loadId(oClienteBean);
        try {
            ClienteDao oClienteDAO = new ClienteDao(oContexto.getEnumTipoConexion());
            oClienteDAO.remove(oClienteBean);
        } catch (Exception e) {
            throw new ServletException("ClienteController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del cliente con id=" + Integer.toString(oClienteBean.getId()));
        return Mensaje;
    }

}
