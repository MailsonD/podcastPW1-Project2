<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>IFCast - Criar turma </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>IFCast</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="../css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<%@ include file="headerLogged.jsp" %>

<div class="card-panel">
    <div class="row">
        <form class="col s12">
            <div class="row">
                <div class="input-field col s12">
                    <input id="nome" type="text" class="validate">
                    <label for="nome">Nome</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="descricao" type="text" class="validate">
                    <label for="descricao">Descrição</label>
                </div>
            </div>

            <div class="row">
                <div class="col s6">
                    <div class="input-field col s8">
                        <select>
                            <%--<option value="" disabled selected>Choose your option</option>--%>
                            <option value="1">Option 1</option>
                            <option value="2">Option 2</option>
                            <option value="3">Option 3</option>
                        </select>
                        <label>Materialize Select</label>
                    </div>
                    <div class="input-field col s4">
                        <div id="membros">
                            <a class="btn-floating btn-large waves-effect waves-light red"><i
                                    class="material-icons">add</i></a> Adicionar membros
                        </div>
                    </div>
                </div>
                <div class="col s6">
                    <div class="col s12">
                        <div class="card blue-grey darken-1">
                            <div class="card-content white-text">
                                <span class="card-title">Alunos adicionados</span>
                                <ul class="collection with-header" id="listaAlunos">
                                    <li class="collection-item black-text"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
                                    <li class="collection-item"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
                                    <li class="collection-item"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
                                    <li class="collection-item"><div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
                                </ul>

                            </div>
                            <div class="card-action">
                                <button class="btn waves-effect waves-light right" type="submit" name="action">Criar
                                    <i class="material-icons right">cloud</i>
                                </button>
                                <div class="row"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <%--<div class="row">--%>
            <%--<div class="input-field col s12">--%>

            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="input-field col s12">--%>
            <%----%>
            <%--</div>--%>
            <%--</div>--%>

        </form>
    </div>
</div>

<%@ include file="footer.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('select').formSelect();
        $('.sidenav').sidenav();
    });
</script>
</body>
</html>