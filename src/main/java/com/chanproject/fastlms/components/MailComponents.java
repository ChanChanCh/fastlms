package com.chanproject.fastlms.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class MailComponents {

    private final JavaMailSender javaMailSender;
    public void sendMailTest() {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo("chan.1118.0919@gmail.com");
        msg.setSubject("안녕하세요. 바인홀 입니다.");
        msg.setText(" 안녕하세요. 바인홀입니다 반갑습니다 ");

        javaMailSender.send(msg);


    }

}
