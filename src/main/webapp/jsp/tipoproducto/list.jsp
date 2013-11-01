
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
    <div class="span8">
        <% if (!oContexto.getMetodo().equalsIgnoreCase("selectone")) {
                out.print("<h1>Listado de tipos de productos</h1>");
            } else {
                out.print("<h1>Selección de tipos de productos</h1>");
            }
            if (!oIterador.hasNext()) {
                out.print("<h4>Listado vacío</h4>");
            } else {
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
                    out.print("<a href=\"Controller?class=tipoproducto&method=list\">Quitar orden</a>");
                }
            %>
        </div>
    </div>
</div>
<table class="table table-hover table-condensed">
    <tr>
        <th>id
            <a href="Controller?class=tipoproducto&method=list&order=id&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=tipoproducto&method=list&order=id&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>descripción
            <a href="Controller?class=tipoproducto&method=list&order=descripcion&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=tipoproducto&method=list&order=descripcion&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
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
                        <% } else {%>
                    <a class="btn btn-mini" href="Controller?class=producto&method=updatetipoproducto&id=<%=oContexto.getId()%>&id_tipoproducto=<%=oTipoproductoBean.getId()%>&selectonetable=<%=oContexto.getSelectOneTable()%>&selectonefield=<%=oContexto.getSelectOneField()%>"><i class="icon-ok"></i></a>                    
                        <% } %>
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
