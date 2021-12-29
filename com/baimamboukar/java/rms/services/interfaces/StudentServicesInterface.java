package com.baimamboukar.java.rms.services.interfaces;

public interface StudentServicesInterface {
    public void register();

    public void viewGrades();

    public void updateProfile();

    public void sendReport(String title, String motif, String message);
}
