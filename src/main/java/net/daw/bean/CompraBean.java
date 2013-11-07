package net.daw.bean;

import java.util.Date;

public class CompraBean {

    private int id = 0;
    private ClienteBean cliente;
    private ProductoBean producto;
    private Integer cantidad;
    private Date fecha;

    public CompraBean() {
        this.cliente = new ClienteBean();
        this.producto = new ProductoBean();
    }

    public CompraBean(Integer intId) {
        this.id = intId;
        this.cliente = new ClienteBean();
        this.producto = new ProductoBean();
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
