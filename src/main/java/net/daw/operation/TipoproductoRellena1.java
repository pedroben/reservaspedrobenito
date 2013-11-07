package net.daw.operation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.TipoproductoBean;
import net.daw.dao.TipoproductoDao;
import net.daw.helper.Contexto;

public class TipoproductoRellena1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        TipoproductoBean oTipoproductoBean = new TipoproductoBean();
        TipoproductoDao oTipoproductoDao = new TipoproductoDao(oContexto.getEnumTipoConexion());

        ArrayList<String> uno = new ArrayList<>();
        ArrayList<String> dos = new ArrayList<>();

        uno.add("Herramienta");
        uno.add("Accesorio");
        uno.add("Producto");
        uno.add("Artículo");
        uno.add("Referencia");
        uno.add("Mercancía");
        uno.add("Género");

        dos.add("manual");
        dos.add("automático");
        dos.add("descatalogado");
        dos.add("inexistente");

        String primero;
        String segundo;

        Integer contador = 0;
        Iterator<String> iterador1 = uno.listIterator();
        while (iterador1.hasNext()) {
            primero = iterador1.next();
            contador++;
            Iterator<String> iterador2 = dos.listIterator();
            while (iterador2.hasNext()) {
                segundo = iterador2.next();
                contador++;               
                oTipoproductoBean.setId(0);
                oTipoproductoBean.setDescripcion(primero + " " + segundo);
                try {
                    oTipoproductoDao.set(oTipoproductoBean);

                } catch (Exception e) {
                    throw new ServletException("ProductoController: Update Error: Phase 2: " + e.getMessage());
                }

            }
        }
        return "OK- información generada.";
    }
}
