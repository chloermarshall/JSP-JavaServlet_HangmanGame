package hangman;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Game", urlPatterns = {"/Game"})
public class jugada extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession miSesion = request.getSession();
        
        int intentosMax = (int)miSesion.getAttribute("intentosMax");
        int intentos = (int)miSesion.getAttribute("intentos");
        String palabra  = (String)miSesion.getAttribute("palabra");
        String escondidoStr = (String)miSesion.getAttribute("escondidoStr");
        String intentosStr = request.getParameter("intentosStr");
        String resulto = "N";
        
        intentos++;

        escondidoStr = checkStr(palabra, escondidoStr, intentosStr);
        
        
        if(intentos == intentosMax+1){
            resulto = "L";
        }
        
        if(escondidoStr.equals(palabra)){
            //win
            resulto = "W";
        }        
        
        miSesion.setAttribute("resulto", resulto);
        miSesion.setAttribute("intentos", intentos);
        miSesion.setAttribute("escondidoStr", escondidoStr);
        response.sendRedirect("miJuego.jsp");
    }

    private String checkStr(String palabra, String escondido, String str){
        if(str.equals("")){
            return escondido;  
        }
        
        if(str.length()>1){
            if(str.toLowerCase().equals(palabra.toLowerCase())){
                return palabra;  
            }
        }
              
        char[] res = escondido.toLowerCase().toCharArray();
        char c = str.charAt(0);
        for(int i = 0, l = palabra.length(); i < l; i++){
            if(palabra.toLowerCase().charAt(i) == c){
                res[i] = c;
            }
        }
        res[0] = Character.toUpperCase(res[0]);
        return new String(res);  // next attempt
    }
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Nada";
    }// </editor-fold>

}
