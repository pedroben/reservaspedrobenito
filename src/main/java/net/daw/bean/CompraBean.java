/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.bean;

import java.util.Date;

/**
 *
 * @author rafa
 */
public class CompraBean {
    private int id = 0;
    private ClienteBean oCliente;
    private ProductoBean oProducto;
    private int Cantidad;
    private Date a;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClienteBean getoCliente() {
        return oCliente;
    }

    public void setoCliente(ClienteBean oCliente) {
        this.oCliente = oCliente;
    }

    public ProductoBean getoProducto() {
        return oProducto;
    }

    public void setoProducto(ProductoBean oProducto) {
        this.oProducto = oProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Date getA() {
        return a;
    }

    public void setA(Date a) {
        this.a = a;
    }
    
            
}
