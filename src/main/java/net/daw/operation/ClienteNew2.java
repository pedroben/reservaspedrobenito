package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.ClienteBean;
import net.daw.dao.ClienteDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ClienteParam;

public class ClienteNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        ClienteBean oClienteBean = new ClienteBean();
        ClienteDao oClienteDao = new ClienteDao(oContexto.getEnumTipoConexion());
        ClienteParam oClienteParam = new ClienteParam(request);
        oClienteBean = oClienteParam.loadId(oClienteBean);
        try {
            oClienteBean = oClienteParam.load(oClienteBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oClienteDao.set(oClienteBean);
        } catch (Exception e) {
            throw new ServletException("ClienteController: New Error: Phase 2: " + e.getMessage());
        }
        String strMensaje = "Se ha añadido la información del cliente con id=" + Integer.toString(oClienteBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=cliente&method=view&id=" + oClienteBean.getId() + "\">Ver cliente creado en el formulario</a><br />";
        return strMensaje;
    }
}
