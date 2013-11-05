
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.TipoproductoBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<TipoproductoBean> alPagina = (ArrayList<TipoproductoBean>) alObjetoParametro.get(0);
    Iterator<TipoproductoBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span9">
        <% if (!oContexto.getMetodo().equalsIgnoreCase("selectone")) {
                out.print("<h1>Listado de tipos de productos</h1>");
            } else {
                out.print("<h1>Selección de tipos de productos</h1>");
            }
        %>
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
            if (oContexto.getHmFilter() != null) {
                out.print("<p>Listado filtrado por " + oContexto.getHmFilter().keySet().toArray()[0].toString() + "    ");
                out.print("<a href=\"Controller?" + oContexto.getSerializedParamsExceptFilter() + "\">(Quitar filtro)</a></p>");
            } else {
                out.print("<p>Sin filtrar</p>");
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
    <div class="span3">
        <div class="text-right">
            <legend>Filtro de cliente</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="clienteForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>descripcion</option>
                        </select>                        
                        <input id="filtervalue" name="filtervalue" type="text" size="20" maxlength="50" value=""  width="100" style="width: 100px"/>
                    </span>
                    <span>
                        <input type="submit" name="enviar" value="Filtrar" />
                    </span>
                </fieldset>
        </div>
    </div>
</div>
<table class="table table-hover table-condensed">
    <tr>
        <th>id
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>descripción
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=descripcion&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=descripcion&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th colspan="2">Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
            TipoproductoBean oTipoproductoBean = oIterador.next();
    %>
    <tr>
        <td><%=oTipoproductoBean.getId()%></td>
        <td><%=oTipoproductoBean.getDescripcion()%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <% if (!oContexto.getMetodo().equalsIgnoreCase("selectone")) {%>
                    <a class="btn btn-mini" href="Controller?class=tipoproducto&method=view&id=<%=oTipoproductoBean.getId()%>"><i class="icon-eye-open"></i></a>                    
                    <a class="btn btn-mini" href="Controller?class=tipoproducto&method=update&id=<%=oTipoproductoBean.getId()%>"><i class="icon-pencil"></i></a>           
                    <a class="btn btn-mini" href="Controller?class=tipoproducto&method=remove&id=<%=oTipoproductoBean.getId()%>"><i class="icon-trash"></i></a>            
                        <% } else {
                            if (oContexto.getSearchingFor().equals("tipoproducto")) {
                        %>
                    <a class="btn btn-mini" href="Controller?<%=oContexto.getSerializedParamsExceptClassMethod()%>&class=<%=oContexto.getClaseRetorno()%>&method=<%=oContexto.getMetodoRetorno()%>&id_tipoproducto=<%=oTipoproductoBean.getId()%>"><i class="icon-ok"></i></a>                   
                        <% } else {%>
                    <a class="btn btn-mini" href="Controller?class=producto&method=updatetipoproducto&id=<%=oContexto.getId()%>&id_tipoproducto=<%=oTipoproductoBean.getId()%>"><i class="icon-ok"></i></a>  -->                    

                    <% }
                        } %>
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
