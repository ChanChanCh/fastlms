package com.chanproject.fastlms.components;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class MailComponents {

    private final JavaMailSender javaMailSender;
    public void sendMailTest() {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo("chan.1118.0919@gmail.com");
        msg.setSubject("회원가입 인증 메일입니다.");
        msg.setText(" 회원가입 인증메일입니다");

        javaMailSender.send(msg);

    }

    public boolean sendMail(String mail, String subject, String text ){

        boolean result = false;
        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text,true);
            }
        };

        try {
            javaMailSender.send(msg);
            result = true;

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;

    }
}


