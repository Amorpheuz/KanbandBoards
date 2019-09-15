/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.dao;

import java.util.ArrayList;
import kanbandboards.entity.Card;

/**
 *
 * @author Yash
 */
public interface CardDao {
    public boolean addCard(Card card);
    public ArrayList<Card> getCards();
    public Card getCard(int cardId);
    public boolean updateCard(Card card);
    public boolean delCard(int cardId);
}
