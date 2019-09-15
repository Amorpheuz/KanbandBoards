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
import kanbandboards.dao.BoardColumnDao;
import kanbandboards.entity.BoardColumn;
import kanbandboards.entity.Card;
import kanbandboards.util.DbConnection;

/**
 *
 * @author Yash
 */
public class BoardColumnDaoImpl implements BoardColumnDao{

    @Override
    public boolean addBoardColumn(BoardColumn bCol) {
        Connection conn = DbConnection.getConnection();
        String query = "insert into board_cols(col_name,col_constraints) values(?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, bCol.getColName());
            preparedStatement.setString(2, bCol.getColConstraints());
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
    public ArrayList<BoardColumn> getBoardColumns() {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from board_cols";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet bcols = preparedStatement.executeQuery();
            ArrayList<BoardColumn> boardCols = new ArrayList<>();
            while (bcols.next()){
                String subQuery = "Select * from cards where col_id = ?";
                PreparedStatement preparedStatementSub = connection.prepareStatement(query);
                preparedStatementSub.setInt(1,bcols.getInt(1));
                ResultSet crds = preparedStatementSub.executeQuery();
                ArrayList<Card> cards = new ArrayList<>();
                while(crds.next()){
                    cards.add(new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getObject(4, LocalDateTime.class)));
                }
                boardCols.add(new BoardColumn(bcols.getInt(1),bcols.getString(2), bcols.getString(3),cards));
            }
            if (boardCols.size() > 0){
                return boardCols;
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
    public BoardColumn getBoardColumn(int colId) {
        Connection connection = DbConnection.getConnection();
        try {
            String query = "Select * from board_cols where col_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,colId);
            ResultSet bcols = preparedStatement.executeQuery();
            if (bcols.next()){
                String subQuery = "Select * from cards where col_id = ?";
                PreparedStatement preparedStatementSub = connection.prepareStatement(query);
                preparedStatementSub.setInt(1,bcols.getInt(1));
                ResultSet crds = preparedStatementSub.executeQuery();
                ArrayList<Card> cards = new ArrayList<>();
                while(crds.next()){
                    cards.add(new Card(crds.getInt(1),crds.getInt(2),crds.getInt(3),crds.getInt(4),crds.getString(5),crds.getObject(4, LocalDateTime.class)));
                }
                BoardColumn boardCol = new BoardColumn(bcols.getInt(1),bcols.getString(2), bcols.getString(3),cards);
                return boardCol;
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
    public boolean updateBoardColumn(BoardColumn bCol) {
        Connection connection = DbConnection.getConnection();
        String query = "update board_cols set col_name = ?, col_constraints = ? where col_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bCol.getColName());
            preparedStatement.setString(2, bCol.getColConstraints());
            preparedStatement.setInt(3, bCol.getColId());
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
    public boolean delBoardColumn(int colId) {
        Connection connection = DbConnection.getConnection();
        String query = "delete from board_cols where col_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, colId);
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
