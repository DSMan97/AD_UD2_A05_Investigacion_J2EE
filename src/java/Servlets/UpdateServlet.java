/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modelo.Peliculas;
import Session.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guillermo Crespo, Javier Borreguero y Miguel Martinez
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    
    @EJB
    Session aEJB;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("nombre") == null) {

                /* TODO output your page here. You may use following sample code. */

                List<Peliculas> l = aEJB.allPeliculas();
                /* bucle para recorrer la lista que corresponda */
                out.println("<select name='id'>");
                for (int i = 0; i < l.size(); i++) {
                    out.println("<option value=" + l.get(i).getCodigo() + ">" + l.get(i).getTitulo()+ "<br>");

                }
                out.println("</select><br>");
                out.println("Nombre:<br>\n" +
                            "<input type='text' name='titulo'/><br>\n" +
                            "</br>Fecha de lanzamiento:<br>\n" +
                            "<input type='text' name='fecha'/><br>\n" +
                            "</br>Presupuesto:<br>\n" +
                            "<input type='text' name='presupuesto'/><br><br>");
                out.println("</form>");
                out.println("</body>");
                out.println("</head>");
                out.println("</html>");
            } else {
                String nombre = request.getParameter("titulo");
                String fechalanzamiento = request.getParameter("fecha");
                SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
                Date fecha = null;
                try {
                    fecha = date.parse(fechalanzamiento);
                } catch (ParseException ex) {
                    Logger.getLogger(InsertServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               String presupuesto = request.getParameter("presupuesto");
                double mPresupuesto = Double.parseDouble(presupuesto);
               
                Peliculas pelis = new Peliculas();
                pelis.setTitulo(nombre);
                pelis.setFecha(fecha);
                pelis.setPresupuesto(mPresupuesto);
                aEJB.updatePelicula(pelis);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet InsertServlet</title>");
                out.println("<script>window.location='index.jsp'</script>");
                out.println("<body>");
                out.println("</body>");
                out.println("</head>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
