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

public class FileDataPersistenceService {
    //private final String scheduleFilePath;
    private final String staffFilePath;

    public FileDataPersistenceService( String staffFilePath) {
        //this.scheduleFilePath = scheduleFilePath;
        this.staffFilePath = staffFilePath;
    }
    
    //read staff detail from textfile user.txt
    public ArrayList<MosqueStaff> readStaffAccounts() {
        ArrayList<MosqueStaff> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(staffFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                staffList.add(MosqueStaff.fromFileString(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading staff accounts: " + e.getMessage());
        }
        return staffList;
    }
    
    //save mosque staff to textfile staff.txt
    public void saveStaffAccounts(ArrayList<MosqueStaff> staffList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(staffFilePath))) {
            for (MosqueStaff staff : staffList) {
                writer.write(staff.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving staff accounts: " + e.getMessage());
        }
    }
}
