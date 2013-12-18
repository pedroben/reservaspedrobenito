package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CaptcharBean;

import net.daw.bean.UsuarioBean;
import net.daw.dao.CaptcharDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.UsuarioParam;

public class UsuarioNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        UsuarioBean oUsuarioBean = new UsuarioBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        oUsuarioBean = oUsuarioParam.loadId(oUsuarioBean);
        
        int id_captchar = Integer.parseInt(request.getParameter("id_captchar"));
        String respuesta = request.getParameter("respuesta");
        
        CaptcharBean oCaptcharBean = new CaptcharBean(id_captchar);
        CaptcharDao oCaptcharDao = new CaptcharDao(oContexto.getEnumTipoConexion());
        oCaptcharBean = oCaptcharDao.get(oCaptcharBean);
        
        String strMensaje = "";
        
        
        try {
            oUsuarioBean = oUsuarioParam.load(oUsuarioBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        
        if(oCaptcharBean.getRespuesta().equalsIgnoreCase(respuesta)) {
            try {
                oUsuarioDao.set(oUsuarioBean);
            } catch (Exception e) {
                throw new ServletException("UsuarioController: New Error: Phase 2: " + e.getMessage());
            }
            strMensaje = "Se ha añadido la información del usuario con id=" + Integer.toString(oUsuarioBean.getId()) + "<br />";
            //strMensaje += "<a href=\"Controller?class=usuario&method=view&id=" + oUsuarioBean.getId() + "\">Ver usuario creado en el formulario</a><br />";

        }else{
            strMensaje = "El captchar no es correcto<br />";
            strMensaje += "<a href=\"Controller?class=usuario&method=new&nombre="+oUsuarioBean.getNombre()+"&login="+oUsuarioBean.getLogin()+"\">Volver</a><br />";
        }
        return strMensaje;
    }
}
