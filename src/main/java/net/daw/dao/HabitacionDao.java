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
import net.daw.bean.HabitacionBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author rafa
 */
public class HabitacionDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public HabitacionDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("habitacion", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("HabitacionDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("habitacion", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("HabitacionDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<HabitacionBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<HabitacionBean> arrHabitacion = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("habitacion", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                HabitacionBean oHabitacionBean = new HabitacionBean(iterador.next());
                arrHabitacion.add(this.get(oHabitacionBean));
            }
            oMysql.desconexion();
            return arrHabitacion;
        } catch (Exception e) {
            throw new Exception("HabitacionDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public HabitacionBean get(HabitacionBean oHabitacionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            oHabitacionBean.setNumero(Integer.parseInt(oMysql.getOne("habitacion", "numero", oHabitacionBean.getId())));
            oHabitacionBean.setCapacidad(Integer.parseInt(oMysql.getOne("habitacion", "capacidad", oHabitacionBean.getId())));

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("HabitacionDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oHabitacionBean;
    }

    public void set(HabitacionBean oHabitacionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oHabitacionBean.getId() == 0) {
                oHabitacionBean.setId(oMysql.insertOne("habitacion"));
            }
            oMysql.updateOne(oHabitacionBean.getId(), "habitacion", "numero", Integer.toString(oHabitacionBean.getNumero()));
            oMysql.updateOne(oHabitacionBean.getId(), "habitacion", "capacidad", Integer.toString(oHabitacionBean.getCapacidad()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("HabitacionDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(HabitacionBean oHabitacionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oHabitacionBean.getId(), "habitacion");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("HabitacionDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
