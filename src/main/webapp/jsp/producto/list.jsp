
<%@ page import="net.daw.helper.Contexto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.ProductoBean"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<ProductoBean> alPagina = (ArrayList<ProductoBean>) alObjetoParametro.get(0);
    Iterator<ProductoBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de productos</h1>
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
                    out.print("<a href=\"Controller?class=producto&method=list\">Quitar orden</a>");
                }
            %>
        </div>
    </div>
</div>
<table class="table table-hover table-condensed">
    <tr>
        <th>id
            <a href="Controller?class=producto&method=list&order=id&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=producto&method=list&order=id&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>código
            <a href="Controller?class=producto&method=list&order=codigo&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=producto&method=list&order=codigo&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>descripción
            <a href="Controller?class=producto&method=list&order=descripcion&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=producto&method=list&order=descripcion&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>precio
            <a href="Controller?class=producto&method=list&order=precio&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?class=producto&method=list&order=precio&ordervalue=desc"><i class="icon-arrow-down"></i></a>                
        </th>
        <th>tipo de producto
        <a href="Controller?class=producto&method=list&order=id_tipoproducto&ordervalue=asc"><i class="icon-arrow-up"></i></a>
        <a href="Controller?class=producto&method=list&order=id_tipoproducto&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
    </th>
<th>Operaciones</th>
</tr>
<%
    while (oIterador.hasNext()) {
        ProductoBean oProductoBEAN = oIterador.next();
%>
<tr>
    <td><%=oProductoBEAN.getId()%></td>
    <td><%=oProductoBEAN.getCodigo()%></td>
    <td><%=oProductoBEAN.getDescripcion()%></td>
    <td><%=oProductoBEAN.getPrecio()%>&euro;</td>
    <td>
        
            <%=oProductoBEAN.getTipoProducto().getDescripcion()%>
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=tipoproducto&method=selectone&id=<%=oProductoBEAN.getId()%>&selectonetable=producto&selectonefield=id_tipoproducto"><i class="icon-search"></i></a>                                        
            </div>
   
    </td>
    <td>
        <div class="btn-toolbar">
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=producto&method=view&id=<%=oProductoBEAN.getId()%>"><i class="icon-eye-open"></i></a>                    
                <a class="btn btn-mini" href="Controller?class=producto&method=update&id=<%=oProductoBEAN.getId()%>"><i class="icon-pencil"></i></a>           
                <a class="btn btn-mini" href="Controller?class=producto&method=remove&id=<%=oProductoBEAN.getId()%>"><i class="icon-trash"></i></a>            
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
