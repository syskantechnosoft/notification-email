package com.syskan.notificationmail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EmailService {
	
	private Environment env;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	public EmailService(Environment env) {
		this.env=env;
	}

	public void sendSimpleEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		log.info(this.getClass().getName()+" Send Simple Email Start!!!");
		message.setFrom("syskantechnosoft@gmail.com"); // Replace with your Gmail
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
		log.info(this.getClass().getName()+" Send Simple Email End!!!");
	}

	public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("syskantechnosoft@gmail.com"); // Replace with your Gmail
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body);

		if (attachmentPath != null && !attachmentPath.isEmpty()) {
			FileSystemResource file = new FileSystemResource(new File(attachmentPath));
			helper.addAttachment(file.getFilename(), file);
		}

		mailSender.send(message);
	}
}