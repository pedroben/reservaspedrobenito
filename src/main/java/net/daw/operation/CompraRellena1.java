package net.daw.operation;

import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CompraBean;
import net.daw.dao.ClienteDao;

import net.daw.dao.CompraDao;
import net.daw.dao.ProductoDao;
import net.daw.helper.Contexto;

public class CompraRellena1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        CompraBean oCompraBean = new CompraBean();
        CompraDao oCompraDao = new CompraDao(oContexto.getEnumTipoConexion());

        ClienteDao oClienteDao = new ClienteDao(oContexto.getEnumTipoConexion());
        ProductoDao oProductoDao = new ProductoDao(oContexto.getEnumTipoConexion());

      

        int num1;
        int num2;
     
        for (int i = 1; i <= 100; i++) {

           
            oCompraBean.setId(0);

            do {
                num1 = 1 + (int) (Math.random() * ((200 - 1) + 1));
                oCompraBean.getCliente().setId(num1);
                oClienteDao.get(oCompraBean.getCliente());
            } while (oCompraBean.getCliente().getId() == 0);

            do {
                num2 = 1 + (int) (Math.random() * ((100 - 1) + 1));
                oCompraBean.getProducto().setId(num2);
                oProductoDao.get(oCompraBean.getProducto());
            } while (oCompraBean.getProducto().getId() == 0);

            oCompraBean.setCantidad(num1 + num2 );
            Date date = new Date(97, 1, 23);

            oCompraBean.setFecha(date);

            try {
                oCompraDao.set(oCompraBean);

            } catch (Exception e) {
                throw new ServletException("CompraRellena: Update Error: Phase 1: " + e.getMessage());
            }

        }

        return "OK- información generada.";
    }
}
