package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ClienteBean;
import net.daw.helper.Contexto;
import net.daw.parameter.ClienteParam;

public class ClienteNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        ClienteParam oClienteParam = new ClienteParam(request);
        ClienteBean oClienteBean = new ClienteBean();       
        try {
            oClienteBean = oClienteParam.load(oClienteBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/cliente/form.jsp");
        return oClienteBean;
    }

}
