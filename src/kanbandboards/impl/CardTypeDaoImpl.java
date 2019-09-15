/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import kanbandboards.dao.CardTypeDao;
import kanbandboards.entity.Card;
import kanbandboards.entity.CardType;
import kanbandboards.util.DbConnection;

/**
 *
 * @author Yash
 */
public class CardTypeDaoImpl implements CardTypeDao {

    @Override
    public boolean addCardType(CardType cType) {
        Connection conn = DbConnection.getConnection();
        String query = "insert into card_types(ctype_name,ctype_color) values(?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, cType.getCardTypeName());
            preparedStatement.setString(2, cType.getCardTypeColor());
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
    public ArrayList<CardType> getCardTypes() {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from card_types";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet cTyps = preparedStatement.executeQuery();
            ArrayList<CardType> cTypes = new ArrayList<>();
            while (cTyps.next()){
                String subQuery = "Select * from cards where ctype_id = ?";
                PreparedStatement preparedStatementSub = connection.prepareStatement(query);
                preparedStatementSub.setInt(1,cTyps.getInt(1));
                ResultSet crds = preparedStatementSub.executeQuery();
                ArrayList<Card> cards = new ArrayList<>();
                while(crds.next()){
                    cards.add(new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getObject(4, LocalDateTime.class)));
                }
                cTypes.add(new CardType(cTyps.getInt(1),cTyps.getString(2), cTyps.getString(3),cards));
            }
            if (cTypes.size() > 0){
                return cTypes;
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
    public CardType getCardType(int cardTypeId) {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from card_types where ctype_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,cardTypeId);
            ResultSet cTyps = preparedStatement.executeQuery();
            if (cTyps.next()){
                String subQuery = "Select * from cards where ctype_id = ?";
                PreparedStatement preparedStatementSub = connection.prepareStatement(query);
                preparedStatementSub.setInt(1,cTyps.getInt(1));
                ResultSet crds = preparedStatementSub.executeQuery();
                ArrayList<Card> cards = new ArrayList<>();
                while(crds.next()){
                    cards.add(new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getObject(4, LocalDateTime.class)));
                }
                CardType cType = new CardType(cTyps.getInt(1),cTyps.getString(2), cTyps.getString(3),cards);
                return cType;
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
    public boolean updateCardType(CardType cType) {
        Connection connection = DbConnection.getConnection();
        String query = "update card_types set ctype_name = ?, ctype_color = ? where ctype_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cType.getCardTypeName());
            preparedStatement.setString(2, cType.getCardTypeColor());
            preparedStatement.setInt(3, cType.getCardTypeId());
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
    public boolean delCardType(int cardTypeId) {
        Connection connection = DbConnection.getConnection();
        String query = "delete from card_types where ctype_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cardTypeId);
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
    
}
