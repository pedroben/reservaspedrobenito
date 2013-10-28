<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.ProductoBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String codigo = "";
    String descripcion = "";
    String precio = "";
    String id_tipoproducto = "";
    if ("update".equals(oContexto.getMetodo()) || oContexto.getMetodo().equals("view")) {
        ProductoBean oProductoBean = (ProductoBean) oContexto.getParametro();
        id = oProductoBean.getId();
        codigo = oProductoBean.getCodigo();
        descripcion = oProductoBean.getDescripcion();
        precio = Float.toString(oProductoBean.getPrecio());
        id_tipoproducto = Integer.toString(oProductoBean.getId_tipoproducto());
    }
    if (oContexto.getMetodo().equals("view")) {
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strValueBoton = "Crear";
    }
%>
<h1>Edición de producto</h1>
<form class="semantic" action="Controller" method="post" id="clienteForm">
    <fieldset>
        <legend>Formulario de producto</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="producto" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div>
            <label for="codigo">Código: </label> 
            <input <%=strControlEnabled%>  id="codigo" name="codigo"
                   type="text" size="30" maxlength="50" autofocus="autofocus"
                   value="<%=codigo%>" />
            <br />
        </div>
        <div>
            <label for="descripcion">Descripción: </label> 
            <input <%=strControlEnabled%>  id="descripcion"
                   name="descripcion" type="text" size="30" maxlength="50"
                   value="<%=descripcion%>" />
            <br />
        </div>
        <div>
            <label for="precio">Precio: </label> 
            <input <%=strControlEnabled%>  id="precio"
                   name="precio" type="text" size="30" maxlength="50"
                   value="<%=precio%>" /> 
            <br />
        </div>
        <div>
            <label for="id_tipoproducto">Tipo de producto: </label> 
            <input <%=strControlEnabled%>  id="id_tipoproducto"
                   name="id_tipoproducto" type="text" size="30" maxlength="50"
                   value="<%=id_tipoproducto%>" /> 
            <input type="submit" name="id_tipoproducto" value="id_tipoproducto" />
            
            <br />
        </div>            
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
