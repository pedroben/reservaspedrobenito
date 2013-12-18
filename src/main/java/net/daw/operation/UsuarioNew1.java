package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CaptcharBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.CaptcharDao;
import net.daw.helper.Contexto;
import net.daw.parameter.UsuarioParam;

public class UsuarioNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        UsuarioBean oUsuarioBean = new UsuarioBean();

        int desde = 1;
        int hasta = 4;
        int aleatorio = (int) (Math.random() * (hasta - desde + 1) + desde);
        CaptcharBean oCaptcharBean = new CaptcharBean(aleatorio);

        CaptcharDao oCaptcharDao = new CaptcharDao(oContexto.getEnumTipoConexion());
        oCaptcharBean = oCaptcharDao.get(oCaptcharBean);

        ArrayList<Object> objetos = new ArrayList<>();
        
        try {
            oUsuarioBean = oUsuarioParam.load(oUsuarioBean);
            
            objetos.add(oUsuarioBean);
            objetos.add(oCaptcharBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/usuario/registro.jsp");
        //return oUsuarioBean;
        return objetos;
    }
}
