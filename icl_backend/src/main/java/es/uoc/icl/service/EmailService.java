package es.uoc.icl.service;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import es.uoc.icl.domain.Email;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Value("${spring.mail.username}")
	private String remitente;
	
	public void enviarEmail(Email email) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(remitente);
			helper.setTo(email.getDestino());
			helper.setSubject(email.getTitulo());
			helper.setText(email.getContenido(), true);
			
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enviarEmailPlantilla(Email email, Map<String, Object> model, String template) throws Exception {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			Context context = new Context();
			context.setVariables(model);
			String html = templateEngine.process(template, context);
			helper.setFrom(remitente);
			helper.setTo(email.getDestino());
			helper.setSubject(email.getTitulo());
			helper.setText(html, true);
			FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/images/logo.png"));
			helper.addInline("logo", res);
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
