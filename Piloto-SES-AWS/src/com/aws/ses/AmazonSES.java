package com.aws.ses;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class AmazonSES {

    static final String FROM = "alex@ellosconnecti.com";   
    static final String TO = "alex@ellosconnecti.com";  
                                                       
    
    static final String BODY = "Email de teste.";
    static final String SUBJECT = "Cliente";
    
    
    static final String SMTP_USERNAME = "";  
    static final String SMTP_PASSWORD = "";
    
    
    static final String HOST = "email-smtp.us-west-2.amazonaws.com";    
    static final int PORT = 587;

    public static void main(String[] args) throws Exception {

        // Configurações de conexão
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");

   
    	Session session = Session.getDefaultInstance(props);

        // Montando a mensagem
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/plain");
            
        // Criando o transporte para conexão     
        Transport transport = session.getTransport();
                    
        
        try
        {
            System.out.println("Conectando ao SES SMTP para tentativa de email...");
            
            // Conexão com o SES SMTP
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Enviando o email
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email enviado!");
        }
        catch (Exception ex) {
            System.out.println("O email não foi enviado.");
            System.out.println("Mensagem de erro: " + ex.getMessage());
        }
        finally
        {
            // Finalizando conexão
            transport.close();        	
        }
    }
}