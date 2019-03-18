<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt">
<head>

    <meta http-equiv=”Content-Type” charset="UTF-8" content=”text/html;″>

    <meta http-equiv=”Content-Type” content=”text/html; charset=iso-8859-1″>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>IFCast - Início</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="../css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

    <link href="../css/timeline.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <style>
        .circle{ border-radius: 50%;}
    </style>



</head>
<body>

<%@ include file = "headerLogged.jsp" %>
<div class="timeline">
    <div class="row">
        <div class="col s8 m3">
            <div class="card" id="podcast">
                <div class="card-image">
                    <img src="https://images-na.ssl-images-amazon.com/images/I/41AZ6IMW5gL.jpg" class="capa">
                    <span class="card-title">Bohemian Rhapsody</span>
                    <a class="btn-floating halfway-fab waves-effect waves-light red" href="moduloPodcast.jsp"><i class="material-icons">play_arrow</i></a>
                </div>
                <div class="card-content deep-purple darken-1">
                    <p class="autor white-text">Queen</p><br>
                    <p class="descricao white-text"> Composta em 1975 por Freddie Mercury. Foi a primeira música a alcançar a
                        primeira posição duas vezes com a mesma versão.</p>
                </div>
            </div>
        </div>
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

<script>
    $(document).ready(function () {
        $('.modal').modal();
    });
</script>
</body>
</html>
