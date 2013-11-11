
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.TipoproductoBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author rafa
 */
public class TipoproductoDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;
    private final String strTabla = "tipoproducto";

    public TipoproductoDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages(strTabla, intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<TipoproductoBean> getPage(int intRegsPerPag, int intPage,ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> alId;
        ArrayList<TipoproductoBean> alBeans = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            alId = oMysql.getPage(strTabla, intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = alId.listIterator();
            while (iterador.hasNext()) {
                TipoproductoBean oTipoproductoBean = new TipoproductoBean(iterador.next());
                alBeans.add(this.get(oTipoproductoBean));
            }
            oMysql.desconexion();
            return alBeans;
        } catch (Exception e) {
            throw new Exception("TiporoductoDao.getPage: Error: " + e.getMessage());
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

    public TipoproductoBean get(TipoproductoBean oTipoproductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oTipoproductoBean.setDescripcion(oMysql.getOne(strTabla, "descripcion", oTipoproductoBean.getId()));
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("TiporoductoDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oTipoproductoBean;
    }

    public void set(TipoproductoBean oTipoproductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oTipoproductoBean.getId() == 0) {
                oTipoproductoBean.setId(oMysql.insertOne(strTabla));
            }
            oMysql.updateOne(oTipoproductoBean.getId(), strTabla, "descripcion", oTipoproductoBean.getDescripcion());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("TiporoductoDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(TipoproductoBean oTipoproductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oTipoproductoBean.getId(), strTabla);
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("TiporoductoDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
