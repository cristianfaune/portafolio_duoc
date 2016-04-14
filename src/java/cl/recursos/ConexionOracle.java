package cl.recursos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

public class ConexionOracle {

    public String driver, url, ip, bd, usr, pass;

    public Connection con;

    public ConexionOracle(String ip, String bd, String usr, String pass) {

        driver = "oracle.jdbc.driver.OracleDriver";
        this.bd = bd;
        this.usr = usr;
        this.pass = pass;
        url = new String("jdbc:oracle:thin:@" + ip + ":1521:" + bd);

        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, usr, pass);
            System.out.println("Conexion a Base de Datos " + bd + " Ok");
        } catch (Exception exc) {
            System.out.println("Error al tratar de abrir la base de Datos" + bd + " : " + exc);
        }
    }

    public Connection getConexion() {
        return con;
    }

    public Connection CerrarConexion() throws SQLException {
        con.close();
        con = null;
        return con;
    }
    
    public static void main(String [] args)
	{
		ConexionOracle con = new ConexionOracle("localhost","XE","portafolio","portafolio");
                
	}
} //fin de la clase


