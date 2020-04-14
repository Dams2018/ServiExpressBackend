package com.serviexpress.apirest.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.thymeleaf.context.Context;

import com.serviexpress.apirest.service.EmailService;



@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    TemplateEngine templateEngine; // From Thymeleaf
    
	@Autowired
    JavaMailSender mailSender;
    
	@Override
	public void emailSend(String email, String name, String usuario, String pass) {
		String processedHTMLTemplate = this.constructHTMLTemplate(name, usuario, pass);

		
			MimeMessagePreparator preparator = message -> {
				MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED,
						"UTF-8");
				helper.setFrom("serviexpress2080@gmail.com");
				helper.setTo(email);
					helper.setSubject("Hola "+ name+ " Bienvenido");
				helper.setText(processedHTMLTemplate, true);
			};

			try {
				System.out.println("Enviando email a " + email);
				mailSender.send(preparator);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

	

	}

	// Fills up the HTML file
	private String constructHTMLTemplate(String name,String usuario, String pass) {

		Context context = new Context();
		context.setVariable("name", name);
		context.setVariable("usuario", usuario);
		context.setVariable("pass", pass);
		return templateEngine.process("MyHTML", context);
	}
    
}