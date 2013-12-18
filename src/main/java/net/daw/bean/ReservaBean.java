/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;

/**
 *
 * @author al037914
 */
public class ReservaBean {
    
    private int id = 0;
    private Date fecha;
    private HabitacionBean habitacion;
    private UsuarioBean usuario;
    
    public ReservaBean() {
        this.habitacion = new HabitacionBean();
        this.usuario = new UsuarioBean();
    }
    
    public ReservaBean(int id) {
        this.id = id;
        this.habitacion = new HabitacionBean();
        this.usuario = new UsuarioBean();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public HabitacionBean getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionBean habitacion) {
        this.habitacion = habitacion;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }
    
}
