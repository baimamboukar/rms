// package com.baimamboukar.java.rms.src.models;

// import com.sendpulse.restapi.Sendpulse;

// public class Sendgrid {
// public static void main(String[] args) {
// Sendpulse sendpulse = new Sendpulse("6ab9a5c995945e2f6cfa618c1721b3bf",
// "6ce55b9f94967975d638812801954a08");
// Map<String, Object> from = new HashMap<String, Object>();
// from.put("name", "Your Sender Name");
// from.put("email", "your-sender-email@gmail.com");
// ArrayList<Map> to = new ArrayList<Map>();
// Map<String, Object> elementto = new HashMap<String, Object>();
// elementto.put("name", "Subscriber's name");
// elementto.put("email", "subscriber@gmail.com");
// to.add(elementto);
// Map<String, Object> emaildata = new HashMap<String, Object>();
// emaildata.put("html","Your email content goes here");
// emaildata.put("text","Your email text version goes here");
// emaildata.put("subject","Testing SendPulse API");
// emaildata.put("from",from);
// emaildata.put("to",to);
// Map<String, Object> result = (Map<String, Object>)
// sendpulse.smtpSendMail(emaildata);
// System.out.println("Result: " + result);
// }
