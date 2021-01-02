package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;

    private Session session;
    private String email, subject, message,message1;
    String symptoms, name,  gender, blood, previousdisease;
    int age, height, weight;
    String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());



    public JavaMailAPI(Context context, String email, String subject, String message, String symptoms, String name, int age,String gender, int height, int weight, String blood, String previousdisease) {



        this.context = context;
        this.email = email;
        this.subject = subject;
        this.symptoms = symptoms;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.blood = blood;
        this.previousdisease = previousdisease;
        this.message = message;
        this.message1 = "<div style=\"   height : 750px; width: 770px; margin: 0; background-color: rgb(141, 141, 204); border-style: solid; padding: 20px; border-width: 2px; border-color: black;\">";
        this.message1 += "<div style = \" position: absolute; bottom: 60px; left: 200px;\">";
        this.message1 += "<pre>";
        this.message1 += "<header style=\"display: flex; align-items: center; border-bottom: 1px solid #000; padding: 5px\";>";
        this.message1 += "<span style=\"float: left;\"><h2>MEDICAL REPORT</h2></span><span>                             Date : " + currentDateTimeString + "</span>\n";
        this.message1 += "</header>";
        this.message1 += "<p>";
        this.message1 += "<p><b>Name : </b>" + name + "<b>             Age : </b>" + age + "</p><p><b>Gender : </b>" + gender + "<b>      Height : </b>" + height + "<b>      Weight : </b>" + weight + "</p><p><b>Blood group : </b>" + blood + "<b>         Medical history : </b>" + previousdisease + "</p>\n";
        this.message1 += "<p><b>Symptoms experiencing : </b>" + symptoms + "</p>\n";
        this.message1 += "<p><b>Possible condition/disease : </b>" + message + "</p><br/>\n";
        this.message1 += "<p>";
        this.message1 += "</pre>";
        this.message1 += "</div>";
        this.message1 += "</div>";
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Utils.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(subject);
            //mimeMessage.setText(message);
            mimeMessage.setContent(message1, "text/html");
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;


    }
}