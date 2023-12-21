package com.eventify.constants;

public class PortalConstants {

	private PortalConstants() {
	}

	public static final String EMAIL_RECIPIENT = "priyanshi.porwal@bytesfarms.com";
	public static final String EMAIL_FROM = "priyanshi.porwal@bytesfarms.com";

	public static final String EMAIL_SUBJECT_PREFIX = "Continuum - Your RMA Return Order";
	public static final String EMAIL_BODY_PREFIX = "Continuum - RMA Status Changed";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	// SMTP properties
	public static final String SMTP_HOST = "mail.smtp.host";
	public static final String SMTP_PORT = "mail.smtp.port";
	public static final String SMTP_AUTH = "mail.smtp.auth";
	public static final String SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

	// Email template file path
	public static final String EMAIL_TEMPLATE_FILE_PATH = "resources/email_template.vm";

	public static final String MAIL_HOST = "${spring.mail.host}";
	public static final String MAIL_PORT = "${spring.mail.port}";
	public static final String MAIL_USERNAME = "${spring.mail.username}";
	public static final String MAIL_PASSWORD = "${spring.mail.password}";
}
