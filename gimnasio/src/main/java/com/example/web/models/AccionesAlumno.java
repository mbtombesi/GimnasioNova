package com.example.web.models;
import com.example.web.models.Alumno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccionesAlumno {

    public static int registrarAlumno(Alumno a){
        int estado = 0;

        try {
            /*necesario para conectarme con la base de datos */
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gimnasio", "root", "");

            String query = "INSERT INTO alumnos (nombre, apellido, contacto) values(?,?,?)";

            

            
            String nombre = a.getNombre();
            String apellido = a.getApellido();
            String contacto = a.getContacto();

            PreparedStatement pst = conexion.prepareStatement(query);
            
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setString(3, contacto);

            estado = pst.executeUpdate();

            conexion.close(); /*cerrar conexion con la base de datos */

        } catch (Exception e){
            System.out.println(e);
            System.out.println("upss algo salio mal");

        }

        return estado;
    }

    public static int actualizarAlumno(Alumno a){
        int estado = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gimnasio", "root", "");

            String query = "UPDATE alumnos SET nombre=?,apellido=?,contacto=? WHERE id_cliente=?";

            int id = a.getId();
            String nombre = a.getNombre();
            String apellido = a.getApellido();
            String contacto = a.getContacto();

            PreparedStatement pst = conexion.prepareStatement(query);
            
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setString(3, contacto);
            pst.setInt(4, id);

            estado = pst.executeUpdate();

            conexion.close();

        } catch (Exception e){
            System.out.println("upss algo salio mal");

        }

        return estado;
    }

    //CONSULTAR REGISTRO POR ID

    public static Alumno verAlumno(int idAConsultar){
        Alumno alumnoADevolver = new Alumno();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gimnasio", "root", "");

            String query = "SELECT * FROM alumnos WHERE id_cliente=?";

            PreparedStatement pst = conexion.prepareStatement(query);
            
            pst.setInt(1, idAConsultar);

            ResultSet consultaAlumno = pst.executeQuery();

            if (consultaAlumno.next()){
                alumnoADevolver.setId (consultaAlumno.getInt(1));
                alumnoADevolver.setNombre (consultaAlumno.getString(2));
                alumnoADevolver.setApellido (consultaAlumno.getString(3));
                alumnoADevolver.setContacto (consultaAlumno.getString(4));
                conexion.close();
            }

            

        } catch (Exception e){
            System.out.println(e);
            System.out.println("upss algo salio mal");

        }

        return alumnoADevolver;
    }

    //CONSULTAR TODOS LOS REGISTROS DE UNA TABLA --hay un problema que no pude solucionar

    public static List<Alumno> verTodosAlumnos(){
        List<Alumno> listaAlumnosADevolver = new ArrayList<Alumno>();
       

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gimnasio", "root", "");

            String query = "SELECT * FROM alumnos";

            PreparedStatement pst = conexion.prepareStatement(query);

            ResultSet consultaAlumno = pst.executeQuery();

            while (consultaAlumno.next()){
                Alumno alumnoADevolver = new Alumno();
                alumnoADevolver.setId (consultaAlumno.getInt(1));
                alumnoADevolver.setNombre (consultaAlumno.getString(2));
                alumnoADevolver.setApellido (consultaAlumno.getString(3));
                alumnoADevolver.setContacto (consultaAlumno.getString(4));
                listaAlumnosADevolver.add(alumnoADevolver);
            }
            conexion.close();        

        } catch (Exception e){
            System.out.println(e);
            System.out.println("upss algo salio mal");

        }

        return listaAlumnosADevolver;
    }

    
}
