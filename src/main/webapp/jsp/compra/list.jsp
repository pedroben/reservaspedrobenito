

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@page import="net.daw.bean.CompraBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<CompraBean> alPagina = (ArrayList<CompraBean>) alObjetoParametro.get(0);
    Iterator<CompraBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span9">
        <h1>Listado de compras</h1>
        <%
            if (!oIterador.hasNext()) {
                out.print("<h4>Listado vacío</h4>");
            } else {
        %>
        <%
            if (oContexto.getHmOrder() != null) {
                out.print("<p>Listado ordenado por " + oContexto.getHmOrder().keySet().toArray()[0].toString() + "    ");
                out.print("<a href=\"Controller?" + oContexto.getSerializedParamsExceptOrder() + "\">(Quitar orden)</a></p>");
            } else {
                out.print("<p>Sin ordenar</p>");
            }
        %>
        <%
            ArrayList<String> paginacion = (ArrayList<String>) alObjetoParametro.get(1);
            Iterator<String> iterador2 = paginacion.listIterator();
            while (iterador2.hasNext()) {
                String o = iterador2.next();
                out.print(o);
            }
        %>
    </div>
</div>
<table class="table table-hover table-condensed">
    <tr>
        <th>id
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>cliente
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=cliente&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=cliente&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>producto
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=producto&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=producto&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>cantidad
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=cantidad&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=cantidad&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>fecha
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
            CompraBean oCompraBEAN = oIterador.next();
    %>
    <tr>
        <td><%=oCompraBEAN.getId()%></td>
        <td>
            <%=oCompraBEAN.getCliente().getNombre()%>
        </td>
        <td>
            <%=oCompraBEAN.getProducto().getDescripcion()%>
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=producto&method=selectone&id=<%=oCompraBEAN.getId()%>"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <td><%=oCompraBEAN.getCantidad()%></td>
        <td><%=oCompraBEAN.getFecha()%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">                    
                    <a class="btn btn-mini" href="Controller?class=cliente&method=view&id=<%=oCompraBEAN.getId()%>"><i class="icon-eye-open"></i></a>                    
                    <a class="btn btn-mini" href="Controller?class=cliente&method=update&id=<%=oCompraBEAN.getId()%>"><i class="icon-pencil"></i></a>           
                    <a class="btn btn-mini" href="Controller?class=cliente&method=remove&id=<%=oCompraBEAN.getId()%>"><i class="icon-trash"></i></a>                         
                </div>
            </div>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
