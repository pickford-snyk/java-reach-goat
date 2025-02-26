package io.snyk.reachgoat.log4japp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public void init (Principal principal, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write("<h1>Hello User: " + principal.getName() + "</h1>");
        response.getWriter().write("<a href=\"/login?logout\">Logout</a>");
    }
}
