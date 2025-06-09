/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupprojekoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/*
 * SYAHIRAN 2418369
 */
public class MosqueStaff extends User {
    private String passwordHash;

    public MosqueStaff(String userId,String name, String password) {
        super(userId, name);//call user constructor
        
        this.userId = userId;
        this.name = name;
        this.passwordHash = password;
    }

    // Getter
    public String getPasswordHash() {
        return passwordHash;
    }

    // Setter
    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }
    
    //convert staff data to string format to save 
    public String toFileString() {
        return getUserId() + "|" + getName() + "|" + getPasswordHash();
    }
    
    // create moquestaff object from string
    public static MosqueStaff fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        return new MosqueStaff(parts[0], parts[1], parts[2]);
    }
}
