package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.ClienteBean;
import net.daw.dao.ClienteDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ClienteParam;

public class ClienteUpdate1 implements Operation {

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
            throw new ServletException("ClienteController: Update Error: Phase 1: " + e.getMessage());
        }
        try {
            oClienteBean = oClienteParam.load(oClienteBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        return oClienteBean;
    }
}
