/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.UsuarioBean;
import net.daw.bean.CaptcharBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author rafa
 */
public class CaptcharDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public CaptcharDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("captchar", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("CaptcharDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<CaptcharBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<CaptcharBean> arrCaptchar = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("captchar", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                CaptcharBean oCaptcharBean = new CaptcharBean(iterador.next());
                arrCaptchar.add(this.get(oCaptcharBean));
            }
            oMysql.desconexion();
            return arrCaptchar;
        } catch (Exception e) {
            throw new Exception("CaptcharDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public CaptcharBean get(CaptcharBean oCaptcharBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            oCaptcharBean.setPregunta(oMysql.getOne("captchar", "pregunta", oCaptcharBean.getId()));
            oCaptcharBean.setRespuesta(oMysql.getOne("captchar", "respuesta", oCaptcharBean.getId()));

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("CaptcharDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oCaptcharBean;
    }

    public void set(CaptcharBean oCaptcharBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oCaptcharBean.getId() == 0) {
                oCaptcharBean.setId(oMysql.insertOne("captchar"));
            }
            oMysql.updateOne(oCaptcharBean.getId(), "captchar", "pregunta", oCaptcharBean.getPregunta());
            oMysql.updateOne(oCaptcharBean.getId(), "captchar", "respuesta", oCaptcharBean.getRespuesta());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("CaptcharDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(CaptcharBean oCaptcharBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oCaptcharBean.getId(), "captchar");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("CaptcharDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
