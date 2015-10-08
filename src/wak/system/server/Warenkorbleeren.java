package wak.system.server;

import wak.objects.Warenkorb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chris_000 on 08.10.2015.
 */
@WebServlet(name = "Warenkorb-leeren")
public class Warenkorbleeren extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean cookie_vorhanden=false;
        Cookie cook=null;
        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                Cookie c = cookies[i];
                if(c.getName().compareTo("id")==0){
                    cook = c;
                    cookie_vorhanden=true;
                    break;
                }else{

                }
            }
        }else{

        }

        for(Warenkorb w:Seitenaufbau.koerbe){
            if(w.getUuid().equals(cook.getValue())){
                w.dropkorb();
            }
        }
        request.getRequestDispatcher("./jsp/warenkorb.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
