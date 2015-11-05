<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 01.11.2015
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../html/header/header_sub.jsp" %>
<%@ page import="wak.system.server.Seitenaufbau" %>


<div class="main"><!-- start main -->
    <div class="container">


        <div class="row details"><!-- start details -->
            <p class="h2">Profil</p>
            <table style="width: 100%; valign:top" border="0">
                <tr>
                    <%Seitenaufbau.getProfil(out, request.getCookies());%>
                </tr>
            </table>

        </div><!-- end  details -->


    </div>
</div>

<%@include file="../html/footer/footer.jsp" %>
