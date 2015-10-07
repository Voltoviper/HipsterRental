package wak.system.server;

import wak.objects.Warenkorb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by chris_000 on 07.10.2015.
 */
@WebServlet(name = "proofCookie")
public class proofCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        if(!cookie_vorhanden) {
            UUID uuid = UUID.randomUUID();
            Seitenaufbau.koerbe.add(new Warenkorb(uuid.toString()));
            Cookie id =
                    new Cookie("id", uuid.toString());
            id.setDomain("localhost");
            id.setPath("./");
            response.addCookie(id);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
