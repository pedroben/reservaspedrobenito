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
import net.daw.bean.HabitacionBean;
import net.daw.bean.UsuarioBean;
import net.daw.bean.ReservaBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author rafa
 */
public class ReservaDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public ReservaDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("reserva", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ReservaDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("reserva", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ReservaDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<ReservaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ReservaBean> arrReserva = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("reserva", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ReservaBean oReservaBean = new ReservaBean(iterador.next());
                arrReserva.add(this.get(oReservaBean));
            }
            oMysql.desconexion();
            return arrReserva;
        } catch (Exception e) {
            throw new Exception("ReservaDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ReservaBean get(ReservaBean oReservaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            
            UsuarioBean oUsuarioBean = new UsuarioBean();
            HabitacionBean oHabitacionBean = new HabitacionBean();
            
            oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("reserva","id_usuario",oReservaBean.getId())));
            oHabitacionBean.setId(Integer.parseInt(oMysql.getOne("reserva","id_habitacion",oReservaBean.getId())));
            
            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            HabitacionDao oHabitacionDao = new HabitacionDao(enumTipoConexion);
            
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
            oHabitacionBean = oHabitacionDao.get(oHabitacionBean);
            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy-MM-dd");
            String fecha = oMysql.getOne("reserva","fecha",oReservaBean.getId());
            oReservaBean.setFecha(formatoFecha.parse(fecha));
            oReservaBean.setHabitacion(oHabitacionBean);
            oReservaBean.setUsuario(oUsuarioBean);

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ReservaDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oReservaBean;
    }

    public void set(ReservaBean oReservaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oReservaBean.getId() == 0) {
                oReservaBean.setId(oMysql.insertOne("reserva"));
            }
            oMysql.updateOne(oReservaBean.getId(), "reserva", "id_usuario", Integer.toString(oReservaBean.getUsuario().getId()));
            oMysql.updateOne(oReservaBean.getId(), "reserva", "id_habitacion", Integer.toString(oReservaBean.getHabitacion().getId()));
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oReservaBean.getId(), "reserva", "fecha", formatoFecha.format(oReservaBean.getFecha()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ReservaDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(ReservaBean oReservaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oReservaBean.getId(), "reserva");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ReservaDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
