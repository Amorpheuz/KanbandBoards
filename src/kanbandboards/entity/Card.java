/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Yash
 */
public class Card {
    private int cardId;
    private int userId;
    private int colId;
    
    private int cTypeId;
    private String cardTitle;
    private LocalDateTime cardCreated;
    
    private CardType cardType;
    private User user;
    private BoardColumn boardColumn;

    public Card() {
    }

    public Card(int cardId,int userId, int colId, int cTypeId, String cardTitle, LocalDateTime cardCreated) {
        this.cardId = cardId;
        this.userId = userId;
        this.colId = colId;
        this.cTypeId = cTypeId;
        this.cardTitle = cardTitle;
        this.cardCreated = cardCreated;
    }

    public Card(int cardId, int userId, int colId, int cTypeId, String cardTitle, LocalDateTime cardCreated, CardType cardType, User user, BoardColumn boardColumn) {
        this.cardId = cardId;
        this.userId = userId;
        this.colId = colId;
        this.cTypeId = cTypeId;
        this.cardTitle = cardTitle;
        this.cardCreated = cardCreated;
        this.cardType = cardType;
        this.user = user;
        this.boardColumn = boardColumn;
    }

    public int getCardId() {
        return cardId;
    }

    public int getUserId() {
        return userId;
    }

    public int getColId() {
        return colId;
    }

    public int getcTypeId() {
        return cTypeId;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public LocalDateTime getCardCreated() {
        return cardCreated;
    }

    public CardType getCardType() {
        return cardType;
    }

    public User getUser() {
        return user;
    }

    public BoardColumn getBoardColumn() {
        return boardColumn;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public void setcTypeId(int cTypeId) {
        this.cTypeId = cTypeId;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public void setCardCreated(LocalDateTime cardCreated) {
        this.cardCreated = cardCreated;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBoardColumn(BoardColumn boardColumn) {
        this.boardColumn = boardColumn;
    }

    @Override
    public String toString() {
        return "Card{" + "cardId=" + cardId + ", userId=" + userId + ", colId=" + colId + ", cTypeId=" + cTypeId + ", cardTitle=" + cardTitle + ", cardCreated=" + cardCreated + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.cardId;
        hash = 71 * hash + this.userId;
        hash = 71 * hash + this.colId;
        hash = 71 * hash + this.cTypeId;
        hash = 71 * hash + Objects.hashCode(this.cardTitle);
        hash = 71 * hash + Objects.hashCode(this.cardCreated);
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
        final Card other = (Card) obj;
        if (this.cardId != other.cardId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (this.colId != other.colId) {
            return false;
        }
        if (this.cTypeId != other.cTypeId) {
            return false;
        }
        if (!Objects.equals(this.cardTitle, other.cardTitle)) {
            return false;
        }
        if (!Objects.equals(this.cardCreated, other.cardCreated)) {
            return false;
        }
        return true;
    }
}
