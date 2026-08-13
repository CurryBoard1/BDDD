package modelos;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Alumno {
    private int id;
    private String matricula;
    private String nombre;
    private int edad;
    private String sexo;
    private String correo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Alumno(int id, String matricula, String correo, String sexo, int edad, String nombre) {
        this.id = id;
        this.matricula = matricula;
        this.correo = correo;
        this.sexo = sexo;
        this.edad = edad;
        this.nombre = nombre;


    }
    public Alumno() {}


    // REGISTRAR ALUMNO


    public void save() {

        String sql = "INSERT INTO alumno(matricula,nombre,edad,sexo,correo) VALUES(?,?,?,?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ps.setString(2, nombre);
            ps.setInt(3, edad);
            ps.setString(4, sexo);
            ps.setString(5, correo);

            ps.executeUpdate();

            System.out.println("Alumno registrado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // BUSCAR POR MATRICULA


    public static Alumno findByMatricula(String matriculaBuscar) {

        Alumno alumno = null;

        String sql = "SELECT * FROM alumno WHERE matricula=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matriculaBuscar);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                alumno = new Alumno();

                alumno.setId(rs.getInt("id"));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setEdad(rs.getInt("edad"));
                alumno.setSexo(rs.getString("sexo"));
                alumno.setCorreo(rs.getString("correo"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumno;

    }

}

