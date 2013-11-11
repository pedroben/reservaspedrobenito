<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto"); %>
<%
    String menu = "<div class=\"well sidebar-nav\"><ul class=\"nav nav-list\">";

    menu += "<li class=\"nav-header\">Usuario</li>";

    if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("ocioso")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    
    menu += "<a href=\"Controller\">Home</a></li>";

    if (!oContexto.getHaySesion()) {
        if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("login")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=usuario&method=login\">Login</a></li>";
    } else {
        if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("logout")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=usuario&method=logout\">Logout</a></li>";
    }
    //-----------------------------------------------
    menu += "<li class=\"nav-header\">Cliente</li>";

    if (oContexto.getClase().equals("cliente") && oContexto.getMetodo().equals("new")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller?class=cliente&method=new\">Crear</a></li>";

    if (oContexto.getClase().equals("cliente") && oContexto.getMetodo().equals("list")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller?class=cliente&method=list\">Listar</a></li>";
    //-----------------------------------------------
    menu += "<li class=\"nav-header\">Compra</li>";

    if (oContexto.getClase().equals("compra") && oContexto.getMetodo().equals("new")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller?class=compra&method=new\">Crear</a></li>";

    if (oContexto.getClase().equals("compra") && oContexto.getMetodo().equals("list")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller?class=compra&method=list\">Listar</a></li>";    
    //-----------------------------------------------
    menu += "<li class=\"nav-header\">Producto</li>";
    
    if (oContexto.getClase().equals("producto") && oContexto.getMetodo().equals("new")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller?class=producto&method=new\">Crear</a></li>";
    if (oContexto.getClase().equals("producto") && oContexto.getMetodo().equals("list")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller?class=producto&method=list\">Listar</a></li>";

    menu += "<li class=\"nav-header\">Tipo de Producto</li>";
    
    if (oContexto.getClase().equals("tipoproducto") && oContexto.getMetodo().equals("list")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller?class=tipoproducto&method=list\">Listar</a></li>";   
   
    menu += "</ul></div>";
%>
<%=menu%>