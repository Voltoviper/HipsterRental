<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 14.10.2015
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../html/header/header_subsub.jsp" %>
<%@ page import="wak.system.server.Seitenaufbau" %>

<ol class="breadcrumb">
    <li><a href="../../index.jsp">Home</a></li>
    <li><a href="./Uebersicht-Mitarbeiter.jsp">Mitarbeiterbereich</a></li>
    <li><a href="./bestelluebersicht.jsp">Bestellübersicht</a></li>
    <li class="active">Bestelldetails</li>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
    <div class="container">
        <div class="row details"><!-- start details -->
            <p class="h2">Mitarbeiterliste</p>
            <table style="width: 100%; valign:top" border="0">
                <tr>
                    <%Seitenaufbau.getBestelldetails(out, request.getCookies(), request.getParameter("bestellid"));%>


                </tr>
            </table>

        </div><!-- end  details -->


    </div>
</div>

<%@include file="../../html/footer/footer.jsp" %>
