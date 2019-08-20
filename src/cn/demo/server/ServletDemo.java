package cn.demo.server;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/demo")
public class ServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        String username=req.getParameter("username");
//        String password = req.getParameter("password");
//        resp.setContentType("text/html;charset=utf-8");
//        if("caterpillar".equals(username) && "123456".equals(password)){
//            resp.getWriter().write("登录成功！");
//        }else{
//            resp.getWriter().write("用户名或密码不对");
//        }

        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

}
