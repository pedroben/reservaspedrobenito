
package net.daw.bean;

import java.util.Date;

public class CompraBean {
    private int id = 0;
    private ClienteBean cliente;
    private ProductoBean producto;
    private Integer cantidad;
    private Date fecha;

       public CompraBean() {
     
    }
    
    public CompraBean(Integer intId) {
        this.id=intId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public ProductoBean getProducto() {
        return producto;
    }

    public void setProducto(ProductoBean producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    
            
}
