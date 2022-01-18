package com.gov.student.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.gov.student.dto.MailVO;
import com.gov.student.service.EmailService;

import lombok.Value;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender mailSender;

	/*
	 * @Value("${document.upload.guideline.path}") public String
	 * guideLinedocumentUploadPath;
	 */
	public void sendEmail(MailVO emailvo)  {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(emailvo.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(emailvo.getMailFrom(), "KCS"));
			mimeMessageHelper.setTo(emailvo.getMailTo());
			mimeMessageHelper.setText(emailvo.getMailContent());
			
			File fileName = new File(emailvo.getAttachments().getOriginalFilename());
			System.out.println("orignial fileName::->"+fileName);
			
			//String path = fileName.getAbsolutePath();
			
			//String replacePath = fileName.getAbsolutePath();
			
			//System.out.println("orignial path"+path);
			
			  
			//replacePath = replacePath.replace("\\","\\\\");
			
	       // System.out.println("new Replaced path"+replacePath);
	            
	         // File file=new File(path);

			//FileSystemResource file = new FileSystemResource(new File("D:\\Tejas\\KT document\\AFC Customer.docx"));
			//mimeMessageHelper.addAttachment("AFC Customer.docx",file);
			FileSystemResource file = new FileSystemResource(new File("D:\\Tejas\\KT document"+"\\"+emailvo.getAttachments().getOriginalFilename()));
			
			
			mimeMessageHelper.addAttachment(emailvo.getAttachments().getOriginalFilename(),file);
			
			
			mailSender.send(mimeMessageHelper.getMimeMessage());
			
			/*
			 * String currentPath = System.getProperty("user.dir");
			 * System.out.println("Current path is:: " + currentPath);
			 */
			
			/*Path currentDirectoryPath = Paths.get("").toAbsolutePath();
			String currentPath = currentDirectoryPath.toString();
			System.out.println("Current directory path:: " + currentPath);*/
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
