
package net.daw.bean;

/**
 *
 * @author rafa
 */
public class TipoproductoBean {

    private int id = 0;
    private String descripcion = "";

    public TipoproductoBean() {

    }

    public TipoproductoBean(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
