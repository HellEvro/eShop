/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eShop;

import java.sql.SQLException;
import mysql.DB;
import user_interface.LoginFrame;
import user_interface.StartFrame;

/**
 *
 * @author teacher
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DB db = new DB("jdbc:mysql://localhost/eShop", "root", "");
            new LoginFrame(db);
        } catch (SQLException ex) {
            new StartFrame();
        }
//
    }
}
