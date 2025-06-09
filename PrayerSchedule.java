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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PrayerSchedule {
    
    private LocalDate date;
    private String prayerTime;
    private String role;
    private String name;
    
    public PrayerSchedule(LocalDate date, String prayerTime, String role, String name){
        this.date = date;
        this.prayerTime = prayerTime;
        this.role = role;
        this.name = name;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }
    
    public String getPrayerTime(){
        return prayerTime;
    }
    
    public void setPrayerTime(String prayerTime){
        this.prayerTime = prayerTime;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String toFileString(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(format);
        return dateString + "," + prayerTime + "," + role + "," + name;
    }
    
    //write to file   
    public void writeToFile(String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){ //the true enable append mode
            writer.write( date + "," + prayerTime + "," + role + "," + name +"\n");
            writer.newLine();//add new line for each order
            System.out.println("Schedule added to "+ filename);
        }
        catch(IOException ioe){
            System.err.println("Error writing to file: " + ioe.getMessage());//for error use System.err
        }
    }
    
    //to rewrite after delete
    public static void writeAllSchedules(String filename, ArrayList<PrayerSchedule> list) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) { // overwrite mode
        for (PrayerSchedule s : list) {
            writer.write(s.getDate() + "," + s.getPrayerTime() + "," + s.getRole() + "," + s.getName());
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println(e.getMessage());
    }
}
    
    public static ArrayList<PrayerSchedule> readFromFile(String filename){
        ArrayList<PrayerSchedule> prayerSchedulesList = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            
            while((line = reader.readLine()) != null){
                String[] parts = line.split(","); // split and put in parts
                
                if(parts.length == 4){
                    LocalDate date = LocalDate.parse(parts[0]);
                    String prayerTime = parts[1];
                    String role = parts[2];
                    String name = parts[3];
                    
                    PrayerSchedule schedule = new PrayerSchedule(date, prayerTime, role, name);
                    prayerSchedulesList.add(schedule);
                }
            }
            
            
        }
        catch(IOException ioe){
            System.err.println("Error reading from this file: " + ioe.getMessage());
        }
        return prayerSchedulesList;
    }
    
}
