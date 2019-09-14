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
public class User {
    private int userId;
    private String userName;
    private UserTypes userType;
    private String userPass;
    private ArrayList<Card> cards;

    public User() {
    }

    public User(int userId, String userName, UserTypes userType, String userPass) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userPass = userPass;
    }

    public User(int userId, String userName, UserTypes userType, String userPass, ArrayList<Card> cards) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userPass = userPass;
        this.cards = cards;
    }
    
    

    public int getUserId() {
        return userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public String getUserPass() {
        return userPass;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", userType=" + userType + ", userPass=" + userPass + '}';
    }       

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.userId;
        hash = 97 * hash + Objects.hashCode(this.userName);
        hash = 97 * hash + Objects.hashCode(this.userType);
        hash = 97 * hash + Objects.hashCode(this.userPass);
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
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userPass, other.userPass)) {
            return false;
        }
        if (this.userType != other.userType) {
            return false;
        }
        return true;
    }    
}
