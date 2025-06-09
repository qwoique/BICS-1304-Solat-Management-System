/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupprojekoop;

import java.util.ArrayList;
/*
 * SYAHIRAN 2418369
 */
public class SolatManagementSystem {
    private ArrayList<PrayerSchedule> prayerSchedules;
    private ArrayList<MosqueStaff> mosqueStaffList;
    private MosqueStaff currentUser;
    private final FileDataPersistenceService persistenceService;

    public SolatManagementSystem() {
        // Initialize with file paths 
        this.persistenceService = new FileDataPersistenceService( "staff.txt");
        // Load data on startup 
        //this.prayerSchedules = persistenceService.loadSchedules();
        this.mosqueStaffList = persistenceService.readStaffAccounts();
        this.currentUser = null;
    }
    
    //to verify userId and password
    public boolean login(String userId, String password) {
        for (MosqueStaff staff : mosqueStaffList) {
            if (staff.getUserId().equals(userId) && staff.getPasswordHash().equals(password)) {
                this.currentUser = staff;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
    }
    
    public boolean registerStaff(String userId, String name, String password){
        for (MosqueStaff staff : mosqueStaffList){
            if(staff.getUserId().equals(userId)){
                return false; // mean that the userid already exist
            }
        }
        
        MosqueStaff newStaff = new MosqueStaff(userId, name, password);
        this.mosqueStaffList.add(newStaff);
        persistenceService.saveStaffAccounts(this.mosqueStaffList);
        return true;
    }

    
}
