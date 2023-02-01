package com.chanproject.fastlms.main.controller;



import com.chanproject.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index(){
//
//        String email = "mn99108@naver.com";
//        String subject = "이메일 테스트로 글써봄";
//        String text = "<p>안녕하세요.</p><p>테스트 해봅니다.</p>";
//
//        mailComponents.sendMail(email, subject, text);
        return "index";
    }


    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset-UTF-8");

        PrintWriter printWriter = response.getWriter();

        String msg = "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                "<p>hello</p> <p>fastlms website!!</p>" +
                "<p>안녕하소</p>" +
                "</body>" +
                "</html>";

        printWriter.write(msg);
        printWriter.close();

    }





}
