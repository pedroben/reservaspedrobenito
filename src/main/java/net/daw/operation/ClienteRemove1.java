package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ClienteBean;
import net.daw.helper.Contexto;
import net.daw.parameter.ClienteParam;

public class ClienteRemove1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        ClienteBean oClienteBean = new ClienteBean();   
        ClienteParam oClienteParam = new ClienteParam(request);
        oClienteBean = oClienteParam.loadId(oClienteBean);
        return "Borrar al cliente " + oClienteBean.getId();

    }
}
