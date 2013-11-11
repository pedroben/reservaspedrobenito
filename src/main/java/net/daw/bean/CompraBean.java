package net.daw.bean;

import java.util.Date;

public class CompraBean {

    private int id = 0;
    private ClienteBean cliente = null;
    private ProductoBean producto = null;
    private Integer cantidad = 0;
    private Date fecha = new Date();

    public CompraBean() {
        this.cliente = new ClienteBean();
        this.cliente.setId(0);
        this.producto = new ProductoBean();
        this.producto.setId(0);
    }

    public CompraBean(Integer intId) {
        this.id = intId;
        this.cliente = new ClienteBean();
        this.cliente.setId(0);
        this.producto = new ProductoBean();
        this.producto.setId(0);
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
