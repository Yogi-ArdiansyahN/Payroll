/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author MrY
 */
public class conn {
    public Connection connection;
    public conn(){
        try{
            String url = "jdbc:mysql://localhost/payroll";
            String user = "root";
            String pass = "";
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Tidak Terkoneksi Database");
        } catch (Exception e){
            
        }
    }
//    public Connection connect(){
//        throw new UnsurpportedOperationException("Not Supported yet.");
//    }
}
