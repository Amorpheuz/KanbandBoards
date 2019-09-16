/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import kanbandboards.dao.UserDao;
import kanbandboards.entity.Card;
import kanbandboards.entity.User;
import kanbandboards.entity.UserTypes;
import kanbandboards.util.DbConnection;

/**
 *
 * @author Yash
 */
public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User u) {
        Connection conn = DbConnection.getConnection();
        String query = "insert into users(user_name,user_type,user_pass) values(?,?,?)";
        String password = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(u.getUserPass().getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            password = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setInt(1, u.getUserId());
            preparedStatement.setString(1, u.getUserName());
            preparedStatement.setInt(2, u.getUserType().ordinal());
            preparedStatement.setString(3, password);
            int rec = preparedStatement.executeUpdate();
            if (rec == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public ArrayList<User> getUsers() {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet usrs = preparedStatement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (usrs.next()){
                String subQuery = "Select * from cards where user_id = ?";
                PreparedStatement preparedStatementSub = connection.prepareStatement(subQuery);
                preparedStatementSub.setInt(1,usrs.getInt(1));
                ResultSet crds = preparedStatementSub.executeQuery();
                ArrayList<Card> cards = new ArrayList<>();
                while(crds.next()){
                    cards.add(new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getDate(6).toLocalDate().atStartOfDay()));
                }
                users.add(new User(usrs.getInt(1),usrs.getString(2),UserTypes.values()[usrs.getInt(3)],usrs.getString(4),cards));
            }
            if (users.size() > 0){
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User getUser(int userId) {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from users where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet usrs = preparedStatement.executeQuery();
            if (usrs.next()){
                String subQuery = "Select * from cards where user_id = ?";
                PreparedStatement preparedStatementSub = connection.prepareStatement(subQuery);
                preparedStatementSub.setInt(1,usrs.getInt(1));
                ResultSet crds = preparedStatementSub.executeQuery();
                ArrayList<Card> cards = new ArrayList<>();
                while(crds.next()){
                    cards.add(new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getDate(6).toLocalDate().atStartOfDay()));
                }
                User user = new User(usrs.getInt(1),usrs.getString(2),UserTypes.values()[usrs.getInt(3)],usrs.getString(4),cards);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean updateUser(User u) {
//        if (!checkRecord(empid)){
//            return false;
//        }
        Connection connection = DbConnection.getConnection();
        String query = "update users set user_name = ?,user_type = ?, user_pass = ? where user_id = ?";
        String password = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(u.getUserPass().getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            password = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, u.getUserName());
            preparedStatement.setInt(3, u.getUserType().ordinal());
            preparedStatement.setString(3,password);
            preparedStatement.setInt(4, u.getUserId());

            int rec = preparedStatement.executeUpdate();
            if (rec == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delUser(int userId) {
//        if (!checkRecord(empid)){
//            return false;
//        }
        Connection connection = DbConnection.getConnection();
        String query = "delete from users where user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            int rec = preparedStatement.executeUpdate();
            if (rec == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public User getUser(String userName){
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from users where user_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            ResultSet usrs = preparedStatement.executeQuery();
            if (usrs.next()){
                User user = new User(usrs.getInt(1),usrs.getString(2),UserTypes.values()[usrs.getInt(3)],usrs.getString(4));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
