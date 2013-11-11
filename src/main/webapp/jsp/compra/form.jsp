<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.CompraBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String id_cliente = "";
    String descCliente = "";
    String id_producto = "";
    String descProducto = "";
    String cantidad = "0";
    String fecha = "";

    CompraBean oCompraBean = (CompraBean) oContexto.getParametro();
    id = oCompraBean.getId();
    id_cliente = Integer.toString(oCompraBean.getCliente().getId());
    if (!(oCompraBean.getCliente().getNombre().equals("") && oCompraBean.getCliente().getApe1().equals(""))) {
        descCliente = oCompraBean.getCliente().getNombre() + " " + oCompraBean.getCliente().getApe1();
    }
    id_producto = Integer.toString(oCompraBean.getProducto().getId());
    if (oCompraBean.getProducto().getId() > 0) {
        descProducto = oCompraBean.getProducto().getDescripcion();
    }
    cantidad = oCompraBean.getCantidad().toString();
    fecha = new SimpleDateFormat("yyyy-MM-dd").format(oCompraBean.getFecha());

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de compra</h1>
<form class="form-horizontal" action="Controller" method="post" id="clienteForm">
    <legend>Formulario de compra</legend>
    <input type="hidden" name="id" value="<%=id%>" /> 
    <input type="hidden" name="class" value="compra" /> 
    <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
    <input type="hidden" name="phase" value="2" />
    <div class="control-group">
        <label class="control-label" for="id_producto">Producto: </label> 
        <div class="controls">                
            <input readonly="true" id="id_producto" class="input-mini"
                   name="id_producto" type="text" size="5" maxlength="5"
                   value="<%=id_producto%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="producto" />
            <span class="alert alert-success"><%=descProducto%></span>
        </div>
    </div>             

    <div class="control-group">
        <label class="control-label" for="id_cliente">Cliente: </label> 
        <div class="controls">                
            <input readonly="true" id="id_cliente" class="input-mini"
                   name="id_cliente" type="text" size="5" maxlength="5"
                   value="<%=id_cliente%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="cliente" />
            <span class="alert alert-success"><%=descCliente%></span>
        </div>
    </div>             
    <div class="control-group">
        <label class="control-label" for="precio">Cantidad: </label> 
        <div class="controls">
            <input <%=strControlEnabled%>  id="cantidad"
                                           name="cantidad" type="text" size="30" maxlength="50"
                                           value="<%=cantidad%>"  /> 
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="fecha">Fecha: </label> 
        <div class="controls">
            <input <%=strControlEnabled%>  id="fecha"
                                           name="fecha" type="date" size="30" maxlength="50"
                                           value="<%=fecha%>" /> 
        </div> 
    </div>
    <div class="control-group">
        <div class="controls">
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </div>
</form>