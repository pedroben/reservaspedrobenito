package net.daw.bean;

public class ProductoBean {

    private int id;
    private String codigo;
    private String descripcion;
    private Float precio;
    private TipoproductoBean tipoProducto;
          
    public ProductoBean() {

    }

    public ProductoBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public TipoproductoBean getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoproductoBean tipoProducto) {
        this.tipoProducto = tipoProducto;
    }


}
