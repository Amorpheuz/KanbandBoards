/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import kanbandboards.dao.CardDao;
import kanbandboards.entity.BoardColumn;
import kanbandboards.entity.Card;
import kanbandboards.entity.CardType;
import kanbandboards.entity.User;
import kanbandboards.entity.UserTypes;
import kanbandboards.util.DbConnection;

/**
 *
 * @author Yash
 */
public class CardDaoImpl implements CardDao {

    @Override
    public boolean addCard(Card card) {
        Connection conn = DbConnection.getConnection();
        String query = "insert into cards(user_id, col_id, ctype_id, card_title, card_created) values(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, card.getUserId());
            preparedStatement.setInt(2, card.getColId());
            preparedStatement.setInt(3, card.getcTypeId());
            preparedStatement.setString(4, card.getCardTitle());
            preparedStatement.setDate(5, Date.valueOf(card.getCardCreated().toLocalDate()));
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
    public ArrayList<Card> getCards() {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from cards";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet crds = preparedStatement.executeQuery();
            ArrayList<Card> cards = new ArrayList<>();
            while (crds.next()){
                String subQuery1 = "Select * from users where user_id = ?";
                PreparedStatement preparedStatementSub1 = connection.prepareStatement(subQuery1);
                preparedStatementSub1.setInt(1,crds.getInt(2));
                ResultSet usr = preparedStatementSub1.executeQuery();
                User user = null;
                if (usr.next()) {
                    user = new User(usr.getInt(1),usr.getString(2),UserTypes.values()[usr.getInt(3)],usr.getString(4));
                }
                String subQuery2 = "Select * from board_cols where col_id = ?";
                PreparedStatement preparedStatementSub2 = connection.prepareStatement(subQuery2);
                preparedStatementSub2.setInt(1,crds.getInt(3));
                ResultSet bCol = preparedStatementSub2.executeQuery();
                BoardColumn bColumn = null;
                if (bCol.next()) {
                    bColumn = new BoardColumn(bCol.getInt(1),bCol.getString(2),bCol.getString(3));
                }
                String subQuery3 = "Select * from card_types where ctype_id = ?";
                PreparedStatement preparedStatementSub3 = connection.prepareStatement(subQuery3);
                preparedStatementSub3.setInt(1,crds.getInt(4));
                ResultSet cTyp = preparedStatementSub3.executeQuery();
                CardType cType = null;
                if (cTyp.next()) {
                    cType = new CardType(cTyp.getInt(1),cTyp.getString(2),cTyp.getString(3));
                }
                cards.add(new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getDate(6).toLocalDate().atStartOfDay(),cType,user,bColumn));
            }
            if (cards.size() > 0){
                return cards;
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
    public Card getCard(int cardId) {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from Cards where card_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,cardId);
            ResultSet crds = preparedStatement.executeQuery();
            if (crds.next()){
                String subQuery1 = "Select * from users where user_id = ?";
                PreparedStatement preparedStatementSub1 = connection.prepareStatement(subQuery1);
                preparedStatementSub1.setInt(1,crds.getInt(2));
                ResultSet usr = preparedStatementSub1.executeQuery();
                User user = new User(usr.getInt(1),usr.getString(2),UserTypes.values()[usr.getInt(3)],usr.getString(4));
                String subQuery2 = "Select * from board_cols where col_id = ?";
                PreparedStatement preparedStatementSub2 = connection.prepareStatement(subQuery2);
                preparedStatementSub2.setInt(1,crds.getInt(3));
                ResultSet bCol = preparedStatementSub2.executeQuery();
                BoardColumn bColumn = new BoardColumn(bCol.getInt(1),bCol.getString(2),bCol.getString(3));
                String subQuery3 = "Select * from card_types where ctype_id = ?";
                PreparedStatement preparedStatementSub3 = connection.prepareStatement(subQuery3);
                preparedStatementSub3.setInt(1,crds.getInt(4));
                ResultSet cTyp = preparedStatementSub3.executeQuery();
                CardType cType = new CardType(cTyp.getInt(1),cTyp.getString(2),cTyp.getString(3));
                Card card = new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getObject(4, LocalDateTime.class),cType,user,bColumn);
                return card;
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
    public boolean updateCard(Card card) {
        Connection connection = DbConnection.getConnection();
        String query = "update cards set user_id = ?, col_id = ?, ctype_id = ?, card_title = ?, card_created = ? where card_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, card.getUserId());
            preparedStatement.setInt(2, card.getColId());
            preparedStatement.setInt(3, card.getcTypeId());
            preparedStatement.setString(4, card.getCardTitle());
            preparedStatement.setDate(5, Date.valueOf(card.getCardCreated().toLocalDate()));
            preparedStatement.setInt(6, card.getCardId());
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
    public boolean delCard(int cardId) {
        Connection connection = DbConnection.getConnection();
        String query = "delete from cards where card_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cardId);
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
