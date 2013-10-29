package net.daw.helper;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Contexto {

    private HashMap<String, String> parameters;

    private Integer id;

    private String clase;
    private String metodo;
    private String fase;

    private String selectOneTable;
    private String selectOneField;

    private Integer page;
    private Integer nrpp;

    private String filter;
    private String filterValue;

    private String order;
    private String orderValue;

    private String vista;

    private String titulo;
    private String mensaje;
    private Object parametro;

    private Boolean haySesion;

    private ArrayList<String> paginacion;
    private ArrayList<Object> listado;

    private Enum.Connection enumTipoConexion;

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNrpp() {
        return nrpp;
    }

    public void setNrpp(Integer nrpp) {
        this.nrpp = nrpp;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getParametro() {
        return parametro;
    }

    public void setParametro(Object parametro) {
        this.parametro = parametro;
    }

    public Boolean getHaySesion() {
        return haySesion;
    }

    public void setHaySesion(Boolean haySesion) {
        this.haySesion = haySesion;
    }

    public ArrayList<String> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(ArrayList<String> paginacion) {
        this.paginacion = paginacion;
    }

    public ArrayList<Object> getListado() {
        return listado;
    }

    public void setListado(ArrayList<Object> listado) {
        this.listado = listado;
    }

    public Enum.Connection getEnumTipoConexion() {
        return enumTipoConexion;
    }

    public void setEnumTipoConexion(Enum.Connection enumTipoConexion) {
        this.enumTipoConexion = enumTipoConexion;
    }

    public String getOperacion() {
        String strOperation = "";
        strOperation += Character.toUpperCase(clase.charAt(0)) + clase.substring(1);
        strOperation += Character.toUpperCase(metodo.charAt(0)) + metodo.substring(1);
        strOperation += fase;
        return strOperation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getSerializedParams() {
        String resultado = "";
        Iterator oIterator = this.parameters.entrySet().iterator();
        while (oIterator.hasNext()) {
            Map.Entry oEntrada = (Map.Entry) oIterator.next();
            resultado += oEntrada.getKey() + "=" + oEntrada.getValue() + "&";
            oIterator.remove();
        }
        return resultado.substring(0, resultado.length() - 2);
    }

    public String getSelectOneTable() {
        return selectOneTable;
    }

    public void setSelectOneTable(String selectOneTable) {
        this.selectOneTable = selectOneTable;
    }

    public String getSelectOneField() {
        return selectOneField;
    }

    public void setSelectOneField(String selectOneField) {
        this.selectOneField = selectOneField;
    }

}
