/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanbandboards.dao;

import java.util.ArrayList;
import kanbandboards.entity.User;

/**
 *
 * @author Yash
 */
public interface UserDao {
    public boolean addUser(User u);
    public ArrayList<User> getUsers();
    public User getUser(int userId);
    public boolean updateUser(User u);
    public boolean delUser(int userId);
}
