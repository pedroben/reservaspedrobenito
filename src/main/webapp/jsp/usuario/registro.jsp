<%@page import="java.util.ArrayList"%>
<%@page import="net.daw.dao.CaptcharDao"%>
<%@page import="net.daw.bean.CaptcharBean"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.UsuarioBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String nombre = "";
    String login = "";
    String password = "";
    Integer id_captchar = 0;
    String preguntaCaptchar = "";
    String respuestaCaptchar = "";

    ArrayList<Object> objetos = (ArrayList<Object>) oContexto.getParametro();
    UsuarioBean oUsuarioBean = (UsuarioBean) objetos.get(0);
    CaptcharBean oCaptcharBean = (CaptcharBean) objetos.get(1);
    
    id = oUsuarioBean.getId();
    nombre = oUsuarioBean.getNombre();
    login = oUsuarioBean.getLogin();
    password = oUsuarioBean.getPassword();
    
    id_captchar = oCaptcharBean.getId();
    preguntaCaptchar = oCaptcharBean.getPregunta();

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
<h1><%=strTitulo%> de usuario</h1>
<form class="form-horizontal" action="Controller" method="post" id="usuarioForm">
    <fieldset>
        <legend>Formulario de usuario</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="usuario" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <input type="hidden" name="id_captchar" value="<%=id_captchar%>" />
        <div class="control-group">
            <label class="control-label" for="nombre">Nombre: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="nombre" name="nombre" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=nombre%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="login">Usuario: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="login" name="login" type="text" size="30" maxlength="50" value="<%=login%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="password">Contraseña: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="password" name="password" type="password" size="30" maxlength="50" value="<%=password%>" /> <br />
            </div>
        </div>
            <div class="recuadrar"><h5>Captchar</h5>    
        <div class="control-group">
            <label class="control-label" for="pregunta">Pregunta: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> class="input-xxlarge" id="pregunta" name="pregunta" type="text" size="50" maxlength="50" value="<%=preguntaCaptchar%>" readonly="true" /> <br />
            </div>
        </div>    
        <div class="control-group">
            <label class="control-label" for="respuesta">Respuesta </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="respuesta" name="respuesta" type="text" size="30" maxlength="50" value="" /> <br />
            </div>
        </div>    
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
