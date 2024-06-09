package org.star.mailing;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final Handlebars handlebars;

    public void sendRegistration(String email) {
        try {
            Template template = handlebars.compile("template/registration");
            Map<String, Object> templateParams = new HashMap<>();
            templateParams.put("email", email);
            templateParams.put("link", "http://dev-starovoytov.7bits.it");
            String compiledtemplate = template.apply(templateParams);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String from = "nicksterefcraftet@gmail.com";
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("registration");
            helper.setText(compiledtemplate, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            return;
        }
    }
}
