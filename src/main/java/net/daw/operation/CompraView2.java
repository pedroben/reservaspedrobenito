
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;

/**
 *
 * @author rafa
 */
public class CompraView2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/compra/list.jsp");
        oContexto.setClase("compra");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        CompraList1 oOperacion = new CompraList1();
        return oOperacion.execute(request, response);
    }
}
