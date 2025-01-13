package com.syskan.notificationmail.dto;

import lombok.Data;

@Data
public class EmailRequest {
	private String to;
	private String subject;
	private String body;
	private String attachment; // Optional: Path to attachment file
}
