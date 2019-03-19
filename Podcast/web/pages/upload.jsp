<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="pt-br" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" charset="ISO-8859-1" content="text/html; charset=ISO-8859-1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>IFCast - Upload</title>

    <style>
        <jsp:include page="../css/materialize.css" />
        <jsp:include page="../css/style.css" />
    </style>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>
<%@ include file = "headerLogged.jsp" %>


<div class="row">
    <div class="center">
        <form class="center col s4">
            <div class="row">
                <div class="input-field col s6">
                    <input id="t�tulo" type="text" class="validate">
                    <label for="t�tulo">T�tulo</label>
                </div>
                <div class="input-field col s6">
                    <input id="categoria" type="text" class="validate">
                    <label for="categoria">Categoria</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="decricao" type="text" class="validate">
                    <label for="decricao">Descri��o</label>
                </div>
            </div>
            <div class="file-field input-field">
                <div class="btn">
                    <span>Arquivo de �udio PodCast</span>
                    <input type="file">
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text">
                </div>
            </div>
        </form>
    </div>
</div>



<%@ include file = "footer.jsp" %>

<!--  Scripts-->
<script type="text/javascript"
        src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js">
</script>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

<script src="../js/materialize.js"></script>
<script src="../js/init.js"></script>

</body>
</html>
