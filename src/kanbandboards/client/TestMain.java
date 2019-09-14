/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kanbandboards.util.DbConnection;

/**
 *
 * @author Yash
 */
public class TestMain {
    public static void main(String[] args) {
        Connection conn = DbConnection.getConnection();
        System.out.println(conn);
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
