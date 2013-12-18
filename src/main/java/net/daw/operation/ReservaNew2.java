/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ReservaBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ReservaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ReservaParam;

/**
 *
 * @author rafa
 */
public class ReservaNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");


            switch (oContexto.getSearchingFor()) {
                case "Establecer año":
                case "Establecer mes":
                case "Establecer dia": {   
                    oContexto.setClase("reserva");
                    oContexto.setMetodo("new");
                    oContexto.setFase("1");
                    oContexto.setSearchingFor("usuario");
                    oContexto.setClaseRetorno("reserva");
                    oContexto.setMetodoRetorno("new");
                    oContexto.setFaseRetorno("1");
                    ReservaNew1 oOperacion = new ReservaNew1();
                    return oOperacion.execute(request, response); 
                }
                case "usuario": {
                    oContexto.setVista("jsp/usuario/list.jsp");
                    oContexto.setClase("usuario");
                    oContexto.setMetodo("list");
                    oContexto.setFase("1");
                    oContexto.setSearchingFor("usuario");
                    oContexto.setClaseRetorno("reserva");
                    oContexto.setMetodoRetorno("new");
                    oContexto.setFaseRetorno("1");
                    oContexto.removeParam("id_usuario");
                    UsuarioList1 oOperacion = new UsuarioList1();
                    return oOperacion.execute(request, response);
                }
                case "habitacion": {
                    oContexto.setVista("jsp/habitacion/list.jsp");
                    oContexto.setClase("habitacion");
                    oContexto.setMetodo("list");
                    oContexto.setFase("1");
                    oContexto.setSearchingFor("habitacion");
                    oContexto.setClaseRetorno("reserva");
                    oContexto.setMetodoRetorno("new");
                    oContexto.setFaseRetorno("1");
                    oContexto.removeParam("id_habitacion");
                    HabitacionList1 oOperacion = new HabitacionList1();
                    return oOperacion.execute(request, response);
                }    
                default:
                    oContexto.setVista("jsp/mensaje.jsp");
                    ReservaBean oReservaBean = new ReservaBean();
                    ReservaDao oReservaDao = new ReservaDao(oContexto.getEnumTipoConexion());
                    ReservaParam oReservaParam = new ReservaParam(request);
                    oReservaBean = oReservaParam.loadId(oReservaBean);
                    try {
                        oReservaBean = oReservaParam.load(oReservaBean);
                    } catch (NumberFormatException e) {
                        return "Tipo de dato incorrecto en uno de los campos del formulario";
                    }
                    try {
                        oReservaDao.set(oReservaBean);
                    } catch (Exception e) {
                        throw new ServletException("ReservaController: Update Error: Phase 2: " + e.getMessage());
                    }
                    String strMensaje = "Se ha añadido la información de reserva con id=" + Integer.toString(oReservaBean.getId()) + "<br />";
                    strMensaje += "<a href=\"Controller?class=reserva&method=view&id=" + oReservaBean.getId() + "\">Ver reserva creada</a><br />";
                    return strMensaje;

            }
    }
}
