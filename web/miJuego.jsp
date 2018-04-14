<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="game">
            
            <%if(((String)session.getAttribute("resulto")).equals("N")){%>
            
            <h3>Juego del Ahorcado</h3>
            <h2>Intento <%=session.getAttribute("intentos")%> de <%=session.getAttribute("intentosMax")%></h2>
            <h3>
                <b><%=session.getAttribute("escondidoStr")%></b> 
                <sub>(<%=((String)session.getAttribute("escondidoStr")).length()%>)</sub>
            </h3><br>
            <form action="Game" method="post">
                Elige Letra: <input type="text" name="intentosStr"><br><br>
                <input type="submit">
            </form>
            <h2>Número de partidas jugadas = <%=session.getAttribute("juegoNum")%></h2>
            
            <%}else if (((String)session.getAttribute("resulto")).equals("W")){%>

            <h3>Juego del Ahorcado</h3>
            <h2>Has ganado! La palabra era  <%=session.getAttribute("palabra")%></h2>
            <form action="Init" method="post">
                Nivel de Dificultad: <br>
                <input type="radio" name="difficultad" id="bajo" value="Bajo">
                <label for="bajo">Bajo</label><br>
                <input type="radio" name="difficultad" id="medio" value="Medio" checked>
                <label for="medio">Medio</label><br>
                <input type="radio" name="difficultad" id="alto" value="Alto">
                <label for="alto">Alto</label><br>
                Número de intentos: <input type="text" name="intentos" value="10"><br><br>
                <input type="submit">
            </form>
            <h2>Número de partidas jugadas = <%=session.getAttribute("juegoNum")%></h2>
            
            <%}else if (((String)session.getAttribute("resulto")).equals("L")){%>
            
            <h3>Juego del Ahorcado</h3>
            <h2>Has perdido! La palabra era  <%=session.getAttribute("palabra")%></h2>
            <form action="Init" method="post">
               Nivel de Dificultad:  <br>
                <input type="radio" name="difficultad" id="bajo" value="Bajo">
                <label for="bajo">Bajo</label><br>
                <input type="radio" name="difficultad" id="medio" value="Medio" checked>
                <label for="medio">Medio</label><br>
                <input type="radio" name="difficultad" id="alto" value="Alto">
                <label for="alto">Alto</label><br>
                Número de intentos: <input type="text" name="intentos" value="10"><br><br>
                <input type="submit">
            </form>
            <h2>Número de partidas jugadas = <%=session.getAttribute("juegoNum")%></h2>
            <%}%>
        </div>
    </body>
</html>
