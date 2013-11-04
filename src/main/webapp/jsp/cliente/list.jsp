
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.ClienteBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<ClienteBean> alPagina = (ArrayList<ClienteBean>) alObjetoParametro.get(0);
    Iterator<ClienteBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span9">
        <h1>Listado de clientes</h1>
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
                            <option>nombre</option>
                            <option>ape1</option>
                            <option>ape2</option>
                            <option>email</option>
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
        <th>nombre
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=nombre&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=nombre&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>primer apellido
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=ape1&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=ape1&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>segundo apellido
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=ape2&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=ape2&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>email
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=email&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=email&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>Relaciones</th>
        <th>Operaciones</th>
    </tr>
    <%        while (oIterador.hasNext()) {
            ClienteBean oClienteBEAN = oIterador.next();
    %>
    <tr>
        <td><%=oClienteBEAN.getId()%></td>
        <td><%=oClienteBEAN.getNombre()%></td>
        <td><%=oClienteBEAN.getApe1()%></td>
        <td><%=oClienteBEAN.getApe2()%></td>
        <td><%=oClienteBEAN.getEmail()%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">                    
                    <a class="btn btn-mini" href="Controller?class=compra&method=list&foreignfield=id_cliente&foreignfieldvalue=<%=oClienteBEAN.getId()%>">Compras</a>                    
                </div>
            </div>
        </td>        
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">                    
                    <a class="btn btn-mini" href="Controller?class=cliente&method=view&id=<%=oClienteBEAN.getId()%>"><i class="icon-eye-open"></i></a>                    
                    <a class="btn btn-mini" href="Controller?class=cliente&method=update&id=<%=oClienteBEAN.getId()%>"><i class="icon-pencil"></i></a>           
                    <a class="btn btn-mini" href="Controller?class=cliente&method=remove&id=<%=oClienteBEAN.getId()%>"><i class="icon-trash"></i></a>                         
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
