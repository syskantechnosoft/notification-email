package com.syskan.notificationmail.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syskan.notificationmail.dto.EmailRequest;
import com.syskan.notificationmail.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
		try {
			if (request.getAttachment() != null && !request.getAttachment().isEmpty()) {
				emailService.sendEmailWithAttachment(request.getTo(), request.getSubject(), request.getBody(),
						request.getAttachment());
			} else {
				emailService.sendSimpleEmail(request.getTo(), request.getSubject(), request.getBody());
			}
			return ResponseEntity.ok("Email sent successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to send email: " + e.getMessage());
		}
	}
}
