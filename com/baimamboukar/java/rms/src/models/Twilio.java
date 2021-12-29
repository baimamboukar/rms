package com.baimamboukar.java.rms.src.models;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

public class Twilio {
    private String clientID;
    private String authID;
    private String senderEmail;
    private String senderPhone;

    public Twilio(String clientID, String authID, String senderEmail, String senderPhone) {
        this.clientID = clientID;
        this.authID = authID;
        this.senderEmail = senderEmail;
        this.senderPhone = senderPhone;
    }

    public void send(String from, String to, String message) {
        System.out.println("Sending message...");
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getAuthID() {
        return authID;
    }

    public void setAuthID(String authID) {
        this.authID = authID;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    @Override
    public String toString() {
        return "Twilio [authID=" + authID + ", clientID=" + clientID + ", senderEmail=" + senderEmail + ", senderPhone="
                + senderPhone + "]";
    }

    public static void sendMail() {
        // email ID of Recipient.
        String recipient = "baimamboukar@gmail.com";

        // email ID of Sender.
        String sender = "bsquare.j2@gmail.com";

        // using host as localhost
        String host = "127.0.0.1";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        try {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("This is Subject");

            // set body of the email.
            message.setText("This is a test mail");

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
