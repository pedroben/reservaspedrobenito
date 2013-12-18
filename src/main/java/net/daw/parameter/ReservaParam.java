/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ReservaBean;

/**
 *
 * @author rafa
 */
public class ReservaParam {

    private final HttpServletRequest request;

    public ReservaParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public ReservaBean loadId(ReservaBean oReserva) {
        try {
            if (request.getParameter("id") != null) {
                oReserva.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oReserva.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oReserva;
    }

    public ReservaBean load(ReservaBean oReserva) throws NumberFormatException, ParseException {
        try {
            String anyo = "";
            String mes = "";
            String dia = "";
            anyo = request.getParameter("anyo");
            mes = request.getParameter("mes");
            dia = request.getParameter("dia");
            if ((anyo != null && mes != null && dia != null)) {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = anyo + "-" + mes + "-" + dia;
                oReserva.setFecha(formatoFecha.parse(fecha));
            }
            if(request.getParameter("id_habitacion") != null){
                oReserva.getHabitacion().setId(Integer.parseInt(request.getParameter("id_habitacion")));
            }
            if(request.getParameter("id_usuario") != null){
                oReserva.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException e) {
            throw new ParseException("Controller: Error: load: Formato de datos en fecha incorrecto " + e.getMessage(),1);
        }
        return oReserva;
    }
}
