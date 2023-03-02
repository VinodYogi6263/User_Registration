package com.ss.email;

import java.io.File;
import java.time.LocalDateTime;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Email_Send {
	
	
	@Autowired
	private  JavaMailSender javaMailSender;
	
	private  Logger log=LoggerFactory.getLogger(Email_Send.class);

	
	public  void send(String to_Email,String Body ) {
		String path ="D://SS/test.jpg";
		try 
		{
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setFrom("rajguruv737@gmail.com");
			
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_Email));
			
			mimeMessageHelper.setText(Body);
			
			mimeMessageHelper.setSubject("FORGOT YOUR PASSWORD");
			
			FileSystemResource fileSystemResource = new FileSystemResource(new File(path));
			
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			
			javaMailSender.send(mimeMessage);
		
			log.info("SEND EMAIL SUCCESSFULLY");
			
		} 
		catch (MessagingException e)
		{

			log.error(e.getMessage());
		}
	}

}
