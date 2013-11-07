/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.ClienteBean;
import net.daw.bean.CompraBean;
import net.daw.bean.ProductoBean;
import net.daw.data.Mysql;

/**
 *
 * @author rafa
 */
public class CompraDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public CompraDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, HashMap<String, String> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("compra", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<CompraBean> getPage(int intRegsPerPag, int intPage, HashMap<String, String> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<CompraBean> arrCompra = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("compra", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                CompraBean oCompraBean = new CompraBean(iterador.next());
                arrCompra.add(this.get(oCompraBean));
            }
            oMysql.desconexion();
            return arrCompra;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }

    public CompraBean get(CompraBean oCompraBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            ProductoBean oProductoBean = new ProductoBean();
            ClienteBean oClienteBean = new ClienteBean();

            oProductoBean.setId(Integer.parseInt(oMysql.getOne("compra", "id_producto", oCompraBean.getId())));
            oClienteBean.setId(Integer.parseInt(oMysql.getOne("compra", "id_cliente", oCompraBean.getId())));

            oCompraBean.setCantidad(Integer.parseInt(oMysql.getOne("compra", "cantidad", oCompraBean.getId())));
            //oCompraBean.setFecha(oMysql.getOne("compra", "fecha", oCompraBean.getId()));

            ProductoDao oProductoDao = new ProductoDao(enumTipoConexion);
            ClienteDao oClienteDao = new ClienteDao(enumTipoConexion);

            oProductoBean = oProductoDao.get(oProductoBean);
            oClienteBean = oClienteDao.get(oClienteBean);

            oCompraBean.setProducto(oProductoBean);
            oCompraBean.setCliente(oClienteBean);

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ProductoDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oCompraBean;
    }

    public void set(CompraBean oCompraBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oCompraBean.getId() == 0) {
                oCompraBean.setId(oMysql.insertOne("compra"));
            }
            oMysql.updateOne(oCompraBean.getId(), "compra", "id_cliente", oCompraBean.getCliente().getId().toString());
            oMysql.updateOne(oCompraBean.getId(), "compra", "id_producto", oCompraBean.getProducto().getId().toString());
            oMysql.updateOne(oCompraBean.getId(), "compra", "cantidad", oCompraBean.getCantidad().toString());            
//            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");          
//            oMysql.updateOne(oCompraBean.getId(), "compra", "fecha", sdf.format(oCompraBean.getFecha()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("CompraDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(CompraBean oCompraBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oCompraBean.getId(), "compra");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("CompraDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
