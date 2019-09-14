/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.entity;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Yash
 */
public class BoardColumn {
    private int colId;
    private String colName;
    private String colConstraints;
    
    private ArrayList<Card> cards;

    public BoardColumn() {
    }

    public BoardColumn(int colId, String colName, String colConstraints) {
        this.colId = colId;
        this.colName = colName;
        this.colConstraints = colConstraints;
    }

    public BoardColumn(int colId, String colName, String colConstraints, ArrayList<Card> cards) {
        this.colId = colId;
        this.colName = colName;
        this.colConstraints = colConstraints;
        this.cards = cards;
    }

    public int getColId() {
        return colId;
    }

    public String getColName() {
        return colName;
    }

    public String getColConstraints() {
        return colConstraints;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public void setColConstraints(String colConstraints) {
        this.colConstraints = colConstraints;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "BoardColumn{" + "colId=" + colId + ", colName=" + colName + ", colConstraints=" + colConstraints + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.colId;
        hash = 79 * hash + Objects.hashCode(this.colName);
        hash = 79 * hash + Objects.hashCode(this.colConstraints);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardColumn other = (BoardColumn) obj;
        if (this.colId != other.colId) {
            return false;
        }
        if (!Objects.equals(this.colName, other.colName)) {
            return false;
        }
        if (!Objects.equals(this.colConstraints, other.colConstraints)) {
            return false;
        }
        return true;
    }
    
        
}
