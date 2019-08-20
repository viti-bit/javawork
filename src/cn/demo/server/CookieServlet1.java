package cn.demo.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieServlet1")
public class CookieServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应消息体的数据格式及编码
        response.setContentType("text/html;charset=utf-8");

        //1.获取所有Cookie
        Cookie[] cookies = request.getCookies();
        boolean flag=false;
        //2.遍历cookie数组
        if (cookies!=null || cookies.length!=0 ){
            for (Cookie cookie : cookies) {
                //3.获取cookie名称
                String name = cookie.getName();
                //4.判断名称是否lastTime
                if ("lastTime".equals(name)){
                    //有cookie，不是第一次访问
                    flag=true;
                    //响应数据
                    //获取cookie的value，时间
                    String value = cookie.getValue();
                    //URL解码
                    value= URLDecoder.decode(value,"utf-8");
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为："+value+"</h1>");

                    setCookieValue(response,cookie);

                    break;
                }
            }
        }
        if (cookies==null || cookies.length==0 || flag==false){
            //没有，第一次访问
            //设置cookie的value
            Cookie cookie=null;
            setCookieValue(response,cookie);
            response.getWriter().write("<h1>您好，欢迎首次访问</h1>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    public void setCookieValue(HttpServletResponse response,Cookie cookie){
        //设置cooKie的value
        //获取当前时间的字符串，重新设置cookie的值，重新发送cookie
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str_date = sdf.format(date);
        //URL编码
        try {
            str_date= URLEncoder.encode(str_date,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (cookie==null){
            cookie=new Cookie("lastTime",str_date);
        }else{
            cookie.setValue(str_date);
        }
        //设置cookie存活时间
        cookie.setMaxAge(60*60*24*30);//一个月
        response.addCookie(cookie);
    }
}
