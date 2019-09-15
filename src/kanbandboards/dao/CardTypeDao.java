/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.dao;

import java.util.ArrayList;
import kanbandboards.entity.CardType;

/**
 *
 * @author Yash
 */
public interface CardTypeDao {
    public boolean addCardType(CardType cType);
    public ArrayList<CardType> getCardTypes();
    public CardType getCardType(int cardTypeId);
    public boolean updateCardType(CardType cType);
    public boolean delCardType(int cardTypeId);
}
