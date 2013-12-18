<%@page import="java.util.Date"%>
<%@page import="net.daw.helper.Fechas"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.ReservaBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String anyo = "";
    String mes = "";
    String dia = "";
    String id_habitacion = "";
    String numeroHabitacion = "";
    String id_usuario = "";
    String nombreUsuario = "";


    String strMesEnabled = "disabled=\"true\"";
    String strDiaEnabled = "disabled=\"true\"";
    if (request.getParameter("anyo") != null) {
        anyo = request.getParameter("anyo");
        strMesEnabled = "";
    }
    if (request.getParameter("mes") != null) {
        mes = request.getParameter("mes");
        strDiaEnabled = "";
    }
    if (request.getParameter("dia") != null) {
        dia = request.getParameter("dia");
    }

    ReservaBean oReservaBean = (ReservaBean) oContexto.getParametro();
    id = oReservaBean.getId();

    id_habitacion = Integer.toString(oReservaBean.getHabitacion().getId());
    if (oReservaBean.getHabitacion().getId() > 0) {
        numeroHabitacion = Integer.toString(oReservaBean.getHabitacion().getNumero());
    }
    id_usuario = Integer.toString(oReservaBean.getUsuario().getId());
    if (oReservaBean.getUsuario().getId() > 0) {
        nombreUsuario = oReservaBean.getUsuario().getNombre();
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
<h1><%=strTitulo%> de reserva</h1>
<form class="form-horizontal" action="Controller" method="post" id="reservaForm">
    <legend>Formulario de reserva</legend>
    <input type="hidden" name="id" value="<%=id%>" /> 
    <input type="hidden" name="class" value="reserva" /> 
    <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
    <input type="hidden" name="phase" value="2" />
    
    <div class="control-group">
        <label class="control-label" for="id_usuario">Usuario </label> 
        <div class="controls">                
            <input readonly="true" id="id_usuario" class="input-mini"
                   name="id_usuario" type="text" size="5" maxlength="5"
                   value="<%=id_usuario%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
            <span class="alert alert-success"><%=nombreUsuario%></span>
        </div>
    </div>             

    <div class="control-group">
        <label class="control-label" for="id_habitacion">Habitacion: </label> 
        <div class="controls">                
            <input readonly="true" id="id_habitacion" class="input-mini"
                   name="id_habitacion" type="text" size="5" maxlength="5"
                   value="<%=id_habitacion%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="habitacion" />
            <span class="alert alert-success"><%=numeroHabitacion%></span>
        </div>
    </div>   

    <div>    
        <div class="control-group">
            <label class="control-label" for="anyo">Año: </label> 
            <div class="controls">
                <!-- -->
                <select <%=strControlEnabled%>  id="anyo" name="anyo" class="input-small">
                    <%for (int i = 2013; i <= 2020; i++) {%>
                    <option value="<%=i%>"
                            <% if (anyo != "") {
                                    if (i == Integer.parseInt(anyo)) {%>
                            selected <% }%>
                            <% }%>
                            ><%=i%></option>
                    <% }%>    
                </select>
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="Establecer año" />
                <!-- --> 
            </div> 
        </div>
        <div class="control-group">
            <label class="control-label" for="mes">Mes: </label> 
            <div class="controls">
                <!-- -->
                <select <%=strMesEnabled%>  id="mes" name="mes" class="input-mini">
                    <% if(anyo != "") { %>
                    <%for (int i = 1; i <= 12; i++) { %>
                        <option value="<%=i%>" 
                        <% if (mes != "") { %>
                            <% if (i == Integer.parseInt(mes)) { %>
                                selected 
                            <% } %>
                        <% } %>
                        ><%=i%>
                        </option>
                    <% } %>    
                    <% } %>
                </select>
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="Establecer mes" />
                <!-- --> 
            </div>   
        </div>
        <div class="control-group">
            <label class="control-label" for="dia">Dia: </label> 
            <div class="controls">
                <!-- -->
                <%
                    int intPrimerDiaMes = 0;
                    int intUltimoDiaMes = 0;
                    if (anyo != "" && mes != "") {
                        intPrimerDiaMes = 1;
                        intUltimoDiaMes = Fechas.getUltimoDiaDelMes(Integer.parseInt(anyo),Integer.parseInt(mes) - 1);
                    }
                %>
                <select <%=strDiaEnabled%>  id="dia" name="dia" class="input-mini">
                    <% if(mes != "") { %>
                    <%for (int i = intPrimerDiaMes; i <= intUltimoDiaMes; i++) {%>
                    <option value="<%=i%>"
                            <% if (dia != "") {
                                    if (i == Integer.parseInt(dia)) {%>
                            selected <% }%>
                            <% }%>
                            ><%=i%></option>
                    <% } %>
                    <% } %>
                    <input <%=strControlEnabled%> type="submit" name="searchingfor" value="Establecer dia" />
                </select>
                <!-- --> 
            </div> 
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </div>
</form>