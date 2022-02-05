/*
 📌📌📌 THE ICT UNIVERSITY, FALL 20201 📌📌📌

 🚩 ♨♨♨♨♨ JAVA PROGRAMMING I ♨♨♨♨♨ 🚩

 🚩 INSTRUCTOR: ENGR. Daniel Moune

 📌﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋📌

 🎯 NAMES: BAIMAM BOUKAR JEAN JACQUES
 🎯 EMAIL: baimam.jeanjacque@ictuniversity.edu.cm
 🎯 WHATSAPP: (+237) 690535759
 🎯 TWITTER: @baimamjj
 🎯 LINKEDIN: linkedin.com/in/baimamboukar
 🎯 WEBSITE: www.baimamboukar.me
 */

package com.baimamboukar.java.rms.src.models;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class Twilio {

    final String subject;
    final String content;
    final File attachment;
    final List<String> receivers;

    public Twilio(String subject, String content, List<String> receivers, File attachment) {
        this.subject = subject;
        this.content = content;
        this.receivers = receivers;
        this.attachment = attachment;
        // change accordingly
        String from = "baimamboukar@gmail.com";

        // or IP address
        String host = "smtp.gmail.com";

        // mail id
        final String username = "baimamboukar@gmail.com";

        // correct password for gmail id
        final String password = "xlmimrnsitabgazm";

        // System.out.println("TLSEmail Start");
        // Get the session object

        // Get system properties//
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // SSL Port
        properties.put("mail.smtp.port", "465");

        // enable authentication
        properties.put("mail.smtp.auth", "true");

        // SSL Factory
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");

        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {

                    // override the getPasswordAuthentication
                    // method
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);

                    }
                });

        // compose the message
        try

        {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("baimamboukar@gmail.com"));
            receivers.forEach((String email) -> {
                try {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            });
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content);
            MimeBodyPart attachmentPart = new MimeBodyPart();
            try {
                attachmentPart.attachFile(attachment);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("File not found");
            }
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            Transport.send(message);
            // System.out.println("Emails delivered successfully...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
