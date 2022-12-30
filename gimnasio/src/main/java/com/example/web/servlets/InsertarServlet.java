package com.example.web.servlets;

import java.io.IOException;

import com.example.web.models.AccionesAlumno;
import com.example.web.models.Alumno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nuevoalumno")
public class InsertarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String n, a, c;

        n = req.getParameter("nombre");

        a = req.getParameter("apellido");

        c = req.getParameter("contacto");

        

        Alumno nuevoAlumno = new Alumno();

        nuevoAlumno.setNombre(n);
        nuevoAlumno.setApellido(a);
        nuevoAlumno.setContacto(c);

        int estado = AccionesAlumno.registrarAlumno(nuevoAlumno);

        if (estado == 1){
            resp.sendRedirect("exito.jsp");
            System.out.println("todo ok");
        } else {
            System.out.println("Error");
            resp.sendRedirect("error.jsp");
        }



    }
}
