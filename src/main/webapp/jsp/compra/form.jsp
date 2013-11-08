
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
    String cantidad = "";
    String fecha = "";

    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        CompraBean oCompraBean = (CompraBean) oContexto.getParametro();
        id = oCompraBean.getId();
        id_cliente = Integer.toString(oCompraBean.getCliente().getId());
        descCliente = oCompraBean.getCliente().getNombre();
        id_producto = Integer.toString(oCompraBean.getProducto().getId());
        descProducto = oCompraBean.getProducto().getDescripcion();
        cantidad = oCompraBean.getCantidad().toString();
        fecha = oCompraBean.getFecha().toString();
    }

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
<h1><%=strTitulo%> de cliente</h1>
<form class="semantic" action="Controller" method="post" id="clienteForm">
    <fieldset>
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
                <input type="submit" name="searchingfor" value="producto" />

                <span class="label"><%=descProducto%></span>
            </div>
        </div>             

        <div class="control-group">
            <label class="control-label" for="id_cliente">Cliente: </label> 
            <div class="controls">                
                <input readonly="true" id="id_cliente" class="input-mini"
                       name="id_producto" type="text" size="5" maxlength="5"
                       value="<%=id_cliente%>" />  
                <input type="submit" name="searchingfor" value="cliente" />
                <span class="label"><%=descCliente%></span>
            </div>
        </div>             
        <div class="control-group">
            <label class="control-label" for="precio">Cantidad: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="cantidad"
                                               name="cantidad" type="text" size="30" maxlength="50"
                                               value="<%=cantidad%>" /> 
            </div>

            <label class="control-label" for="precio">Fecha: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fecha"
                                               name="fecha" type="text" size="30" maxlength="50"
                                               value="<%=fecha%>" /> 
            </div>


            <div class="control-group">
                <div class="controls">
                    <input type="submit" name="enviar" value="<%=strValueBoton%>" />
                </div>
            </div>




    </fieldset>
</form>