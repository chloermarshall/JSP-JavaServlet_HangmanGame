package hangman;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Init", urlPatterns = {"/Init"})
public class inicio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Inicio variables
        HttpSession miSesion = request.getSession();
        int diffLength;
        int juegoNum;
        int intentosMax = 0;
        int intentos = 1;
        int diff = 1;
        String palabra = "";
        String escondidoStr = "";
        String[] palabrasList = new String[]{
            "Hat","Happiness", "Paleontologist"
          
        };
        diffLength = palabrasList.length/3;
        
        // assign variables
        try {
            intentosMax = Integer.parseInt(request.getParameter("intentos"));
            if(intentosMax < 1){
                intentosMax = 10;
            }
        } catch (Exception e) {
            intentosMax = 10;
        }
        switch(request.getParameter("difficultad")){
            case "Bajo":
                diff = 0;   // 3-6
                break;
            case "Medio":
                diff = 1;   // 7-10
                break;
            case "Alto":
                diff = 2;   // 11-14
                break;
        }
       
        
        palabra = palabrasList[new Random().nextInt(diffLength) * 3 + diff];
                
        for(int i = 0; i < palabra.length(); i++){
                escondidoStr += "*";
            }
        
        if(miSesion.getAttribute("juegoNum") == null || miSesion.getAttribute("juegoNum").equals("")){
            juegoNum = 1;
        }else{
            juegoNum = (int)miSesion.getAttribute("juegoNum");
            juegoNum++;
        }
        
        // save variables
        miSesion.setAttribute("escondidoStr", escondidoStr);
        miSesion.setAttribute("palabra", palabra);
        miSesion.setAttribute("intentos", intentos);
        miSesion.setAttribute("intentosMax", intentosMax);
        miSesion.setAttribute("juegoNum", juegoNum);
        miSesion.setAttribute("resulto", "N");
        response.sendRedirect("miJuego.jsp");
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
    }

}
