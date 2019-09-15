/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.dao;

import java.util.ArrayList;
import kanbandboards.entity.BoardColumn;

/**
 *
 * @author Yash
 */
public interface BoardColumnDao {
    public boolean addBoardColumn(BoardColumn bCol);
    public ArrayList<BoardColumn> getBoardColumns();
    public BoardColumn getBoardColumn(int colId);
    public boolean updateBoardColumn(BoardColumn bCol);
    public boolean delBoardColumn(int colId);
}
