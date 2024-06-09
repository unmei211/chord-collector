package org.star.mailing;

import com.github.jknack.handlebars.Handlebars;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@AllArgsConstructor
public class MailConfiguration {
    @Bean
    public Handlebars handlebars() {
        return new Handlebars();
    }
}
