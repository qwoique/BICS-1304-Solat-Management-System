/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solatmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileDataPersistenceService {
    private final String scheduleFilePath;
    private final String staffFilePath;

    public FileDataPersistenceService(String scheduleFilePath, String staffFilePath) {
        this.scheduleFilePath = scheduleFilePath;
        this.staffFilePath = staffFilePath;
    }

    /**
     * Loads all prayer schedules from a text file. 
     * @return A list of prayer schedules.
     */
    public ArrayList<PrayerSchedule> loadSchedules() {
        ArrayList<PrayerSchedule> schedules = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(scheduleFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                schedules.add(PrayerSchedule.fromFileString(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading schedules: " + e.getMessage()); // 
        }
        return schedules;
    }

    /**
     * Saves all prayer schedules to a text file. 
     * @param schedules The list of schedules to save.
     */
    public void saveSchedules(ArrayList<PrayerSchedule> schedules) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(scheduleFilePath))) {
            for (PrayerSchedule schedule : schedules) {
                writer.write(schedule.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving schedules: " + e.getMessage()); // 
        }
    }

    /**
     * Loads all mosque staff accounts from a text file.
     * @return A list of mosque staff.
     */
    public ArrayList<MosqueStaff> loadStaffAccounts() {
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

    /**
     * Saves all mosque staff accounts to a text file.
     * @param staffList The list of staff to save.
     */
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
