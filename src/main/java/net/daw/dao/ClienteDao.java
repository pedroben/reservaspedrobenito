package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.daw.bean.ClienteBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class ClienteDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public ClienteDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("cliente", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getPages: Error: " + e.getMessage());
        }
    }

    public int getCount( ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("cliente",  hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getCount: Error: " + e.getMessage());
        }
    }
  
    public ArrayList<ClienteBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean>  hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ClienteBean> arrCliente = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("cliente", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ClienteBean oClienteBean = new ClienteBean(iterador.next());
                arrCliente.add(this.get(oClienteBean));
            }
            oMysql.desconexion();
            return arrCliente;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getPage: Error: " + e.getMessage());
        }
    }

    public ClienteBean get(ClienteBean oClienteBean) throws Exception {
        if (oClienteBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("cliente", oClienteBean.getId())) {
                    oClienteBean.setId(0);
                } else {
                    oClienteBean.setNombre(oMysql.getOne("cliente", "nombre", oClienteBean.getId()));
                    oClienteBean.setApe1(oMysql.getOne("cliente", "ape1", oClienteBean.getId()));
                    oClienteBean.setApe2(oMysql.getOne("cliente", "ape2", oClienteBean.getId()));
                    oClienteBean.setEmail(oMysql.getOne("cliente", "email", oClienteBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("ClienteDao.getCliente: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oClienteBean.setId(0);
        }
        return oClienteBean;
    }

    public void set(ClienteBean oClienteBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oClienteBean.getId() == 0) {
                oClienteBean.setId(oMysql.insertOne("cliente"));
            }
            oMysql.updateOne(oClienteBean.getId(), "cliente", "nombre", oClienteBean.getNombre());
            oMysql.updateOne(oClienteBean.getId(), "cliente", "ape1", oClienteBean.getApe1());
            oMysql.updateOne(oClienteBean.getId(), "cliente", "ape2", oClienteBean.getApe2());
            oMysql.updateOne(oClienteBean.getId(), "cliente", "email", oClienteBean.getEmail());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ClienteDao.setCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(ClienteBean oClienteBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oClienteBean.getId(), "cliente");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ClienteDao.removeCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}
