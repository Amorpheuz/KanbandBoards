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
public class CardType {
    private int cardTypeId;
    private String cardTypeName;
    private String cardTypeColor;
    
    private ArrayList<Card> cards;

    public CardType() {
    }

    public CardType(int cardTypeId, String cardTypeName, String cardTypeColor) {
        this.cardTypeId = cardTypeId;
        this.cardTypeName = cardTypeName;
        this.cardTypeColor = cardTypeColor;
    }

    public CardType(int cardTypeId, String cardTypeName, String cardTypeColor, ArrayList<Card> cards) {
        this.cardTypeId = cardTypeId;
        this.cardTypeName = cardTypeName;
        this.cardTypeColor = cardTypeColor;
        this.cards = cards;
    }
    
    public int getCardTypeId() {
        return cardTypeId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public String getCardTypeColor() {
        return cardTypeColor;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public void setCardTypeColor(String cardTypeColor) {
        this.cardTypeColor = cardTypeColor;
    }

    @Override
    public String toString() {
        return "CardType{" + "cardTypeId=" + cardTypeId + ", cardTypeName=" + cardTypeName + ", cardTypeColor=" + cardTypeColor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.cardTypeId;
        hash = 23 * hash + Objects.hashCode(this.cardTypeName);
        hash = 23 * hash + Objects.hashCode(this.cardTypeColor);
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
        final CardType other = (CardType) obj;
        if (this.cardTypeId != other.cardTypeId) {
            return false;
        }
        if (!Objects.equals(this.cardTypeName, other.cardTypeName)) {
            return false;
        }
        if (!Objects.equals(this.cardTypeColor, other.cardTypeColor)) {
            return false;
        }
        return true;
    }
    
    
}
