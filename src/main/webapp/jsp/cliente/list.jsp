
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
    <div class="span8">
        <h1>Listado de clientes</h1>
        <%
            if (!oIterador.hasNext()) {
                out.print("<h4>Listado vacío</h4>");
            } else {
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
    <div class="span4">
        <div class="text-right">
            <%
                if (oContexto.getHmOrder() != null) {
                    out.print("<h4>Listado ordenado por " + oContexto.getHmOrder().keySet().toArray()[0].toString() + "</h4>");
                    out.print("<a href=\"Controller?class=cliente&method=list\">Quitar orden</a>");
                }
            %>
        </div>
    </div>
</div>
<table class="table table-hover table-condensed">
    <tr>
        <th>id
            <a href="Controller?class=cliente&method=list&order=id&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=cliente&method=list&order=id&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>nombre
            <a href="Controller?class=cliente&method=list&order=nombre&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=cliente&method=list&order=nombre&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>primer apellido
            <a href="Controller?class=cliente&method=list&order=ape1&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=cliente&method=list&order=ape1&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>segundo apellido
            <a href="Controller?class=cliente&method=list&order=ape2&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=cliente&method=list&order=ape2&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>email
            <a href="Controller?class=cliente&method=list&order=email&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=cliente&method=list&order=email&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
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
