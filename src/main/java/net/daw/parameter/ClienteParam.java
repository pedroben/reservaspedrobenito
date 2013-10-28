package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ClienteBean;

/**
 *
 * @author rafa
 */
public class ClienteParam {

    private HttpServletRequest request;

    public ClienteParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public ClienteBean loadId(ClienteBean oCliente) {
        if (request.getParameter("id") != null) {
            oCliente.setId(Integer.parseInt(request.getParameter("id")));
        } else {
            oCliente.setId(0);
        }
        return oCliente;
    }

    public ClienteBean load(ClienteBean oCliente) {
        if ((request.getParameter("nombre") != null)) {
            oCliente.setNombre(request.getParameter("nombre")); 
        }
        if ((request.getParameter("ape1") != null)) {
            oCliente.setApe1(request.getParameter("ape1")); 
        }
        if ((request.getParameter("ape2") != null)) {
            oCliente.setApe2(request.getParameter("ape2")); 
        }
        if ((request.getParameter("email") != null)) {
            oCliente.setEmail(request.getParameter("email")); 
        }                
        return oCliente;
    }
}
