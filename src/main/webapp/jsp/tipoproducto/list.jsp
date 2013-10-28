
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
<h1>Listado de tipos de productos</h1>
<table class="table table-hover table-condensed">
    <tr>
        <th>id</th>
        <th>descripción</th>
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
                    <a class="btn btn-mini" href="Controller?class=producto&method=view&id=<%=oTipoproductoBean.getId()%>"><i class="icon-eye-open"></i></a>                    
                    <a class="btn btn-mini" href="Controller?class=producto&method=update&id=<%=oTipoproductoBean.getId()%>"><i class="icon-pencil"></i></a>           
                    <a class="btn btn-mini" href="Controller?class=producto&method=remove&id=<%=oTipoproductoBean.getId()%>"><i class="icon-trash"></i></a>            
                </div>
            </div>
        </td>
    </tr>
    <%
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
</table>
