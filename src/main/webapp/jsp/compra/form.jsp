
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.CompraBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    
    Integer id = 0;
    String cliente="";
    String producto="";
    String cantidad = "";
    String fecha = "";
    
    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        CompraBean oCompraBean = (CompraBean) oContexto.getParametro();        
        id = oCompraBean.getId();
        cliente = oCompraBean.getCliente().getNombre();
        producto = oCompraBean.getProducto().getDescripcion();
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
        <legend>Formulario de cliente</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="compra" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div>
            <label for="nombre">Cliente: </label> 
            <input <%=strControlEnabled%> id="nombre" name="nombre" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=cliente%>" /><br />
        </div>
        <div>
            <label for="ape1">Producto: </label>
            <input <%=strControlEnabled%> id="ape1" name="ape1" type="text" size="30" maxlength="50" value="<%=producto%>" /><br />
        </div>
        <div>
            <label for="ape2">Cantidad: </label> 
            <input <%=strControlEnabled%> id="ape2" name="ape2" type="text" size="30" maxlength="50" value="<%=cantidad%>" /> <br />
        </div>
        <div>
            <label for="email">Fecha: </label> 
            <input <%=strControlEnabled%> id="email" name="email" type="text" size="30" maxlength="50" value="<%=fecha%>" /><br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>