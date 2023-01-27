package com.chanproject.fastlms;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(){

        return "index";
    }

    // 스프링 -> MVC (view -> 템플릿엔진 화면에 내용을 출력(html))
    // .NET -> MVC (View -> 출력)
    // python django -> MTV(Template -> 화면출력)
    // 백엔드과정 -> V(화면에 보여진 부분) -> 프론트엔드과정
    // V -> HTML ==> 웹페이지가
    // V -> json ==> API


    // request -> web -> server
    //response -> server -> wev

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
