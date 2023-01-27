package com.chanproject.fastlms;



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

        mailComponents.sendMailTest();
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
