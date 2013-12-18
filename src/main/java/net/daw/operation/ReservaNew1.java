package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CaptcharBean;
import net.daw.bean.ReservaBean;
import net.daw.dao.CaptcharDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ReservaParam;

public class ReservaNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        ReservaParam oReservaParam = new ReservaParam(request);
        ReservaBean oReservaBean = new ReservaBean();
        
        try {
            oReservaBean = oReservaParam.load(oReservaBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/reserva/form.jsp");
        return oReservaBean;
    }
}
