/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupprojekoop;

/**
 *
 * @author User
 */

// Muhammad Nur Adam Fitri bin Ali (2417235)

public class User {
    private String userId;
    private String name;

    // constructor
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
