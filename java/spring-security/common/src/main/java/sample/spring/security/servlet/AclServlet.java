package sample.spring.security.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import sample.spring.security.service.AclService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/acl/*")
public class AclServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext());
        AclService service = context.getBean(AclService.class);
        
        String pathInfo = req.getPathInfo();
        if ("/save".equals(pathInfo)) {
            service.save();
        } else if ("/list".equals(pathInfo)) {
            service.list();
        }
    }
    
    
}
